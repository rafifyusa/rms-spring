package com.mitrais.rms.service;

import com.mitrais.rms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    boolean save(User user);
    boolean delete(User user);
    boolean update(User user);
    List<User> findAll();

}
