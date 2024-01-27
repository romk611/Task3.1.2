package com.example.spring_security.configs;

import com.example.spring_security.model.Role;
import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        List<Role> adminList = new ArrayList<>();
        adminList.add(admin);

        List<Role> userList = new ArrayList<>();
        userList.add(user);

        User roman = new User("admin", "$2a$12$hTNiNebrp0HyvN2XadFBZeuJTD1alVx5lROfzgj195uyJHx6gjnme", "Roman", "Shamsiev", (byte) 18, "roman@gmail.com", adminList);
        User dima = new User("user", "$2a$12$LVBDasZuqTqwvOgKPenoyeKHIJSqZyWQP76XuHBWkxzpO3G450gh6", "Dima", "Petrov", (byte) 23, "dima@gmail.com", userList);

        userService.saveUser(roman);
        userService.saveUser(dima);

    }
}
