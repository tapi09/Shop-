package com.completeapplication.shop.service.impl;

import com.amazonaws.services.kms.model.NotFoundException;
import com.completeapplication.shop.model.Address;
import com.completeapplication.shop.repository.AddressRepository;
import com.completeapplication.shop.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository repository;
    @Override
    public Address save(Address address){
        return repository.save(address);
    }
    @Override
    public Address getById(Integer id) {

        return repository.findById(id).orElseThrow(() -> new NotFoundException("id inexistente"));
    }
}
