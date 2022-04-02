package com.example.mvc.manager;

import java.util.HashMap;
import java.util.Map;

public class BeanManager {

    private final Map<Object, Object> beanByKey;

    private static final BeanManager INSTANCE = new BeanManager();

    private BeanManager() {
        this.beanByKey = new HashMap<>();
    }

    public static BeanManager getInstance() {
        return INSTANCE;
    }

    public void addBean(Object key, Object bean) {
        this.beanByKey.put(key, bean);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Object key) {
        return (T) beanByKey.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanOf(Class<T> key) {
        return (T) beanByKey.get(key);
    }
}
