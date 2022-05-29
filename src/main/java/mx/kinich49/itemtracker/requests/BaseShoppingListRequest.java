package mx.kinich49.itemtracker.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BaseShoppingListRequest {

    protected LocalDate shoppingDate;

}
