package mx.kinich49.itemtracker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionsUtil {

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNeitherNullNorEmpty(Collection<?> collection){
        return collection != null && !collection.isEmpty();
    }
}
