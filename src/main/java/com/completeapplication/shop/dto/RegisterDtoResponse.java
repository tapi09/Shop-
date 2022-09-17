package com.completeapplication.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDtoResponse {

    private UserDtoResponse userDto;
    private JwtResponse jwtResponse;
}
