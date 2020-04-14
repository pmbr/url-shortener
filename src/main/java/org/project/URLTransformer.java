package org.project;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class URLTransformer {

    public String addHost(HttpServletRequest request, String shortenedUrl) {
        return request.getRequestURL().toString() + shortenedUrl;
    }

    public String addProtocolIfMissing(String redirectUrl) throws MalformedURLException {
        String redirectUrlWithProtocol = redirectUrl;
        try {
            URL url = new URL(redirectUrl);
            return url.toString();
        } catch (MalformedURLException mue) {
            if (mue.getMessage().contains("no protocol")) {
                return "http://" + redirectUrl;
            }
            throw mue;
        }
    }


}
