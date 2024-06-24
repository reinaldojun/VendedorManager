package br.com.vendedormanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidMatriculaTipoContratacaoValidator.class)
public @interface ValidMatriculaTipoContratacao {
    String message() default "Matrícula ou tipo de contratação inválidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
