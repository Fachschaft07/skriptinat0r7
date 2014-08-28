package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ScriptRepository scripts;
    
    @Autowired
    private ProfessorRepository professors;
    
    @RequestMapping
    public @ResponseBody Collection<SearchResult> index(final ModelMap model, @RequestParam("q") String searchQuery) {
        List<SearchResult> result = new ArrayList<>();
        
        for(Script script : scripts.findByNameContaining(searchQuery)) {
            result.add(new SearchResult(script.toString(), script.toString()));
        }
        for(Professor professor : professors.findByFirstNameContainingOrLastNameContaining(searchQuery)) {
            result.add(new SearchResult(professor.toString(), professor.toString()));
        }
        
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
