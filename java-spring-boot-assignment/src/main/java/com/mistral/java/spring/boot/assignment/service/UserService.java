package com.mistral.java.spring.boot.assignment.service;

import com.mistral.java.spring.boot.assignment.dto.PermissionDto;
import com.mistral.java.spring.boot.assignment.dto.UserDto;
import com.mistral.java.spring.boot.assignment.model.Permission;
import com.mistral.java.spring.boot.assignment.model.User;
import com.mistral.java.spring.boot.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * @desc This method returns a list of users without ordering them
     */
    public List<UserDto> getUsersWithoutOrder() {
        List<User> users =  userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if(users==null || users.isEmpty())
            return userDtos;
        UserDto userDto;
        for(User user: users)
        {
            userDto = convertUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    /**
     * @desc This method returns a list of users while ordering them using a specific condition
     */
    public List<UserDto> getUsersWithOrder(String order) {

        List<User> users = new ArrayList<>();
        List<UserDto> userDtos= new ArrayList<>();
        UserDto userDto;
        switch(order) {
            case "firstNameAsc":
                users =  userRepository.findAllByOrderByFirstNameAsc();
                break;
            case "firstNameDes":
                users = userRepository.findAllByOrderByFirstNameDesc();
                break;
            case "lastNameAsc":
                users = userRepository.findAllByOrderByLastNameAsc();
                break;
            case "lastNameDes":
                users = userRepository.findAllByOrderByLastNameDesc();
            case "userNameAsc":
                users = userRepository.findAllByOrderByUserNameAsc();
                break;
            case "userNameDes":
                users = userRepository.findAllByOrderByUserNameDesc();
                break;
            case "emailAsc":
                users = userRepository.findAllByOrderByEmailAsc();
                break;
            case "emailDes":
                users = userRepository.findAllByOrderByEmailDesc();
                break;
            default:
                users = userRepository.findAll();
        }
        if(users==null || users.isEmpty())
            return userDtos;

        for(User user: users)
        {
            userDto = convertUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    /**
     * @desc This method returns a userDto based on the user Id
     */
    public UserDto getUserById(int id) {
        User user =  userRepository.findById(id).orElse(null);
        UserDto userDto = convertUserToUserDto(user);
        return userDto;
    }

    /**
     * @desc This method converts a User to a userDto
     */
    public UserDto convertUserToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        if(user==null)
            return userDto;
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        Set<Permission> userPermissions = user.getPermissions();
        Set<PermissionDto> userDtoPermissions = new HashSet<>();
        if(userPermissions == null || userPermissions.isEmpty())
        {
            userDto.setPermissions(userDtoPermissions);
            return userDto;
        }
        PermissionDto userPermissionDto = new PermissionDto();
        for(Permission permission: userPermissions)
        {
            userPermissionDto.setCode(permission.getCode());
            userPermissionDto.setDescription(permission.getDescription());
            userDtoPermissions.add(userPermissionDto);
        }
        userDto.setPermissions(userDtoPermissions);
        return userDto;
    }

    /**
     * @desc This method deletes a userById
     */
    public String deleteUserById(int id){
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    /**
     * @desc This method updates a user
     */
    public User updateUser(int id, User user){
        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    /**
     * @desc This method returns a user using a specific filter
     */
    public List<UserDto> getUsersWithFilter(String filter, String value) {

        List<User> users = new ArrayList<>();
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto;

        switch (filter) {
            case "firstName" :
                users = userRepository.findAllByFirstName(value);
                break;
            case "lastName" :
                users = userRepository.findAllByLastName(value);
                break;
            case "userName" :
                users = userRepository.findAllByUserName(value);
                break;
            case "email" :
                users = userRepository.findAllByEmail(value);
                break;
            case "status" :
                users = userRepository.findAllByStatus(value);
                break;
        }
        if(users==null || users.isEmpty())
            return userDtos;

        for(User user: users)
        {
            userDto = convertUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    /**
     * @desc This method manages the permissions of a user
     */
    public User manageUserPermissions(int id, List<Permission> permissions) {
        User userToManage = userRepository.findById(id).orElse(null);
        Set<Permission> userToManagePermissions = userToManage.getPermissions();
        Set<Permission> permissionsToRemove = new HashSet<>();

        for(Permission permission: userToManagePermissions)
        {
            if(!permissions.contains(permission))
                permissionsToRemove.add(permission);
        }
        userToManagePermissions.removeAll(permissionsToRemove);
        for(Permission permission: permissions)
        {
            if(!userToManagePermissions.contains(permission))
                userToManagePermissions.add(permission);
        }
        return userRepository.save(userToManage);
    }

    /**
     * @desc This method can be used to check if a user exists or not using their Id
     */
    public boolean containsKey(int id) {
        return userRepository.existsById(id);
    }
}
