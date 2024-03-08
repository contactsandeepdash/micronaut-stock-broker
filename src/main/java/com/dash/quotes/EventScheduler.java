package com.dash.quotes;

import com.dash.quotes.external.quote.ExternalQuoteProducer;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class EventScheduler {

    private static final Logger log = LoggerFactory.getLogger(EventScheduler.class);

    private static final List<String> SYMBOLS = Arrays.asList("AAPL", "AMZN", "FB", "GOOGL", "NLFX", "TSLA");
    private final ExternalQuoteProducer externalQuoteProducer;

    public EventScheduler(ExternalQuoteProducer externalQuoteProducer) {
        this.externalQuoteProducer = externalQuoteProducer;
    }

    @Scheduled(fixedDelay = "10s")
    void generate() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final ExternalQuote quote = new ExternalQuote(
                SYMBOLS.get(random.nextInt(0, SYMBOLS.size() - 1)),
                randomValue(random),
                randomValue(random)
        );

        log.debug("Generate external quote {}", quote);
        externalQuoteProducer.send(quote.getSymbol(), quote);
    }

    private BigDecimal randomValue(final ThreadLocalRandom random) {
        return BigDecimal.valueOf(random.nextDouble(0, 1000));
    }

}
