package com.onezero.base;

import java.io.Serializable;
import java.util.List;

public interface ITree<T extends Serializable, K extends ITree<T, K>> extends IBase<T> {
    T getParentId();
    void setParentId(T parentId);

    List<K> getChildren();
    void setChildren(List<K> children);

    Integer getOrderNo();
    void setOrderNo(Integer orderNo);

    Boolean getIsLeaf();
    void setIsLeaf(Boolean isLeaf);
}
