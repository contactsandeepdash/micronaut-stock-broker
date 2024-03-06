package com.dash.controller.watchlist;

import com.dash.controller.symbol.Symbol;
import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.List;

@Serdeable
public record WatchList (List<Symbol> symbols) {

    public WatchList() {
        this(new ArrayList<>());
    }
}
