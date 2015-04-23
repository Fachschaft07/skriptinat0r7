package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrdersController extends AbstractController {

    public void orderScripts(@RequestParam(value="script[]", required=false) final String[] scriptHashes) {

    }

}
