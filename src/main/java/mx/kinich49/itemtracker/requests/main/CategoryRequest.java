package mx.kinich49.itemtracker.requests.main;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CategoryRequest {

    protected Long id;
    protected String name;
}