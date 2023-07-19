package com.onezero.base;

import java.io.Serializable;

public interface Creatable<T extends Serializable, K extends Serializable> {
    T getCreator();
    void setCreator(T creator);

    K getCreated();
    void setCreated(K created);
}
