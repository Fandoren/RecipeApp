package com.surmin.uaa.controller;

import com.surmin.uaa.model.UserAuthorization;
import com.surmin.uaa.model.UserDto;
import com.surmin.uaa.service.UserService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public UserAuthorization signIn(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    @PostMapping("/validateToken")
    public UserAuthorization signIn(@RequestParam("token") String token) {
        return userService.validateToken(token);
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/{entityId}")
    public UserDto getOne(@PathVariable("entityId") String entityId) {
        return userService.findOne(entityId);
    }

    @GetMapping("/getAll")
    public Collection<UserDto> getAll() {
        return userService.findAll();
    }
}
