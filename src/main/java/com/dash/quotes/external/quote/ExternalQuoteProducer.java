package com.dash.quotes.external.quote;

import com.dash.quotes.ExternalQuote;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(
        id = "external-quote_producer"
)
public interface ExternalQuoteProducer {

    @Topic("external-quotes")
    void send(@KafkaKey String symbok, ExternalQuote externalQuote);

}
