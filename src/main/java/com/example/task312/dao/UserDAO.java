package com.example.task312.dao;


import com.example.task312.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserDAO {
    public Set<User> getAllUser();
    public User getUserById(long id);
    public void saveUser(User user);
    public void editUser(User user);
    public void deleteUser(long id);
    public User getUserByUsername(String username);
}
