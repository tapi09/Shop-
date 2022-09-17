package com.completeapplication.shop.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.completeapplication.shop.model.Address;
import com.completeapplication.shop.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


public interface AddressService {

    Address save(Address address);

    Address getById(Integer id);
}
