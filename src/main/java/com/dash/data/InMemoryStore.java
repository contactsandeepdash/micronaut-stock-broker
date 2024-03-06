package com.dash.data;

import com.dash.model.Symbol;
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
        IntStream.range(0, 10).forEach( i ->
                addSymbol());
    }

    private void addSymbol() {
        var symbol = new Symbol(faker.stock().nsdqSymbol());
        symbolMap.put(symbol.value(), symbol);
        logger.debug("Added symbol: {}", symbol);
    }



}
