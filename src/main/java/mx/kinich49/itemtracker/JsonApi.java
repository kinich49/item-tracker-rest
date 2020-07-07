package mx.kinich49.itemtracker;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonApi<T> {

    T data;

    public JsonApi(T data) {
        this.data = data;
    }

}
