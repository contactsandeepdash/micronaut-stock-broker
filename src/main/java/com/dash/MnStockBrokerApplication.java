package com.dash;

import com.dash.service.WatchListService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MnStockBrokerApplication {

    private static final Logger log = LoggerFactory.getLogger(MnStockBrokerApplication.class);

    public static void main(String[] args) {

//        Micronaut.run(MnStockBrokerApplication.class, args);
        final ApplicationContext applicationContext = Micronaut.run(MnStockBrokerApplication.class);
        final WatchListService watchListService = applicationContext.getBean(WatchListService.class);
        log.info(watchListService.welcomeFromService());
    }
}