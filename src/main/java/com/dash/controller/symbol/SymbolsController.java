package com.dash.controller.symbol;

import com.dash.data.InMemoryStore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("/")
public class SymbolsController {

    private static final Logger logger = LoggerFactory.getLogger(SymbolsController.class);

    @Inject
    InMemoryStore inMemoryStore;

    @Get
    public String welcomeMessage() {
        return "Welcome to micronaut Stock Broker Service!";
    }

    @Get(value = "/symbols")
    public List<Symbol> getAllSymbols() {
        return new ArrayList<>(inMemoryStore.getSymbolMap().values());
    }

    @Get(value = "/symbols/{value}")
    public Symbol getSymbolByValue(@PathVariable String value) {
        logger.debug("Symbol search for value = {}", value);
        return inMemoryStore.getSymbolMap().get(value);
    }

    @Get("/symbols/filter{?max,offset}")
    public List<Symbol> getSymbolsByQueryValue(@QueryValue Optional<Integer> max, @QueryValue Optional<Integer> offset) {
        return inMemoryStore.getSymbolMap().values()
                .stream()
                .skip(offset.orElse(0))
                .limit(max.orElse(10))
                .toList();
    }

}
