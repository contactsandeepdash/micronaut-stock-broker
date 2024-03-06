package com.dash.controller;

import com.dash.data.InMemoryStore;
import com.dash.model.Symbol;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Controller("/")
public class SymbolsController {

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

}
