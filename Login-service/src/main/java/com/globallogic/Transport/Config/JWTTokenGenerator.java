package com.globallogic.Transport.Config;

import com.globallogic.Transport.model.User;

import java.util.Map;

public interface JWTTokenGenerator {
    Map<String, String> generateToken(User user);
}
