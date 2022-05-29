package mx.kinich49.itemtracker.validators.brandrequest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BrandRequestValidatorTest {

    @InjectMocks
    BrandRequestValidator subject;

    @Mock
    BrandRequestConditionProvider conditionProvider;

}
