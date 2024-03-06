package com.dash;

import com.dash.controller.symbol.SymbolsController;
import com.dash.data.InMemoryStore;
import com.dash.controller.symbol.Symbol;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
public class SymbolControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(SymbolsController.class);

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Inject
    InMemoryStore inMemoryStore;

    @BeforeEach
    void setup() {
        inMemoryStore.initializeWIth(10);
    }

    @Test
    void symbolsEndpointReturnsListOfSymbols() {
        var response = httpClient.toBlocking().exchange("/symbols", JsonNode.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(10, response.getBody().get().size());
    }

    @Test
    void symbolsEndpointReturnsCorrectSymbol() {
        var testSymbol = new Symbol("TEST");
        inMemoryStore.getSymbolMap().put(testSymbol.value(), testSymbol);

        var response = httpClient.toBlocking().exchange("/symbols/" + testSymbol.value(), Symbol.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(testSymbol, response.getBody().get());
    }

    @Test
    void symbolsEndpointReturnsListOfSymbolsTakingQueryParametersIntoAccount() {
        var max10Response = httpClient.toBlocking().exchange("/symbols/filter?max=10", JsonNode.class);
        Assertions.assertEquals(HttpStatus.OK, max10Response.getStatus());
        logger.debug("Max: 10 :\n{}", max10Response.getBody().get());
        Assertions.assertEquals(10, max10Response.getBody().get().size());

        var offset7Response = httpClient.toBlocking().exchange("/symbols/filter?offset=7", JsonNode.class);
        Assertions.assertEquals(HttpStatus.OK, offset7Response.getStatus());
        logger.debug("Offset: 7 :\n{}", offset7Response.getBody().get());
        Assertions.assertEquals(3, offset7Response.getBody().get().size());

        var max2Offset7Response = httpClient.toBlocking().exchange("/symbols/filter?max=2&offset=7", JsonNode.class);
        Assertions.assertEquals(HttpStatus.OK, max2Offset7Response.getStatus());
        logger.debug("Max: 2 and Offset : 7 :\n{}", max2Offset7Response.getBody().get());
        Assertions.assertEquals(2, max2Offset7Response.getBody().get().size());
    }

}
