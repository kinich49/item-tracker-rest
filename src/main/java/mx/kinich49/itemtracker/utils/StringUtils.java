package mx.kinich49.itemtracker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNeitherNullNorEmpty(String s) {
        return s != null && s.length() > 0;
    }
}
