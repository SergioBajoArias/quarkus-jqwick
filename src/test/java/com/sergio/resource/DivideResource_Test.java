package com.sergio.resource;

import com.sergio.common.Constants;
import io.quarkus.test.junit.QuarkusTest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DivideResource_Test {
    @Test
    void testDivide() {
        given()
            .when()
                .get("/divide/9/3")
            .then()
                .statusCode(200)
                .body(is(BigDecimal.valueOf(3).setScale(Constants.DEFAULT_NUMBER_OF_DECIMALS, Constants.DEFAULT_ROUNDING_MODE).toString()));
    }

    @ParameterizedTest
    @ArgumentsSource(DivideArgumentsProvider.class)
    void testDivideBatch(int dividend, int divisor, double result) {
        given()
                .when()
                .get("/divide/" + dividend + "/" + divisor)
                .then()
                .statusCode(200)
                .body(is(BigDecimal.valueOf(result).setScale(Constants.DEFAULT_NUMBER_OF_DECIMALS, Constants.DEFAULT_ROUNDING_MODE).toString()));
    }

    @NoArgsConstructor
    static class DivideArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(9, 3, 3),
                    Arguments.of(10, 2, 5),
                    Arguments.of(10, 3, 3.333333333333),
                    Arguments.of(10, -3, -3.333333333333),
                    Arguments.of(-10, 3, -3.333333333333),
                    Arguments.of(-10, -3, 3.333333333333)
            );
        }
    }
}