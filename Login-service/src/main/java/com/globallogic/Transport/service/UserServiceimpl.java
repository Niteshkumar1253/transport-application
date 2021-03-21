package com.globallogic.Transport.service;

import com.globallogic.Transport.exception.UserNotFoundException;
import com.globallogic.Transport.model.User;
import com.globallogic.Transport.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceimpl implements  UserService{

    private UserRepo userRepo;

@Autowired
    public UserServiceimpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User authUser = userRepo.findByEmailAndPassword(email, password);
        //here we are calling findByIdAndPassword method of userRepository with 2 arguments id and password
        //and storing the result into authUser object of User class(Domain)
        if (authUser == null) {
            log.error("User Not Found");
            throw new UserNotFoundException("Invalid Credentials");
        }
        log.info("sending User Credentials");
        return authUser;
    }
    }

