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

    public static ShoppingItemDto from(ShoppingItem shoppingItem) {
        if (shoppingItem == null)
            return null;

        Item item = shoppingItem.getItem();
        CategoryDto categoryDto = CategoryDto.from(item.getCategory());
        BrandDto brandDto = BrandDto.from(item.getBrand());

        DecimalFormat df = null;
        String quantityDto;
        double unitPrice = shoppingItem.getUnitPrice() / Constants.PRICE_SCALE;
        double totalPrice = unitPrice * shoppingItem.getQuantity();

        String unit = shoppingItem.getUnit();
        double quantity = shoppingItem.getQuantity();

        if (unit != null && unit.length() > 0) {
            df = new DecimalFormat("#0.0##");
            quantityDto = String.format("%1$s %2$s", df.format(quantity), unit);
        } else {
            df = new DecimalFormat("#,###");
            quantityDto = df.format(quantity);
        }

        df = new DecimalFormat("#,##0.00");

        String unitPriceDto = String.format("%1$s%2$s %3$s",
                "$", df.format(unitPrice), shoppingItem.getCurrency());

        String totalPriceDto = String.format("%1$s%2$s %3$s",
                "$", df.format(totalPrice), shoppingItem.getCurrency());

        return new ShoppingItemDto(item.getId(), item.getName(), quantityDto,
                unitPriceDto, totalPriceDto, categoryDto, brandDto);
    }
}