package com.huobangzhu.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

/**
 * @author: Tim.Yao
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtils {

    public static final boolean isAliquot(@NotNull Integer dividend, @NotNull Integer divisor){
        return isAliquot(dividend.longValue(), divisor.longValue());
    }

    public static final boolean isAliquot(@NotNull Long dividend, @NotNull Long divisor){
        return dividend / divisor * divisor == dividend;
    }

    public static final boolean isAliquot(@NotNull BigDecimal dividend, @NotNull BigDecimal divisor){
        return isZero(dividend.remainder(divisor));
    }

    public static final boolean isAliquot(@NotNull BigInteger dividend, @NotNull BigInteger divisor){
        return isZero(dividend.remainder(divisor));
    }

    public static final boolean isLinear(@NotNull Integer subtrahend, @NotNull Integer minuend, @NotNull Integer relevant){
        return isLinear(subtrahend.longValue(), minuend.longValue(), relevant.longValue());
    }

    public static final boolean isLinear(@NotNull Long subtrahend, @NotNull Long minuend, @NotNull Long relevant){
        return 1 == relevant || isAliquot(subtrahend - minuend, relevant);
    }

    public static final boolean isLinear(@NotNull BigDecimal subtrahend, @NotNull BigDecimal minuend, @NotNull BigDecimal relevant){
        BigDecimal diff = subtrahend.subtract(minuend);
        return isZero(relevant) || isAliquot(diff, relevant);
    }

    public static final boolean isLinear(@NotNull BigInteger subtrahend, @NotNull BigInteger minuend, @NotNull BigInteger relevant){
        BigInteger diff = subtrahend.subtract(minuend);
        return isZero(relevant) || isAliquot(diff, relevant);
    }

    public static final boolean valueEquals(@NotNull BigDecimal o, @NotNull BigDecimal o1){
        return 0 == o.compareTo(o1);
    }

    public static final boolean valueEquals(@NotNull BigInteger o, @NotNull BigInteger o1){
        return 0 == o.compareTo(o1);
    }

    public static final boolean isZero(@NotNull BigDecimal o){
        return valueEquals(BigDecimal.ZERO, o);
    }

    public static final boolean isZero(@NotNull BigInteger o){
        return valueEquals(BigInteger.ZERO, o);
    }

    public static final String cleanUUID(){
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
