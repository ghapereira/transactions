package org.ghapereira;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TransactionTest {

    @Test
    public void testGetAccountsInfoOnlyAcceptsIntegers() {
        given()
            .when().get("/v1/accounts/a")
            .then()
            .statusCode(404);
    }

}
