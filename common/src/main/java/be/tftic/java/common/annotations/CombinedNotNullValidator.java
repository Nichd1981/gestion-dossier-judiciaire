package be.tftic.java.common.annotations;

import be.tftic.java.common.models.requests.filter.JugementFilterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

/**
 * Classe de validation de contrainte pour l'annotation @CombinedNotNull.
 * Cette classe est utilisée pour vérifier que l'un des champs spécifiés dans l'annotation @CombinedNotNull n'est pas null ou vide.
 *
 */
@Component
public class CombinedNotNullValidator implements ConstraintValidator<CombinedNotNull, JugementFilterRequest> {

    /**
     * Tableau de noms de champs à vérifier pour l'annotation @CombinedNotNull.
     */
    String[] fields;

    /**
     * Méthode d'initialisation de la contrainte.
     * Cette méthode est appelée par le framework de validation lorsque l'annotation @CombinedNotNull est utilisée pour la première fois.
     *
     * @param combinedNotNull l'annotation @CombinedNotNull à initialiser
     */
    @Override
    public void initialize(final CombinedNotNull combinedNotNull) {
        fields = combinedNotNull.fields();
    }

    /**
     * Méthode de validation de la contrainte.
     * Cette méthode est appelée par le framework de validation pour vérifier que la contrainte @CombinedNotNull est respectée.
     *
     * @param filter l'objet JugementFilterRequest à valider
     * @param context le contexte de validation de contrainte
     * @return true si la contrainte est respectée, false sinon
     */
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
