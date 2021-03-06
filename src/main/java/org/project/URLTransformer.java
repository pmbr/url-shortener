package org.project;

import org.project.exception.BadRedirectURLException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class URLTransformer {

    private final String BASE_PROTOCOL = "http://";
    private final String NO_PROTOCOL_MESSAGE = "no protocol";

    public String addHost(HttpServletRequest request, String shortenedUrl) {
        return extractRequestURL(request) + shortenedUrl;
    }

    public String addProtocolIfAbsent(String redirectUrl) {
        try {
            URL url = new URL(redirectUrl);
            return url.toString();
        } catch (MalformedURLException mue) {
            if (mue.getMessage().contains(NO_PROTOCOL_MESSAGE)) {
                return BASE_PROTOCOL + redirectUrl;
            }
            throw new BadRedirectURLException(redirectUrl);
        }
    }

    protected String extractRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

}
