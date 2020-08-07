package com.epam.winter.java.lab.controller;

import com.epam.winter.java.lab.model.User;
import com.epam.winter.java.lab.model.library.UserInfo;
import com.epam.winter.java.lab.service.library.LibraryService;
import com.epam.winter.java.lab.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {

    UserService userService;
    LibraryService libraryService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping(value = "/user/userView", method = GET)
    public String viewUser(@RequestParam Integer cardId, Model model) {
        UserInfo userInfo = libraryService.getUserInfo(cardId);
        model.addAttribute("userInfo", userInfo);
        return "user/viewUser";
    }

    @RequestMapping(value = "/security/user/delete", method = GET)
    public String deleteUser(@RequestParam Integer userId) {
        userService.delete(userId);
        return "redirect:/home";
    }

    @RequestMapping(value = "/security/user/editView", method = GET)
    public String editUserView(@RequestParam Integer userId, Model model) {
        model.addAttribute("user", userService.getById(userId));
        return "user/editUser";
    }

    @RequestMapping(value = "/security/user/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/home";
    }

    @RequestMapping(value = "/security/user/add", method = GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user/addUser";
    }

    @RequestMapping(value = "/security/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        libraryService.addUser(user);
        return "redirect:/home";
    }
}
