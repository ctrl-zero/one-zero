package com.onezero.core.enhance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Decimal<T extends Number> {
    private BigDecimal value;
    private final Class<T> prototype;


    public Decimal(T value) {
        this.value = big(value);
        this.prototype = getType(value);
    }

    public static <T> BigDecimal big(T val) {
        return switch (val) {
          case Integer i -> new BigDecimal(i);
          case Double d -> new BigDecimal(d);
          case Float f -> new BigDecimal(f);
          case BigInteger bi -> new BigDecimal(bi);
          case BigDecimal bd -> bd;
            default -> throw new IllegalStateException("Unexpected value: " + val);
        };
    }

    @SuppressWarnings("unchecked")
    private Class<T> getType(T val) {
        return (Class<T>) val.getClass();
    }
    public static <T extends Number> Decimal<T> of(T val) {
        return new Decimal<>(val);
    }

    public Decimal<T> plus(Number val) {
        this.value = this.value.add(big(val));
        return this;
    }

    public Decimal<T> multi(Number val) {
        this.value = this.value.multiply(big(val));
        return this;
    }

    public Decimal<T> div(Number val) {
        this.value = this.value.divide(big(val), RoundingMode.HALF_UP);
        return this;
    }

    public Decimal<T> div(T val, int scale, RoundingMode roundingMode) {
        this.value = this.value.divide(big(val), scale, roundingMode);
        return this;
    }

    public T get() {
        return get(this.prototype);
    }

    public <K extends Number> K get(Class<K> clazz) {
        if (clazz == Integer.class) {
            return clazz.cast(this.value.intValue());
        } else if (clazz == Float.class) {
            return clazz.cast(this.value.floatValue());
        } else if (clazz == Double.class) {
            return clazz.cast(this.value.doubleValue());
        } else if (clazz == BigInteger.class) {
            return clazz.cast(this.value.toBigInteger());
        } else {
            return clazz.cast(this.value);
        }
    }
}
