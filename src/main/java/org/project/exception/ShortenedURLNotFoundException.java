package org.project.exception;

public class ShortenedURLNotFoundException extends RuntimeException {

    public ShortenedURLNotFoundException(String shortenedURL) {
        super(String.format("Shortened URL %s not found on repository", shortenedURL));
    }

}
