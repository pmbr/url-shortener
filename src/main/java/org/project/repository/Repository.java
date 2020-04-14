package org.project.repository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.project.exception.ShortenedUrlNotFoundException;

import java.util.concurrent.atomic.AtomicInteger;

public class Repository {

    BiMap<String, Integer> urlMap = HashBiMap.create();

    private AtomicInteger shortenedUrlGenerator = new AtomicInteger(1);

    public Integer shortenedUrl(String redirectUrl) {
        urlMap.putIfAbsent(redirectUrl, shortenedUrlGenerator.getAndAdd(1));
        return urlMap.get(redirectUrl);
    }

    public String redirectUrl(Integer shortenedUrl) {
        String redirectUrl = urlMap.inverse().get(shortenedUrl);
        if (redirectUrl == null) {
            throw new ShortenedUrlNotFoundException(shortenedUrl);
        }
        return redirectUrl;
    }

}
