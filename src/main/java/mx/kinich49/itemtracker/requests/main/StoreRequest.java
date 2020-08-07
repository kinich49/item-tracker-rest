package mx.kinich49.itemtracker.requests.main;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class StoreRequest {

    protected Long id;
    protected String name;
}