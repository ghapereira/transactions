package org.ghapereira.utils;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * POJO to wrap error message
 * Source: https://www.baeldung.com/jax-rs-response
 */
@RegisterForReflection // https://quarkus.io/guides/writing-native-applications-tips#registering-for-reflection
public class ErrorEntity {
    public String errorMessage;

    public ErrorEntity(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
