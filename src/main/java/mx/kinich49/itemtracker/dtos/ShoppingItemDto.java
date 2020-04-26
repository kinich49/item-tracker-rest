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

        DecimalFormat df = new DecimalFormat("#.###");
        String quantityDto;
        double unitPrice = shoppingItem.getUnitPrice() / Constants.PRICE_SCALE;
        double totalPrice = unitPrice * shoppingItem.getQuantity();

        //Only supporting KG for now
        if ("KG".equals(shoppingItem.getUnit())) {
            quantityDto = df.format(shoppingItem.getQuantity()) + " KG";
        } else {
            quantityDto = Integer.toString(((int) shoppingItem.getQuantity()));
        }

        //only supporting MXN for now
        //Add conversion later
        df = new DecimalFormat("#.##");
        String unitPriceDto = "$" + df.format(unitPrice) + " MXN";
        String totalPriceDto = "$" + df.format(totalPrice) + " MXN";
        return new ShoppingItemDto(item.getId(), item.getName(), quantityDto,
                unitPriceDto, totalPriceDto, categoryDto, brandDto);
    }
}