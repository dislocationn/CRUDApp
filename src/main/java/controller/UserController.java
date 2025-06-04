package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("user", service.getAllUsers());
        return "user";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "useradd";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        try {
            service.saveUser(user);
            return "redirect:/user";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "useradd";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = service.findUserById(id);
        model.addAttribute("user", user);
        return "useredit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user, Model model) {
        try {
            service.update(user);
            return "redirect:/user";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "useredit";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return "redirect:/user";
    }
}