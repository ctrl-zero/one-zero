package com.onezero.core;

import com.onezero.core.enhance.Params;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> params = Params.create()
                .put("a", "b")
                .asMap();
    }
}
