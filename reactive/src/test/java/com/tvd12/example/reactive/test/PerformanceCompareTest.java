package com.tvd12.example.reactive.test;

import com.tvd12.example.reactive.HomeController;
import com.tvd12.example.reactive.RxHomeController;
import com.tvd12.example.reactive.core.Reactive;
import com.tvd12.ezyfox.bean.EzyBeanContext;
import com.tvd12.test.performance.Performance;

public class PerformanceCompareTest {
    public static void main(String[] args) {
        final EzyBeanContext beanContext = EzyBeanContext.builder()
            .scan("com.tvd12.example.reactive")
            .build();
        final HomeController sequenceHomeController =
            (HomeController) beanContext.getBean(HomeController.class);
        final RxHomeController rxHomeController =
            (RxHomeController) beanContext.getBean(RxHomeController.class);

        // warm up
        sequenceHomeController.getHomeData();
        rxHomeController.getHomeData();

        long sequenceCallElapsedTime = Performance.create()
            .loop(100000)
            .test(sequenceHomeController::getHomeData)
            .getTime();
        long rxCallElapsedTime = Performance.create()
            .loop(100000)
            .test(rxHomeController::getHomeData)
            .getTime();
        System.out.printf(
            "sequence call elapsed time: %d\nreactive call elapsed time: %d\n",
            sequenceCallElapsedTime,
            rxCallElapsedTime);

        Reactive.destroy();
    }
}
