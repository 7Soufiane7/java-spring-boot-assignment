package com.mistral.java.spring.boot.assignment.repository;

import com.mistral.java.spring.boot.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findAllByOrderByFirstNameAsc();
    public List<User> findAllByOrderByFirstNameDesc();
    public List<User> findAllByOrderByLastNameAsc();
    public List<User> findAllByOrderByLastNameDesc();
    public List<User> findAllByOrderByUserNameAsc();
    public List<User> findAllByOrderByUserNameDesc();
    public List<User> findAllByOrderByEmailAsc();
    public List<User> findAllByOrderByEmailDesc();
    public List<User> findAllByFirstName(String firstName);
    public List<User> findAllByLastName(String LastName);
    public List<User> findAllByUserName(String userName);
    public List<User> findAllByEmail(String email);
    public List<User> findAllByStatus(String status);

}
