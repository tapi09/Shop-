package com.completeapplication.shop.dto;


import com.completeapplication.shop.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
}
