package com.epam.winter.java.lab.controller;

import com.epam.winter.java.lab.service.library.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HomeController {

    private LibraryService libraryService;

    @Resource
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping(value = "/")
    public String init() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home")
    public String homeView(Model model) {
        model.addAttribute("cardUsers", libraryService.getAllCards());
        return "cards";
    }

    @RequestMapping(value = "/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}

