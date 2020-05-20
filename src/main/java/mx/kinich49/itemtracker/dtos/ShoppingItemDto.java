package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.Constants;
import mx.kinich49.itemtracker.models.Item;
import mx.kinich49.itemtracker.models.ShoppingItem;

import java.text.DecimalFormat;

@Data
public class ShoppingItemDto {

    private final long id;
    private final String name;
    private final String quantity;
    private final String unitPrice;
    private final String totalPrice;
    private final CategoryDto category;
    private final BrandDto brand;

    private static final DecimalFormat unitQuantityFormat = new DecimalFormat("#,###");
    private static final DecimalFormat weightQuantityFormat = new DecimalFormat("#0.0##");
    private static final DecimalFormat priceFormat = new DecimalFormat("#,##0.00");

    public static ShoppingItemDto from(ShoppingItem shoppingItem) {
        if (shoppingItem == null)
            return null;

        Item item = shoppingItem.getItem();
        CategoryDto categoryDto = CategoryDto.from(item.getCategory());
        BrandDto brandDto = BrandDto.from(item.getBrand());


        String quantityDto = transformQuantity(shoppingItem.getQuantity(), shoppingItem.getUnit());
        String currency = shoppingItem.getCurrency();
        double unitPrice = transformUnitPrice(shoppingItem.getUnitPrice());
        double totalPrice = unitPrice * shoppingItem.getQuantity();
        String unitPriceDto = formatPrice(unitPrice, currency);
        String totalPriceDto = formatPrice(totalPrice, currency);

        return new ShoppingItemDto(item.getId(), item.getName(), quantityDto,
                unitPriceDto, totalPriceDto, categoryDto, brandDto);
    }

    public static String formatPrice(double price, String currency) {
        return String.format("%1$s%2$s %3$s",
                "$", priceFormat.format(price), currency);
    }

    public static Double transformUnitPrice(int price) {
        return price / Constants.PRICE_SCALE;
    }

    public static Double transformUnitPrice(double price) {
        return price / Constants.PRICE_SCALE;
    }

    public static String transformAndFormatPrice(int price, String currency) {
        return formatPrice(transformUnitPrice(price), currency);
    }

    public static String transformAndFormatPrice(double price, String currency) {
        return formatPrice(transformUnitPrice(price), currency);
    }

    public static String transformQuantity(double quantity, String unit) {
        if (unit != null && unit.length() > 0 && !unit.equalsIgnoreCase("unit")) {
            return String.format("%1$s %2$s", weightQuantityFormat.format(quantity), unit);
        } else {
            return unitQuantityFormat.format(quantity);
        }
    }
}