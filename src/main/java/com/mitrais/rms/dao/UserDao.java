package com.mitrais.rms.dao;

import com.mitrais.rms.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Long>{
    Optional<User> findByUserName(String userName);
}
