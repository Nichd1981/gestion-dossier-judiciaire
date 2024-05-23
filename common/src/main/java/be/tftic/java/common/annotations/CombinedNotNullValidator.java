package be.tftic.java.common.annotations;

import be.tftic.java.common.models.requests.JugementFilterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class CombinedNotNullValidator implements ConstraintValidator<CombinedNotNull, JugementFilterRequest> {

    String[] fields;

    @Override
    public void initialize(final CombinedNotNull combinedNotNull) {
        fields = combinedNotNull.fields();
    }

    @Override
    public boolean isValid(final JugementFilterRequest filter, final ConstraintValidatorContext context) {

        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(filter);

        for (final String f : fields) {
            final Object fieldValue = beanWrapper.getPropertyValue(f);

            if (fieldValue instanceof String) {
                if (!((String) fieldValue).isBlank())
                    return true;
            } else {
                if (fieldValue != null) {
                    return true;
                }
            }
        }

        return false;

    }
}
