package com.huobangzhu.foundation.validation;

import com.huobangzhu.util.MathUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author: Tim.Yao
 */
public class TimesValidator implements ConstraintValidator<Times, Number>{

    private long[] excludes;

    private long value;

    @Override
    public void initialize(Times constraintAnnotation){
        this.excludes = constraintAnnotation.excludes();
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context){
        if(number == null){
            return true;
        }
        if(number instanceof BigDecimal){
            return MathUtils.isAliquot((BigDecimal)number, BigDecimal.valueOf(value));
        }else if(number instanceof BigInteger){
            return MathUtils.isAliquot((BigInteger)number, BigInteger.valueOf(value));
        }else{
            long longValue = number.longValue();
            return MathUtils.isAliquot(longValue, value);
        }
    }
}
