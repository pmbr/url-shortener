package org.project.repository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.project.exception.ShortenedURLNotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.valueOf;

@Component
public class Repository {

    BiMap<String, String> urlMap = HashBiMap.create();

    private AtomicInteger shortenedUrlGenerator = new AtomicInteger(1);

    public String shortenedUrl(String redirectUrl) {
        String shortenedUrl = urlMap.get(redirectUrl);
        if (shortenedUrl != null) {
            return shortenedUrl;
        }
        shortenedUrl = valueOf(shortenedUrlGenerator.getAndAdd(1));
        urlMap.putIfAbsent(redirectUrl, shortenedUrl);
        return urlMap.get(redirectUrl);
    }

    public String redirectUrl(String shortenedUrl) {
        String redirectUrl = urlMap.inverse().get(shortenedUrl);
        if (redirectUrl == null) {
            throw new ShortenedURLNotFoundException(shortenedUrl);
        }
        return redirectUrl;
    }

}
