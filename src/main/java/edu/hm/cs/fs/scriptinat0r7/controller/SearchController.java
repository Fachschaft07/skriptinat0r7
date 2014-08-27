package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping
    public @ResponseBody Collection<SearchResult> index(final ModelMap model, @RequestParam("q") String searchQuery) {
        List<SearchResult> result = new ArrayList<>();
        result.add(new SearchResult(searchQuery, "http://salatkuchen.net"));
        result.add(new SearchResult("Hans Peter 1", "http://salatkuchen1.net"));
        result.add(new SearchResult("Hans Peter 2", "http://salatkuchen2.net"));
        result.add(new SearchResult("Hans Peter 3", "http://salatkuchen3.net"));
        return result;
    }

    public static class SearchResult {
        private String name;
        private String url;
        
        public SearchResult(String name, String url) {
            this.name = name;
            this.url = url;
        }
        
        public String getName() {
            return name;
        }
        public String getUrl() {
            return url;
        }
        
    }
}
