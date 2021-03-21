package com.globallogic.Transport.Config;

import com.globallogic.Transport.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JWTTokenGeneratorImpl  implements JWTTokenGenerator{
    public static final int VALITIDY_PERIOD =  600 * 10000;
    @Value("${jwt.secret}")   //i.e. reading the value of secret from application.yml
    private String secret;   //property

    @Value("${app.jwttoken.message}") //also reading the value pf message from application.yml file
    private String message;           //property

    //creating overridden generateToken Method
    @Override
    public Map<String, String> generateToken(User user) {
        log.info("secret:{}",secret);
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALITIDY_PERIOD))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        /*
         * Generate JWT token and store in String jwtToken
         */

        Map<String,String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put("token",jwtToken);
        jwtTokenMap.put("message",message);
        return jwtTokenMap;
    }
}

