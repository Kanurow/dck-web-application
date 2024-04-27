package com.rowland.engineering.dck.utils;

import com.rowland.engineering.dck.model.Role;
import com.rowland.engineering.dck.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.rowland.engineering.dck.model.RoleName.ROLE_ADMIN;
import static com.rowland.engineering.dck.model.RoleName.ROLE_USER;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = Arrays.asList(
                new Role(ROLE_USER),
                new Role(ROLE_ADMIN)
        );
        roleRepository.saveAll(roles);
    }
}
