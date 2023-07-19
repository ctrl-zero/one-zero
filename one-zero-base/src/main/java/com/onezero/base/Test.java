package com.onezero.base;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> params = Params.create()
                .put("a", "b")
                .asMap();
    }
}
