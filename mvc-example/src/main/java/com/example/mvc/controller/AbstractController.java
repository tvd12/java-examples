package com.example.mvc.controller;

import com.example.mvc.manager.BeanManager;

public class AbstractController {

    protected <T> T getBean(Class<T> beanType) {
        return BeanManager.getInstance().getBean(beanType);
    }
}
