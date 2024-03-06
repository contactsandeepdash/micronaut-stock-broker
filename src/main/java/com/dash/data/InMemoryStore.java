package com.dash.data;

import com.dash.controller.symbol.Symbol;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryStore.class);

    public Map<String, Symbol> getSymbolMap() {
        return symbolMap;
    }

    private final Map<String, Symbol> symbolMap = new HashMap<>();

    private final Faker faker = new Faker();

    @PostConstruct
    public void initialize() {
        initializeWIth(10);
    }

    public void initializeWIth(int numberOfEntries) {
        symbolMap.clear();
        IntStream.range(0, numberOfEntries).forEach( i ->
                addSymbol());
    }

    private void addSymbol() {
        var symbol = new Symbol(faker.stock().nsdqSymbol());
        symbolMap.put(symbol.value(), symbol);
        logger.debug("Added symbol: {}", symbol);
    }



}
