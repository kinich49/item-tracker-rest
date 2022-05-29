package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListBelongsToUserCondition implements Condition<ShoppingListOwnershipParameter> {

    private final ShoppingListRepository repository;

    @Autowired
    public ShoppingListBelongsToUserCondition(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Error> assertCondition(ShoppingListOwnershipParameter param) throws ValidationFlowException {
        var shoppingListID = param.getShoppingListId();
        if (NumberUtils.isNullOrZero(shoppingListID)) {
            throw  new ValidationFlowException("ID can't be null");
        }

        var username = param.getUserDetails().getUsername();
        var shoppingList = repository.findById(shoppingListID)
                .orElseThrow(() -> new ValidationFlowException(String.format("Shopping List with ID %d not found", shoppingListID)));

        boolean isSameUser = shoppingList.getUser().getUsername().equals(username);

        if (isSameUser)
            return Optional.empty();
        else return Optional.of(new Error(0, String.format("Shopping list with ID %1$d not found for user %2$s",
                shoppingListID, username)));
    }
}
