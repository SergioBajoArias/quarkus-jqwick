package com.sergio.resource;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

@QuarkusTest
class DivideResourcePBT_Test {

    @TestHTTPResource("/")
    URL baseURL;

    @Test
    void testDivide() {
        qt()
            .forAll(integers().all(), integers().all().assuming(i -> i != 0))
            .checkAssert((dividend, divisor) -> {
                try (HttpClient client = HttpClient.newBuilder().build()) {

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(baseURL.toString() + "/divide/" + dividend + "/" + divisor))
                            .GET()
                            .build();

                    HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

                    Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
                } catch (URISyntaxException | InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}