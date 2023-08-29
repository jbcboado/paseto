package com.practice.dummymicroservice.service;

import com.practice.dummymicroservice.exception.DuplicateException;
import com.practice.dummymicroservice.model.SuccessResponse;
import com.practice.dummymicroservice.model.User;
import com.practice.dummymicroservice.model.dto.SignupDTO;
import com.practice.dummymicroservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateException("Username Already exist !!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateException("Email Already exist !!");
        }

        return userRepository.save(user);
    }

    public User saveUser(SignupDTO signupDTO) {

        if (userRepository.findByUsername(signupDTO.getUsername()).isPresent()) {
            throw new DuplicateException("Username Already exist !!");
        }
        if (userRepository.findByEmail(signupDTO.getEmail()).isPresent()) {
            throw new DuplicateException("Email Already exist !!");
        }

        User newUser = new User();
        newUser.setUsername(signupDTO.getUsername());
        newUser.setFirstName(signupDTO.getFirstName());
        newUser.setLastName(signupDTO.getLastName());
        newUser.setRoles(signupDTO.getRoles());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var encodePassword = passwordEncoder.encode(signupDTO.getPassword());
        newUser.setPassword(encodePassword);

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameValue) throws UsernameNotFoundException {
        Optional<User> user = getUserByUsername(usernameValue);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }


        return user.get();
    }

    /**
     * @param usernameValue username or email
     * @return Optional User
     */
    private Optional<User> getUserByUsername(String usernameValue) {
        // trim username value
        var username = StringUtils.trimToNull(usernameValue);
        if (StringUtils.isEmpty(username)) {
            return Optional.empty();
        }
        return username.contains("@") ? userRepository.findByEmail(username) : userRepository.findByUsername(username);
    }
}
