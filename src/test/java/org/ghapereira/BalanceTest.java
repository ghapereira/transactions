package org.ghapereira;

import io.quarkus.test.junit.QuarkusTest;

import org.ghapereira.domain.Account;
import org.ghapereira.domain.Transaction;
import org.ghapereira.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

@QuarkusTest
public class BalanceTest {
    private Long createdAccountId;

    @BeforeEach
    public void setup() {
        Account account = new Account();
        account.setDocumentNumber("123");

        Response response = given()
                            .header("Content-type", "application/json")
                            .and()
                            .body(account)
                            .when()
                            .post("/v1/accounts")
                            .then()
                            .extract().response();

        createdAccountId = response.jsonPath().getLong("id");
    }

    @Test
    public void testBalanceForNewAccountIs0() {
        Response response = given()
            .when().get("/v1/balance/" + createdAccountId)
            .then()
            .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(0, response.jsonPath().getFloat("value"));
    }

    @Test
    public void testBalanceCalculation() {
        Transaction transaction = new Transaction();
        transaction.accountId = createdAccountId;
        transaction.setOperationTypeId(Constants.PAYMENT);
        transaction.setAmount(Float.parseFloat("123.45"));

        given()
            .header("Content-type", "application/json")
            .and()
            .body(transaction)
            .when()
            .post("/v1/transactions");

        Response response = given()
            .when().get("/v1/balance/" + createdAccountId)
            .then()
            .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(123.45, response.jsonPath().getFloat("value"), 0.1f);
    }

}
