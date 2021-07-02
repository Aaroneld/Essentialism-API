package com.merrifield.Essentialism.API;

import com.merrifield.Essentialism.API.models.JoinTableModels.UserRole;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.services.RoleService;
import com.merrifield.Essentialism.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Component
public class SeedFunctionalUsers implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("aaroneld", "admin", "Aaron", "Merrifield", "merrifield48@gmail.com", "518-203-0910");
        User testUser = new User("test", "test", "John", "Doe", "email@email.com", "818-111-2121");

        admin.setRoles(Set.of(new UserRole(admin, roleService.findByName("ADMIN"))));
        testUser.setRoles(Set.of(new UserRole(testUser, roleService.findByName("USER"))));

        userService.addUser(admin);
        userService.addUser(testUser);
    }
}
