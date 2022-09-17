package com.completeapplication.shop.controller;


import com.completeapplication.shop.dto.UpdateUserDto;
import com.completeapplication.shop.dto.UserDtoResponse;
import com.completeapplication.shop.mapper.UserMapper;
import com.completeapplication.shop.model.User;
import com.completeapplication.shop.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.completeapplication.shop.api.ApiConstants.USER_URI;

@RestController
@RequestMapping(USER_URI)
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> allUser(){
        List<User> users= service.getUsers();
        List<UserDtoResponse> response = mapper.userListToUserDtoList(users);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable("idUser") Integer id){
        User user = service.getUser(id);
        UserDtoResponse response = mapper.userToUserDto(user);
        return ResponseEntity.ok().body(response);
    }
    @PatchMapping("/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @NotNull @PathVariable Integer id,
                       @Valid @RequestBody UpdateUserDto dto){
        User user = mapper.updateUserDtoToUser(dto);
        service.updateEntityIfExists(id, user);
    }



}
