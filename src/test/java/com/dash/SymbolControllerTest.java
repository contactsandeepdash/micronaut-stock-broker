package com.dash;

import com.dash.data.InMemoryStore;
import com.dash.model.Symbol;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class SymbolControllerTest {

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

}
