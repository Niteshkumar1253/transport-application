package com.globallogic.Transport.controller;

import com.globallogic.Transport.Config.JWTTokenGenerator;
import com.globallogic.Transport.exception.UserNotFoundException;
import com.globallogic.Transport.model.User;
import com.globallogic.Transport.model.UserCredDto;
import com.globallogic.Transport.model.jwtObject;
import com.globallogic.Transport.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/login")
@Slf4j
public class UserController {
    private UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;
    ResponseEntity<?> responseEntity;


    @Value("${app.controller.exception.message1}")//getting the value from application.yml file
    private String message1;

    @Value("${app.controller.exception.message2}")//getting the value from application.yml file
    private String message2;

    @Value("${app.controller.exception.message3}")//getting the value from application.yml file
    private String message3;

    //Constructor based DI for injecting JWT Token Generator
    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }
    @PostMapping("/user")
    public ResponseEntity<?> loginUser(@RequestBody UserCredDto user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException(message1);
            }
            User userDetails = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            //creating object userDetails of User class

            if (userDetails == null) {
                throw new UserNotFoundException(message2);
            }
            if (!(user.getPassword().equals(userDetails.getPassword()))) {
                throw new UserNotFoundException(message3);
            }
            Map<String,String> result = jwtTokenGenerator.generateToken(userDetails);
            log.info("jwt toke: {}",result.get("token"));
            responseEntity = ResponseEntity.ok(new jwtObject(result.get("token"), user.getEmail()));

            /*
             * Create ResponseEntity with token generated by calling generateToken method of JwtTokenGenerator
             */
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


}
