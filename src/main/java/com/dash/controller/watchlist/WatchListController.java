package com.dash.controller.watchlist;

import com.dash.data.InMemoryAccountStore;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Controller("/account/watchlist")
public class WatchListController {

    private static final Logger LOG = LoggerFactory.getLogger(WatchListController.class);

    public static final UUID ACCOUNT_ID = UUID.randomUUID();

    @Inject
    InMemoryAccountStore store;

    @Get(produces = MediaType.APPLICATION_JSON)
    public WatchList get() {
        return store.getWatchList(ACCOUNT_ID);
    }

    @Put(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public WatchList update(@Body WatchList watchList) {
        return store.updateWatchList(ACCOUNT_ID, watchList);
    }

}