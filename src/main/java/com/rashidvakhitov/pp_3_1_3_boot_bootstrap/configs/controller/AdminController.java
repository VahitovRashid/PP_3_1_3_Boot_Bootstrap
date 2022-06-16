package com.rashidvakhitov.pp_3_1_3_boot_bootstrap.configs.controller;


import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.Role;
import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.User;
import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.service.RoleService;
import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //Создание пользователя с ролью ADMIN (Username = admin, Password = admin)
    @PostConstruct
    public void init() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        roles.add(new Role("ROLE_ADMIN"));
        User admin = new User("admin", "admin", (byte) 20, "admin@mail.com", "admin");
        admin.setRoles(roles);
        userService.saveUser(admin);
    }

    @GetMapping(value = "/")
    String getAllUsers(Principal principal, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", userService.findUserByName(principal.getName()));
        return "users";
    }

    @PostMapping(value = "/add")
    String saveUser(@RequestParam(value = "rolesId") String[] roles,
                    @ModelAttribute("user") User user,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users";
        }
        user.setRoles(roleService.getSetRoles(roles));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/{id}")
    public String update(@RequestParam(value = "editRoles") String[] roles,
                         @ModelAttribute("user") User user) {
        user.setRoles(roleService.getSetRoles(roles));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }
}
