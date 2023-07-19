package com.onezero.core.util;


import com.onezero.base.ITree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@code @author:}  voncho
 * {@code @description:} 将list数据转换成树型数据
 * {@code @date:} 2022/11/29 20:54
 */
public interface TreeUtil {

    static <T extends ITree<E, T>, E extends Serializable> List<T> listToTree(List<T> data) {
        return listToTree(data, t -> {});
    }
    static <T extends ITree<E, T>, E extends Serializable> List<T> listToTree(List<T> data, Comparator<? super T> comparator) {
        return listToTree(data, t -> {}, comparator);
    }

    static <T extends ITree<E, T>, E extends Serializable> List<T> listToTree(List<T> data, Consumer<T> consumer) {
        return listToTree(data, consumer, Comparator.comparing(T::getOrderNo));
    }

    /**
     * @param consumer 数据处理回掉
     * @return 转换后数据
     */
    static <T extends ITree<E, T>, E extends Serializable> List<T> listToTree(List<T> data, Consumer<T> consumer, Comparator<? super T> comparator) {
        if (data == null || data.size() == 0) {
            return List.of();
        }

        Map<E, T> dataMap = data.stream()
                .peek(consumer)
                .collect(
                        Collectors.toMap(T::getId, d -> d)
                );

        Map<E, List<T>> groupByParentIdMap = data.stream()
                .collect(
                        Collectors.groupingBy(T::getParentId)
                );
        return composeTree(dataMap, groupByParentIdMap, comparator);
    }

    static <T extends ITree<E, T>, U extends ITree<E, U>, E extends Serializable> List<U> listToTree(List<T> data, Function<T, U> converter) {
        return listToTree(data, converter, Comparator.comparing(U::getOrderNo));
    }

    /**
     * @param converter 数据转换回掉
     * @param <T>       传入数据类型
     * @param <U>       返回数据类型
     * @return 转换后的数据
     */
    static <T extends ITree<E, T>, U extends ITree<E, U>, E extends Serializable> List<U> listToTree(List<T> data, Function<T, U> converter, Comparator<? super U> comparator) {
        if (data == null || data.size() == 0) {
            return List.of();
        }

        if (converter == null) {
            throw new RuntimeException("converter must not be null");
        }

        Map<E, U> dataMap = data.stream()
                .map(converter)
                .collect(
                        Collectors.toMap(U::getId, d -> d)
                );

        Map<E, List<U>> groupByParentIdMap = dataMap.values().stream()
                .collect(
                        Collectors.groupingBy(U::getParentId)
                );
        return composeTree(dataMap, groupByParentIdMap, comparator);

    }

    private static <T extends ITree<E, T>, E extends Serializable> List<T> composeTree(Map<E, T> dataMap, Map<E, List<T>> groupMap, Comparator<? super T> comparator) {
        List<T> trees = new ArrayList<>();

        groupMap.keySet()
                .forEach(
                        parentId -> {
                            if (dataMap.containsKey(parentId)) {
                                T parent = dataMap.get(parentId);
                                List<T> children = groupMap.get(parentId);
                                boolean hasChild = children != null && children.size() > 0;
                                if (hasChild) {
                                    children.sort(comparator);
                                    parent.setChildren(children);
                                }
                            } else {
                                trees.addAll(groupMap.get(parentId));
                            }
                        }
                );
        trees.sort(comparator);
        return trees;
    }

    static <T extends ITree<E, T>, E extends Serializable> T first(List<T> treeList) {
        if (treeList == null) return null;
        T first = treeList.get(0);
        while (first.getChildren() != null && first.getChildren().size() > 0) {
            first = first.getChildren().get(0);
        }
        return first;
    }
}
