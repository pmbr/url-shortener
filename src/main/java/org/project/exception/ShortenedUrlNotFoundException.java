package org.project.exception;

public class ShortenedUrlNotFoundException extends RuntimeException {

    public ShortenedUrlNotFoundException(String shortenedUrl) {
        super(String.format("Shortened URL %s not found on repository", shortenedUrl));
    }

}
