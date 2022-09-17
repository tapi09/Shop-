package com.completeapplication.shop.controller;

import com.completeapplication.shop.dto.AddressDto;
import com.completeapplication.shop.model.Address;
import com.completeapplication.shop.service.AddressService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.completeapplication.shop.api.ApiConstants.ADDRESS_URI;

@RestController
@RequestMapping(ADDRESS_URI)
@RequiredArgsConstructor
@Data
public class AddressController {

}
