package mx.kinich49.itemtracker;

import lombok.Getter;


public final class JsonApi<T> {

    @Getter
    private final T data;
    @Getter
    private final String error;

    public JsonApi(T data) {
        this.data = data;
        this.error = null;
    }

    public JsonApi(String error) {
        this.error = error;
        this.data = null;
    }
}
