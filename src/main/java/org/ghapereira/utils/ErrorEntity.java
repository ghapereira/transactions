package org.ghapereira.utils;

/**
 * POJO to wrap error message
 * Source: https://www.baeldung.com/jax-rs-response
 */
public class ErrorEntity {
    public String errorMessage;

    public ErrorEntity(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
