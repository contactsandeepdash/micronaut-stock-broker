package com.dash.quotes.external.quote;

import com.dash.quotes.ExternalQuote;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@KafkaListener(
        clientId = "pricing-external-quote-consumer",
        groupId = "external-quote-consumer",
        batch = true
)
public class ExternalQuoteConsumer {

    private static final Logger log = LoggerFactory.getLogger(ExternalQuoteConsumer.class);

    @Topic("external-quotes")
    void receive(List<ExternalQuote> externalQuotes) {
        log.debug("Consuming batch of external quotes {}", externalQuotes);
    }
}
