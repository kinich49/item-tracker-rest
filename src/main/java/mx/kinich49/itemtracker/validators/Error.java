package mx.kinich49.itemtracker.validators;

import lombok.Data;

@Data
public class Error {

    private final int code;
    private final String message;
}
