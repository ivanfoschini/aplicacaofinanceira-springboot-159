package aplicacaofinanceira.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CnpjValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cnpj {
    public static final String DEFAULT_MESSAGE = "O CNPJ fornecido é inválido";
    
    String message() default DEFAULT_MESSAGE;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
