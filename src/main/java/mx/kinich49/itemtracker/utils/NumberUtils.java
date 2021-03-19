package mx.kinich49.itemtracker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberUtils {

    public static boolean isNullOrZero(Integer param) {
        return param == null || param == 0;
    }

    public static boolean isNullOrZero(Long param) {
        return param == null || param == 0;
    }

}
