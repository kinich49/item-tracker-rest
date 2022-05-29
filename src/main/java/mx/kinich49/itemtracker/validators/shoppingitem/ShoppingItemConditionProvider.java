package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemConditionProvider extends AbstractConditionProvider<ShoppingItemRequestParameter> {

    private final ItemCurrencyCondition itemCurrencyCondition;
    private final ItemNameCondition itemNameCondition;
    private final ItemUnitCondition itemUnitCondition;
    private final ItemUnitPriceCondition itemUnitPriceCondition;

    @Autowired
    public ShoppingItemConditionProvider(ItemCurrencyCondition itemCurrencyCondition,
                                         ItemNameCondition itemNameCondition,
                                         ItemUnitCondition itemUnitCondition
            , ItemUnitPriceCondition itemUnitPriceCondition) {
        this.itemCurrencyCondition = itemCurrencyCondition;
        this.itemNameCondition = itemNameCondition;
        this.itemUnitCondition = itemUnitCondition;
        this.itemUnitPriceCondition = itemUnitPriceCondition;
    }

    @Override
    public void buildConditions(ShoppingItemRequestParameter parameter) {
        addCondition(itemCurrencyCondition, parameter);
        addCondition(itemNameCondition, parameter);
        addCondition(itemUnitCondition, parameter);
        addCondition(itemUnitPriceCondition, parameter);
    }


}
