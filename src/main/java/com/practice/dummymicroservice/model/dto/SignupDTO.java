package com.practice.dummymicroservice.model.dto;

import com.practice.dummymicroservice.model.GroupRole;
import lombok.Data;

import java.util.List;

@Data
public class SignupDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<GroupRole> roles;
}
