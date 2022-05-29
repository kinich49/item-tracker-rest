package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.utils.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ErrorBuilder {

    private final Set<Integer> consumedKeys = new HashSet<>();
    private final StringBuilder stringBuilder = new StringBuilder();

    public void acceptIfAbsent(Error errorWrapper) {
        if (!consumedKeys.contains(errorWrapper.getCode())) {
            stringBuilder.append(errorWrapper.getMessage());
            consumedKeys.add(errorWrapper.getCode());
        }
    }

    public Optional<String> buildErrorMessage() {
        if (StringUtils.isNeitherNullNorEmptyNorBlank(stringBuilder.toString())) {
            return Optional.of(stringBuilder.toString());
        }

        return Optional.empty();
    }
}
