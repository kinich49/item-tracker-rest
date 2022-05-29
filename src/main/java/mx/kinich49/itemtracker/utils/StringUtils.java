package mx.kinich49.itemtracker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmptyOrBlank(String s) {
        return s == null || s.isEmpty() || s.isBlank();
    }

    public static boolean isNullOrEmptyOrBlank(CharSequence s){
        return s == null || s.length() == 0  || s.toString().isBlank();
    }

    public static boolean isNeitherNullNorEmpty(String s) {
        return s != null && s.length() > 0;
    }

    public static boolean isNeitherNullNorEmptyNorBlank(String s) {
        return s != null && s.length() > 0 && !s.equals(" ");
    }
}
