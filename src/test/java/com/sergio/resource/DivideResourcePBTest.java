package com.sergio.resource;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

@QuarkusTest
class DivideResourcePBTest {

    @TestHTTPResource("/")
    URL baseURL;

    @Test
    void testDivide() {
        qt()
            .forAll(integers().all(), integers().all().assuming(i -> i != 0))
            .check((dividend, divisor) -> {
                try {
                    URL url = new URI(baseURL.toString() + "/divide/" + dividend + "/" + divisor).toURL();
                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                    httpConnection.connect();
                    return httpConnection.getResponseCode() == HttpStatus.SC_OK;
                } catch (URISyntaxException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}