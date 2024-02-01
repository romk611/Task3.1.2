package com.example.task312.service;


import com.example.task312.dao.UserDAO;
import com.example.task312.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Set<User> getAllUser() {
        return userDAO.getAllUser();
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    public User getUserByUsername(String email) {
        return userDAO.getUserByUsername(email);
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities())
                        .build();
        return userDetails;
    }

}
