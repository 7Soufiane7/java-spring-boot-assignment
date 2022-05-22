package com.mistral.java.spring.boot.assignment.dto;

import com.mistral.java.spring.boot.assignment.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private Set<PermissionDto> permissions;
}
