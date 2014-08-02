package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("about")
	public String about(ModelMap model) {
		List<String> contributors = new ArrayList<String>();
		contributors.add("Melanie Reiter");
		contributors.add("Fabian Trampusch");
		contributors.add("Maximilian Götz");
		
		model.addAttribute("contributors", contributors);
		model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
		return "about";
	}
}
