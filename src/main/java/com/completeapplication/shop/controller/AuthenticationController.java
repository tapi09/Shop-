package com.completeapplication.shop.controller;

import com.completeapplication.shop.common.security.services.AuthenticationService;
import com.completeapplication.shop.dto.*;
import com.completeapplication.shop.mapper.AuthenticationMapper;
import com.completeapplication.shop.mapper.UserMapper;
import com.completeapplication.shop.model.User;
import com.completeapplication.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController{

    private final AuthenticationService authService;

    private final UserMapper userMapper;

    private final AuthenticationMapper authMapper;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterDtoRequest request) {
        User user = authMapper.createUserRequestToUser(request);
        User createdUser = userService.saveUser(user);
        UserDtoResponse userResponse = authMapper.userToUserResponse(createdUser);
        String jwt = authService.singIn(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(new RegisterDtoResponse(userResponse, new JwtResponse(jwt)),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody AuthenticationDto authRequest) {

        String jwt = authService.singIn(authRequest.getEmail(), authRequest.getPassword());

        return ResponseEntity.ok(new JwtResponse(jwt));

    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoResponse meData(@AuthenticationPrincipal User user) {
        return userMapper.userToUserDto(user);
    }

}

