package com.sergio.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

@QuarkusTest
class DivideResourcePBT_Test {

    @Test
    void testDivide() {
        qt()
            .forAll(integers().all(), integers().all().assuming(i -> i != 0))
            .checkAssert((dividend, divisor) -> {
                given()
                    .when()
                        .get("/divide/" + dividend + "/" + divisor)
                    .then()
                        .statusCode(200);
            });
    }
}