package com.mistral.java.spring.boot.assignment.dto;

import com.mistral.java.spring.boot.assignment.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private String code;
    private String description;
}