package com.mistral.java.spring.boot.assignment.controller;

import com.mistral.java.spring.boot.assignment.dto.UserDto;
import com.mistral.java.spring.boot.assignment.model.User;
import com.mistral.java.spring.boot.assignment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {

    @MockBean
    private UserService userService;

    @Test
    public void testAddUser() {
        UserController controller = new UserController();
        User user = new User(1, "firstName", "LastName", "UserName", "Password", "email", "status", null);
        ReflectionTestUtils.setField(controller, "userService", userService);
        when(userService.saveUser(user)).thenReturn(user);
        String body = "The user was created successfully";

        ResponseEntity responseEntity = controller.addUser(user);
        assertEquals(responseEntity.getBody(), body);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testAddUsers() {
        UserController controller = new UserController();
        User user1 = new User(1, "firstName", "LastName", "UserName", "Password", "email", "status", null);
        User user2 = new User(2, "firstName2", "LastName2", "UserName2", "Password2", "email2", "status2", null);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        String expectedBodyMessage = "The users were created successfully";
        HttpStatus expectedHttpStatus = HttpStatus.CREATED;

        ReflectionTestUtils.setField(controller, "userService", userService);
        when(userService.saveUsers(users)).thenReturn(users);

        ResponseEntity ActualResponseEntity = controller.addUsers(users);
        assertEquals(ActualResponseEntity.getBody(), expectedBodyMessage);
        assertEquals(ActualResponseEntity.getStatusCode(), expectedHttpStatus);
    }

    @Test
    public void testFindAllUsersWithoutOrder() {
        UserController controller = new UserController();
        UserDto userDto1 = new UserDto("firstName", "LastName", "email", "status", null);
        UserDto userDto2 = new UserDto("firstName2", "LastName2", "email2", "status2", null);
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto1);
        userDtos.add(userDto2);

        ReflectionTestUtils.setField(controller, "userService", userService);
        when(userService.getUsersWithoutOrder()).thenReturn(userDtos);

        List<UserDto> returnedUsers = controller.findAllUsersWithoutOrder();
        assertEquals(returnedUsers, userDtos);
    }
}
