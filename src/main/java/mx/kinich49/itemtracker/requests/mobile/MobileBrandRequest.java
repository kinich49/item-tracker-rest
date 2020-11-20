package mx.kinich49.itemtracker.requests.mobile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.main.BrandRequest;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class MobileBrandRequest extends BrandRequest {

    private Long mobileId;

}
