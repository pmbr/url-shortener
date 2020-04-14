package org.project.repository;

import org.junit.Before;
import org.junit.Test;
import org.project.exception.ShortenedUrlNotFoundException;

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
        String redirectUrl = "www.google.com";
        String shortenedUrl = repository.shortenedUrl(redirectUrl);
        assertNotNull(shortenedUrl);
    }

    @Test
    public void inputSameRedirectUrlTwiceReturnsPreviouslyGeneratedShortenedUrl() {
        String redirectUrl = "www.apple.com";

        String shortenedUrl = repository.shortenedUrl(redirectUrl);
        assertNotNull(shortenedUrl);

        String repositoryShortenedUrl = repository.shortenedUrl(redirectUrl);
        assertNotNull(repositoryShortenedUrl);

        assertEquals(repositoryShortenedUrl, shortenedUrl);
    }

    @Test
    public void searchRedirectUrlForShortenedUrlPresentOnRepositoryReturnsExpectedRedirectUrl() {
        String redirectUrl = "www.yahoo.com";

        String shortenedUrl = repository.shortenedUrl(redirectUrl);
        assertNotNull(shortenedUrl);

        String repositoryRedirectUrl = repository.redirectUrl(shortenedUrl);
        assertNotNull(repositoryRedirectUrl);

        assertEquals(repositoryRedirectUrl, redirectUrl);
    }

    @Test(expected = ShortenedUrlNotFoundException.class)
    public void searchRedirectUrlForShortenedUrlMissingOnRepositoryReturnsException() {
        String redirectUrl = "www.amazon.com";
        repository.shortenedUrl(redirectUrl);
    }

}
