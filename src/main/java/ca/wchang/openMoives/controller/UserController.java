package ca.wchang.openMoives.controller;

import ca.wchang.openMoives.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
//    @Autowired
//    private UserService userService;

    @GetMapping("/getUser")
    public String getUser() {

        System.out.println("hello world");
        return "test user";
    }

}
