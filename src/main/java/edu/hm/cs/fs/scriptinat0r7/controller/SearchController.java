package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;

/**
 * Controller responsible for search queries, e.g. the quick ajax-search and an extended search.
 */
@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController {

    @Autowired
    private LectureService lectures;

    @Autowired
    private ProfessorService professors;

    /**
     * Request method for serializing search results via json.
     *
     * @param model
     *            the model data.
     * @param searchQuery
     *            the search request.
     * @return A collection of search results.
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<SearchResult> index(final ModelMap model, @RequestParam("q") final String searchQuery) {
        final List<SearchResult> result = new ArrayList<>();

        for (final Professor professor : professors.findByFirstNameContainingOrLastNameContaining(searchQuery)) {
            // TODO: provide real urls
            result.add(new SearchResult(professor.getFullName(), professor.toString()));
        }
        for (final Lecture lecture : lectures.findByNameContaining(searchQuery)) {
            // TODO: provide real url
            result.add(new SearchResult(lecture.getLectureAndProfessor(), lecture.toString()));
        }

        return result;
    }

    /**
     * Method used for displaying advanced search form.
     * @return The logical view name.
     */
    @RequestMapping(value = "/advanced", method = RequestMethod.GET)
    public String advancedSearch() {
        return "search/advanced";
    }

    /**
     * Class used for encapsulating and serializing search results.
     */
    public static class SearchResult {
        private final String name;
        private final String url;

        /**
         * Constructs a new search result.
         * @param name The name for the search result, probably displayed to the client.
         * @param url The url wherre this search result is pointing to.
         */
        public SearchResult(final String name, final String url) {
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
