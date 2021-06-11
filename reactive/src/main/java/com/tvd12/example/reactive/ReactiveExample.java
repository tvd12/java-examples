package com.tvd12.example.reactive;

import com.tvd12.example.reactive.data.HomeData;
import com.tvd12.ezyfox.bean.EzyBeanContext;

public class ReactiveExample {

    public static void main(String[] args) {
        final EzyBeanContext beanContext = EzyBeanContext.builder()
            .scan("com.tvd12.example.reactive")
            .build();
        final RxHomeController rxHomeController =
            (RxHomeController) beanContext.getBean(RxHomeController.class);
        final HomeData rxHomeData = rxHomeController.getHomeData();
        System.out.println("rxHomeData: " + rxHomeData);
    }

}
