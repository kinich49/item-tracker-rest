package mx.kinich49.itemtracker.validators;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class Pair<L, R> {
    private final @NonNull L left;
    private final @NonNull R right;

    private Pair(@NonNull L left,
                 @NonNull R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair<L, R> of(@NonNull L left, @NonNull R right) {
        return new Pair<>(left, right);
    }
}
