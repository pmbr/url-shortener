package org.project.repository;

import org.junit.Before;
import org.junit.Test;
import org.project.exception.ShortenedURLNotFoundException;

import static java.lang.String.valueOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class RepositoryTest {

    private Repository repository;

    @Before
    public void setUp() {
        repository = new Repository();
    }

    @Test
    public void inputNewRedirectUrlGeneratesNewShortenedUrl() {
        String redirectURL = "www.google.com";
        String shortenedURL = repository.shortenedUrl(redirectURL);
        assertNotNull(shortenedURL);
    }

    @Test
    public void inputSameRedirectUrlTwiceReturnsPreviouslyGeneratedShortenedUrl() {
        String redirectURL = "www.apple.com";

        String shortenedURL = repository.shortenedUrl(redirectURL);
        assertNotNull(shortenedURL);

        String repositoryShortenedURL = repository.shortenedUrl(redirectURL);
        assertNotNull(repositoryShortenedURL);

        assertEquals(repositoryShortenedURL, shortenedURL);
    }

    @Test
    public void searchRedirectUrlForShortenedUrlPresentOnRepositoryReturnsExpectedRedirectUrl() {
        String redirectURL = "www.yahoo.com";

        String shortenedURL = repository.shortenedUrl(redirectURL);
        assertNotNull(shortenedURL);

        String repositoryRedirectURL = repository.redirectUrl(shortenedURL);
        assertNotNull(repositoryRedirectURL);

        assertEquals(repositoryRedirectURL, redirectURL);
    }

    @Test(expected = ShortenedURLNotFoundException.class)
    public void searchRedirectUrlForShortenedUrlMissingOnRepositoryReturnsException() {
        String shortenedURL = valueOf(Integer.MAX_VALUE);
        repository.redirectUrl(shortenedURL);
    }

}
