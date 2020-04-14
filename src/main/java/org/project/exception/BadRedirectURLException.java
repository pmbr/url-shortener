package org.project.exception;

public class BadRedirectURLException extends RuntimeException {

    public BadRedirectURLException(String redirectURL) {
        super(String.format("Attempt of shorting invalid redirect URL: %s", redirectURL));
    }

}
