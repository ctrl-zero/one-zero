package com.onezero.base;

import java.io.Serializable;

public interface IBase<T extends Serializable> extends Serializable {
    T getId();
    void setId(T id);
}
