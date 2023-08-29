package com.practice.dummymicroservice.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GroupRoleDTO {

    @NotNull
    List<Long> roles;
}
