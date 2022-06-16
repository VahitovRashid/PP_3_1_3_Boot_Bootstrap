package com.rashidvakhitov.pp_3_1_3_boot_bootstrap.service;

import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User saveUser(User user);

    void deleteById(Long id);

    User findUserByName(String name);
}
