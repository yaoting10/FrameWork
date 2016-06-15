package com.huobangzhu.foundation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: Tim.Yao
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {TimesValidator.class})
public @interface Times{

    String message() default "{constraints.Times.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long[] excludes() default {};

    long value() default 1;
}
