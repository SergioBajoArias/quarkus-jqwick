package com.sergio.resource;

import io.quarkus.test.junit.QuarkusTest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DivideResourceTest {
    @Test
    void testDivide() {
        given()
                .when()
                .get("/divide/9/3")
                .then()
                .statusCode(200)
                .body(is("3.00000"));
    }

    @ParameterizedTest
    @ArgumentsSource(DivideArgumentsProvider.class)
    void testDivideBatch(int dividend, int divisor, double result) {
        given()
                .when()
                .get("/divide/" + dividend + "/" + divisor)
                .then()
                .statusCode(200)
                .body(is(BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP).toString()));
    }

    @NoArgsConstructor
    static class DivideArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(9, 3, 3.00000),
                    Arguments.of(10, 2, 5.00000),
                    Arguments.of(10, 3, 3.33333),
                    Arguments.of(10, -3, -3.33333),
                    Arguments.of(-10, 3, -3.33333),
                    Arguments.of(-10, -3, 3.33333)
            );
        }


    }
}