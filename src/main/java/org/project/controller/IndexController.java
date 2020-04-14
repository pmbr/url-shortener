package org.project.controller;

import org.project.URLTransformer;
import org.project.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class IndexController {

    private Repository repository;
    private URLTransformer urlTransformer;

    public IndexController(Repository repository, URLTransformer urlTransformer) {
        this.repository = repository;
        this.urlTransformer = urlTransformer;
    }

    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("shortenedURL", "");
        model.addAttribute("errorMessage", "");
        return "index";
    }

    @PostMapping("/")
    public String shortUrl(HttpServletRequest request, String redirectURL, ModelMap model) {
        try {
            String shortenedURL = repository.shortenedUrl(urlTransformer.addProtocolIfMissing(redirectURL));
            model.addAttribute("shortenedURL", urlTransformer.addHost(request, shortenedURL));
        } catch (MalformedURLException mue) {
            model.addAttribute("errorMessage", "Enter a valid URL");
        }
        return "index";
    }

}
