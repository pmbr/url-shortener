package org.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class URLTransformerTest {

    private URLTransformer urlTransformer;

    @Before
    public void setUp() {
        urlTransformer = spy(new URLTransformer());
    }

    @Test
    public void addProtocolWhenAbsent() {
        String redirectURL = "www.apple.com";
        String returnedRedirectURL = urlTransformer.addProtocolIfAbsent(redirectURL);
        assertEquals("http://www.apple.com", returnedRedirectURL);
    }

    @Test
    public void addNoProtocolWhenPresent() {
        String redirectURL = "https://store.apple.com";
        String returnedRedirectURL = urlTransformer.addProtocolIfAbsent(redirectURL);
        assertEquals("https://store.apple.com", returnedRedirectURL);
    }

    @Test
    public void addHostToShortenedURL() {
        doReturn("http://localhost:8080/").when(urlTransformer).extractRequestURL(any());
        String shortenedUrlWithHost = urlTransformer.addHost(null, "1");
        assertEquals("http://localhost:8080/1", shortenedUrlWithHost);
    }

}