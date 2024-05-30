package be.tftic.java.common.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation de contrainte pour vérifier que l'un des champs spécifiés n'est pas null ou vide.
 * Cette annotation peut être utilisée sur une classe ou une interface pour valider les champs spécifiés.
 *
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = CombinedNotNullValidator.class)
public @interface CombinedNotNull {

    /**
     * Message d'erreur à afficher si la contrainte n'est pas respectée.
     * La valeur par défaut est "fields are required".
     */
    String message() default "fields are required";

    /**
     * Groupes de contraintes auxquels cette contrainte appartient.
     * La valeur par défaut est un tableau vide.
     */
    Class<?>[] groups() default {};

    /**
     * Charge utile de contrainte associée à cette contrainte.
     * La valeur par défaut est un tableau vide.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Tableau de noms de champs à vérifier pour cette contrainte.
     * La valeur par défaut est un tableau vide.
     */
    String[] fields() default {};

}
