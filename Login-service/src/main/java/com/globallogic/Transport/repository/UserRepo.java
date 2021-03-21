package com.globallogic.Transport.repository;

import com.globallogic.Transport.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {

    User findByEmailAndPassword(String email, String password);

}
