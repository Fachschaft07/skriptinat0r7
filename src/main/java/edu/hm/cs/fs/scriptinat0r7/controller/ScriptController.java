package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

@Controller
@RequestMapping("/scripts")
public class ScriptController {

    @Autowired
    ScriptRepository scripts;

    @RequestMapping
    public String example(final ModelMap model) {

        return "example";
    }
}
