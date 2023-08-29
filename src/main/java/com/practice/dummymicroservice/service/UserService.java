package com.practice.dummymicroservice.service;

import com.practice.dummymicroservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, UserDetailsPasswordService {

    /**
    * @return list of User
    * */
    List<User> getUsers();

    /**
     * @param user ussr object
     * @return user saved or updated
     */

    User saveUser(User user);

}
