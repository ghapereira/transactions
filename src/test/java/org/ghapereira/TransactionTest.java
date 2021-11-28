package org.ghapereira;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

@QuarkusTest
public class TransactionTest {

    @Test
    public void testGetAccountsInfoOnlyAcceptsIntegers() {
        given()
            .when().get("/v1/accounts/a")
            .then()
            .statusCode(404);
    }

    @Test
    public void testAccountCannotBeCreatedWithoutMandatoryFields() {
        String requestBodyWrongParameter = "{\"__documentNumber\": \"x2100\"}";

        Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBodyWrongParameter)
            .when().post("/v1/accounts")
            .then()
            .extract().response();

        Assertions.assertEquals(422, response.statusCode());

        String requestBodyEmptyObject = "{}";

        response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBodyEmptyObject)
            .when().post("/v1/accounts")
            .then()
            .extract().response();

        Assertions.assertEquals(422, response.statusCode());
    }
}
