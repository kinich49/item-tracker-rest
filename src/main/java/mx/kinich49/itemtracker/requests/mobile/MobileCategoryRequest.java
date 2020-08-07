package mx.kinich49.itemtracker.requests.mobile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MobileCategoryRequest extends CategoryRequest {

    private Long mobileId;
}
