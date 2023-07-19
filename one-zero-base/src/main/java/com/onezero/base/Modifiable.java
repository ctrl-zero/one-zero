package com.onezero.base;

import java.io.Serializable;

public interface Modifiable<T extends Serializable, K extends Serializable> {
    T getModifier();
    void setModifier(T modifier);

    K getModified();
    void setModified(K modified);
}
