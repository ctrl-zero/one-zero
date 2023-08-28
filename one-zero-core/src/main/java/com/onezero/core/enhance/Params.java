package com.onezero.core.enhance;

import java.util.HashMap;
import java.util.Map;

public class Params<T> {
    private final Map<String, T> data;

    public Params(Map<String, T> data) {
        this.data = data;
    }

    public static <T> Params<T> create(Map<String, T> data) {
        return new Params<>(data);
    }
    public static <T> Params<T> create() {
        Map<String, T> data = new HashMap<>();
        return create(data);
    }

    public Params<T> put(String key, T value) {
        this.data.put(key, value);
        return this;
    }

    public Params<T> remove(String key) {
        this.data.remove(key);
        return this;
    }

    public Map<String, T> asMap() {
        return this.data;
    }
}
