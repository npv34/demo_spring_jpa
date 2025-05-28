package org.app.demojpa.controllers;


import org.app.demojpa.entities.Department;
import org.app.demojpa.entities.User;
import org.app.demojpa.request.UserRequest;
import org.app.demojpa.services.DepartmentService;
import org.app.demojpa.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final DepartmentService departmentService;

    public UserController(UserService userService,
                          DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "users/create";
    }

    @PostMapping("/store")
    public String storeUser(@ModelAttribute UserRequest userRequest) {
        userService.createUser(userRequest);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
