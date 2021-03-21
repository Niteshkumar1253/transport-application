package com.globallogic.Transport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "USER_PROFILE")
public class User {

    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String password;
    private String city;
    private String phoneNo;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
