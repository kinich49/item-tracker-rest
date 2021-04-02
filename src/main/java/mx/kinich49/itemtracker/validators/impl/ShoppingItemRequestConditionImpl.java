package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingItemRequestConditionImpl implements Condition<MainShoppingItemRequest> {

    private final BrandRequestConditionImpl brandRequestCondition;
    private final CategoryRequestConditionImpl categoryRequestCondition;

    @Autowired
    public ShoppingItemRequestConditionImpl(BrandRequestConditionImpl brandRequestCondition,
                                            CategoryRequestConditionImpl categoryRequestCondition) {
        this.brandRequestCondition = brandRequestCondition;
        this.categoryRequestCondition = categoryRequestCondition;
    }

    @Override
    public Optional<String> assertCondition(MainShoppingItemRequest request) {
        if (request == null)
            return Optional.of("Item should not be null");

        var errorsAccumulator = new ArrayList<String>();

        assertItemProperties(request, errorsAccumulator);
        assertBrandRequest(request, errorsAccumulator);
        assertCategoryRequest(request, errorsAccumulator);


        if (errorsAccumulator.isEmpty())
            return Optional.empty();
        else
            return formatErrorMessages(errorsAccumulator);
    }

    private void assertBrandRequest(MainShoppingItemRequest request, List<String> errorAccumulator) {
        brandRequestCondition.assertCondition(request.getBrand())
                .ifPresent(errorAccumulator::add);
    }

    private void assertCategoryRequest(MainShoppingItemRequest request, List<String> errorAccumulator) {
        categoryRequestCondition.assertCondition(request.getCategory())
                .ifPresent(errorAccumulator::add);
    }

    private void assertItemProperties(MainShoppingItemRequest request, List<String> errorAccumulator) {
        assertName(request)
                .ifPresent(errorAccumulator::add);

        assertQuantity(request)
                .ifPresent(errorAccumulator::add);

        assertUnitPrice(request)
                .ifPresent(errorAccumulator::add);

        assertUnit(request)
                .ifPresent(errorAccumulator::add);

        assertCurrency(request)
                .ifPresent(errorAccumulator::add);
    }

    private Optional<String> assertQuantity(MainShoppingItemRequest request) {
        if (request.getQuantity() == 0.0)
            return Optional.of("Item is missing Quantity property.");

        return Optional.empty();
    }

    private Optional<String> assertUnitPrice(MainShoppingItemRequest request) {
        if (request.getUnitPrice() < 1 )
            return Optional.of("Acceptable lowest unit price is 1 (One).");

        return Optional.empty();
    }

    private Optional<String> assertUnit(MainShoppingItemRequest request) {
        if (StringUtils.isNullOrEmptyOrBlank(request.getUnit()))
            return Optional.of("Item is missing Unit property.");

        return Optional.empty();
    }

    private Optional<String> assertName(MainShoppingItemRequest request) {
        if (StringUtils.isNullOrEmptyOrBlank(request.getName()))
            return Optional.of("Item is missing Name property.");

        return Optional.empty();
    }

    private Optional<String> assertCurrency(MainShoppingItemRequest request) {
        if (StringUtils.isNullOrEmptyOrBlank(request.getCurrency()))
            return Optional.of("Item is missing Currency property.");

        return Optional.empty();
    }

    private Optional<String> formatErrorMessages(List<String> errors) {
        var stringBuilder = new StringBuilder();

        for (int i = 0; i < errors.size(); i++) {
            stringBuilder.append(errors.get(i));
            if (i == errors.size() - 2)
                stringBuilder.append(" ");
        }

        return Optional.of(stringBuilder.toString());
    }
}
