package com.mistral.java.spring.boot.assignment.controller;

import com.mistral.java.spring.boot.assignment.dto.UserDto;
import com.mistral.java.spring.boot.assignment.exception.ApiRequestException;
import com.mistral.java.spring.boot.assignment.model.Permission;
import com.mistral.java.spring.boot.assignment.model.User;
import com.mistral.java.spring.boot.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @desc Add a user to the database
     */
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("The user was created successfully", HttpStatus.CREATED);
    }

    /**
     * @desc Add a list of users to the database
     */
    @PostMapping("/addUsers")
    public ResponseEntity<Object> addUsers(@RequestBody List<User> users) {
        userService.saveUsers(users);
        return new ResponseEntity<>("The users were created successfully", HttpStatus.CREATED);
    }

    /**
     * @desc Get all the users in the database as DTOs without ordering them
     */
    @GetMapping("/getUsers")
    public List<UserDto> findAllUsersWithoutOrder() {
        return userService.getUsersWithoutOrder();
    }

    /**
     * @desc Get all the users in the database as DTOs while ordering them
     */
    @GetMapping("/getUsers/{order}")
    public List<UserDto> findAllUsersWithOrder(@PathVariable String order) {
        return userService.getUsersWithOrder(order);
    }

    /**
     * @desc Get all the users  in the database as DTOs that correspond to a specific filter
     */
    @GetMapping("/getUsers/{filter}/{value}")
    public List<UserDto> findAllUsersWithFilter(@PathVariable String filter, @PathVariable String value) {
        return userService.getUsersWithFilter(filter, value);
    }

    /**
     * @desc Get a user from the database using its Id
     */
    @GetMapping("/getUser/{id}")
    public UserDto findUserById(@PathVariable int id) {
        if (!userService.containsKey(id))
            throw new ApiRequestException("We can't retrieve the user because there isn't one with the id you provided");
        return userService.getUserById(id);
    }

    /**
     * @desc Update a user in the database
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
        if (!userService.containsKey(id))
            throw new ApiRequestException("We can't update the user because there isn't one with the id you provided");
        userService.updateUser(id, user);
        return new ResponseEntity<>("The user was updated succesfully", HttpStatus.OK);
    }

    /**
     * @desc Manage permissions of a specific user in the database
     */
    @PutMapping("/manageUserPermissions/{id}")
    public ResponseEntity<Object> manageUserPermissions(@PathVariable int id, @RequestBody List<Permission> permissions) {
        if (!userService.containsKey(id))
            throw new ApiRequestException("We can't manage the permissions because there isn't a user with the id you provided");
        userService.manageUserPermissions(id, permissions);
        return new ResponseEntity<>("The permissions of the user were managed successfully", HttpStatus.OK);
    }

    /**
     * @desc Delete a specific user in the database using its Id
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if (!userService.containsKey(id))
            throw new ApiRequestException("We cannot delete the user because the Id doesn't exist");
        userService.deleteUserById(id);
        return new ResponseEntity<>("The user was deleted successfully", HttpStatus.OK);
    }

}
