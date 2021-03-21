package com.globallogic.Transport.service;

import com.globallogic.Transport.exception.UserNotFoundException;
import com.globallogic.Transport.model.User;

public interface UserService {

    User findByEmailAndPassword(String email, String password) throws UserNotFoundException;
}
