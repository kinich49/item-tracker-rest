package mx.kinich49.itemtracker;

import lombok.Getter;


public final class JsonApi<T> {

    @Getter
    private final T data;
    @Getter
    private final String error;

    private JsonApi(T data, String error) {
        this.data = data;
        this.error = error;
    }

    public JsonApi(T data) {
        this.data = data;
        this.error = null;
    }

    public JsonApi(String error) {
        this.error = error;
        this.data = null;
    }


    public static <T> JsonApi<T> ok(T data) {
        return new JsonApi<>(data, null);
    }

    public static JsonApi<Object> error(String error) {
        return new JsonApi<>(null, error);
    }
}
