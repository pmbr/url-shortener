package org.project;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class URLTransformerTest {

    private URLTransformer urlTransformer;

    @Before
    public void setUp() {
        urlTransformer = new URLTransformer();
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

}