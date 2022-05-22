package com.mistral.java.spring.boot.assignment.controller;

import com.mistral.java.spring.boot.assignment.exception.ApiRequestException;
import com.mistral.java.spring.boot.assignment.model.Permission;
import com.mistral.java.spring.boot.assignment.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * @desc Add a permission to the database
     */
    @PostMapping("/addPermission")
    public ResponseEntity<Object> addUser(@RequestBody Permission permission) {
        permissionService.savePermission(permission);
        return new ResponseEntity<>("The permission was added successfully", HttpStatus.CREATED);
    }

    /**
     * @desc Add a list of permissions to the database
     */
    @PostMapping("/addPermissions")
    public ResponseEntity<Object> addUser(@RequestBody List<Permission> permissions) {
        permissionService.savePermissions(permissions);
        return new ResponseEntity<>("The permissions were added successfully", HttpStatus.CREATED);
    }

    /**
     * @desc Delete a permission from the database
     */
    @DeleteMapping("/deletePermission/{id}")
    public String deleteUser(@PathVariable int id) {
        if (!permissionService.containsKey(id))
            throw new ApiRequestException("We can't delete the permission because there isn't one with the id you provided");
        return permissionService.deletePermission(id);
    }
}
