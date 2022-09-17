package com.completeapplication.shop.mapper;

import com.completeapplication.shop.dto.RegisterDtoRequest;
import com.completeapplication.shop.dto.UserDtoResponse;
import com.completeapplication.shop.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticationMapper {

        UserDtoResponse userToUserResponse(User user);
        User createUserRequestToUser(RegisterDtoRequest user);
}
