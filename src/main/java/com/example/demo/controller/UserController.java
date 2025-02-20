package com.example.demo.controller;

import com.example.demo.common.Wrapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/bySomeField")
    public List<User> getUsersByClass(@RequestBody Wrapper<Object> some) {
        return userService.findByIdOrPassword(some);
    }

    @GetMapping("/updateAndGet/{id}")
    public User getUsersByClass(@PathVariable Long id,
                                @RequestBody Wrapper<String> some) {
        return userService.updateUserAndGet(id, some);
    }
}
