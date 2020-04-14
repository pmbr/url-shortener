package org.project.repository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.project.exception.ShortenedURLNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.valueOf;

@Component
public class Repository {

    Logger logger = LoggerFactory.getLogger(Repository.class);

    BiMap<String, String> urlMap = HashBiMap.create();

    private AtomicInteger shortenedUrlGenerator = new AtomicInteger(1);

    public String shortenedUrl(String redirectURL) {
        String shortenedURL = urlMap.get(redirectURL);
        if (shortenedURL == null) {
            shortenedURL = valueOf(shortenedUrlGenerator.getAndAdd(1));
            urlMap.putIfAbsent(redirectURL, shortenedURL);
            shortenedURL = urlMap.get(redirectURL);
        }
        logger.info("Generated shortened URL {} for redirect URL {}", shortenedURL, redirectURL);
        return shortenedURL;
    }

    public String redirectUrl(String shortenedURL) {
        String redirectURL = urlMap.inverse().get(shortenedURL);
        if (redirectURL == null) {
            throw new ShortenedURLNotFoundException(shortenedURL);
        }
        logger.info("Returned redirect URL {} for shortened URL {}", redirectURL, shortenedURL);
        return redirectURL;
    }

}
