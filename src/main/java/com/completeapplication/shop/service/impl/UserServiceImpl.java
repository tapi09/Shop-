package com.completeapplication.shop.service.impl;


import com.completeapplication.shop.common.exception.ConflictException;
import com.completeapplication.shop.common.exception.NotFoundException;
import com.completeapplication.shop.model.Address;
import com.completeapplication.shop.model.Role;
import com.completeapplication.shop.model.User;
import com.completeapplication.shop.repository.RoleRepository;
import com.completeapplication.shop.repository.UserRepository;
import com.completeapplication.shop.service.AddressService;
import com.completeapplication.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService  {

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AddressService addressService;

    private static final Long ROLE_USER_ID = (long) 2;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return repository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name: %s not found"));
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getEmail());
        if (existsByEmail(user.getEmail())) {
            throw new ConflictException("Email address '%s' already exists");
        }
        Role role = roleRepository.findById(ROLE_USER_ID).orElseThrow(() -> new NotFoundException("invalid role"));
        user.setRole(role);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        if (user.getAddress()!= null) {
            Address address = user.getAddress();
            user.setAddress(addressService.save(address));
        }
        return repository.save(user);
    }
    @Override
    public User getUser(Integer id) {
        log.info("Fetching user by id {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return repository.findAll();
    }

    @Override
    public void updateEntityIfExists(Integer id, User user) {
        repository.findById(id)
                  .map(userJpa -> {
                      Optional.ofNullable(user.getFirstName()).ifPresent(userJpa::setFirstName);
                      Optional.ofNullable(user.getLastName()).ifPresent(userJpa::setLastName);
                      return repository.save(userJpa);
                  }).orElseThrow(() -> new NotFoundException(id));
    }

    private boolean existsByEmail(String email) {
        log.info("validating if email exists");
        return repository.findUserByEmail(email).isPresent();
    }
}
