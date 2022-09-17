package com.completeapplication.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("city")
    private String city;

}
