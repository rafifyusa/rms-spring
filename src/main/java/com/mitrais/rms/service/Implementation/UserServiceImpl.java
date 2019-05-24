package com.mitrais.rms.service.Implementation;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /*@Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    public Optional<User> findById(long id) {
        return userDao.find(id);
    }

    @Transactional
    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Transactional
    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findByName(String name) {
        return userDao.findByUserName(name);
    }
}
