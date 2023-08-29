package com.practice.dummymicroservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    @NotNull
    @Size(min = 4, max = 24)
    @NotBlank(message = "username is mandatory")
    private String username;

    private String firstName;

    private String lastName;
    @Email
    @NotNull
    @NotBlank(message = "email is mandatory")
    private String email;

    private List<GroupRoleDTO> roles;
}
