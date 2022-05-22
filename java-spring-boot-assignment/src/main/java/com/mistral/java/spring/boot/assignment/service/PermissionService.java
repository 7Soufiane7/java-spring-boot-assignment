package com.mistral.java.spring.boot.assignment.service;

import com.mistral.java.spring.boot.assignment.model.Permission;
import com.mistral.java.spring.boot.assignment.model.User;
import com.mistral.java.spring.boot.assignment.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public List<Permission> savePermissions(List<Permission> permissions){
        return permissionRepository.saveAll(permissions);
    }

    public List<Permission> getUsers() {
        return permissionRepository.findAll();
    }

    public String deletePermission(int id){
        permissionRepository.deleteById(id);
        return "permission deleted successfully";
    }

    public boolean containsKey(int id) {
        return permissionRepository.existsById(id);
    }
}
