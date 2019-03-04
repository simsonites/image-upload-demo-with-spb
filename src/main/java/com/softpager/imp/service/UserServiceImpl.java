package com.softpager.imp.service;

import com.softpager.imp.entity.User;
import com.softpager.imp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long theId) {
        Optional<User> theUser = userRepository.findById(theId);
        return theUser.orElse(null);
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

}


