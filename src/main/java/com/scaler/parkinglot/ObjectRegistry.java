package com.scaler.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {
    private Map<String, Object> objectsMap = new HashMap<>();

    public void register(String key, Object object) {
        objectsMap.put(key, object);
    }

    public Object get(String key) {
        return objectsMap.get(key);
    }
}
