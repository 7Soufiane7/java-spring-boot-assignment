package com.mistral.java.spring.boot.assignment.repository;

import com.mistral.java.spring.boot.assignment.model.Permission;
import com.mistral.java.spring.boot.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
