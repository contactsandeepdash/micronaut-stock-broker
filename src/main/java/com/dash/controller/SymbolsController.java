package com.dash.controller;

import com.dash.data.InMemoryStore;
import com.dash.model.Symbol;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Controller("/")
public class SymbolsController {

    private static final Logger logger = LoggerFactory.getLogger(SymbolsController.class);

    @Inject
    InMemoryStore inMemoryStore;

    @Get
    public String welcomeMessage() {
        return "Welcome to micronaut Stock Broker Service!";
    }

    @Get(value = "symbols")
    public List<Symbol> getAllSymbols() {
        return new ArrayList<>(inMemoryStore.getSymbolMap().values());
    }

    @Get(value = "symbols/{value}")
    public Symbol getSymbolByValue(@PathVariable String value) {
        logger.debug("Symbol search for value = {}", value);
        return inMemoryStore.getSymbolMap().get(value);
    }
}
