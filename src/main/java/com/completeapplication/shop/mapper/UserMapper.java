package com.completeapplication.shop.mapper;

import com.completeapplication.shop.dto.AuthenticationDto;
import com.completeapplication.shop.dto.RegisterDtoRequest;
import com.completeapplication.shop.dto.UpdateUserDto;
import com.completeapplication.shop.dto.UserDtoResponse;
import com.completeapplication.shop.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface UserMapper {

    User updateUserDtoToUser(UpdateUserDto userRequest);

    @Named("userToUserDto")
    UserDtoResponse userToUserDto(User user);

    @IterableMapping(qualifiedByName = "userToUserDto")
    List<UserDtoResponse> userListToUserDtoList(List<User> users);
}

