package com.mistral.java.spring.boot.assignment.service;

import com.mistral.java.spring.boot.assignment.model.User;
import com.mistral.java.spring.boot.assignment.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        User user = new User(1, "firstName", "LastName", "UserName", "Password", "email", "status", null);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThat(userRepository.save(user)).isEqualTo(user);
    }
}
