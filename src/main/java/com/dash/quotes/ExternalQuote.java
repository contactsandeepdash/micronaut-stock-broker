package com.dash.quotes;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Serdeable
public class ExternalQuote {

    private String symbol;
    private BigDecimal lastPrice;
    private BigDecimal volume;

    public ExternalQuote(String symbol, BigDecimal lastPrice, BigDecimal volume) {
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
