package com.practice.dummymicroservice.controller;

import com.practice.dummymicroservice.model.dto.LoginDTO;
import com.practice.dummymicroservice.model.dto.SignupDTO;
import com.practice.dummymicroservice.model.SuccessResponse;
import com.practice.dummymicroservice.security.TokenProvider;
import com.practice.dummymicroservice.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserAccountController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> registerUser(@RequestBody SignupDTO signupDTO){
        return ResponseEntity.ok(new SuccessResponse(userDetailsService.saveUser(signupDTO), "User created."));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<SuccessResponse> authenticateUser(@RequestBody LoginDTO loginDTO){

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new SuccessResponse(jwt, "Login successful."));
    }

}
