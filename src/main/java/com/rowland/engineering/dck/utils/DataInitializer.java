package com.rowland.engineering.dck.utils;

import com.rowland.engineering.dck.exception.AppException;
import com.rowland.engineering.dck.model.Role;
import com.rowland.engineering.dck.model.RoleName;
import com.rowland.engineering.dck.model.User;
import com.rowland.engineering.dck.repository.RoleRepository;
import com.rowland.engineering.dck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.rowland.engineering.dck.model.RoleName.ROLE_ADMIN;
import static com.rowland.engineering.dck.model.RoleName.ROLE_USER;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = Arrays.asList(
                new Role(ROLE_USER),
                new Role(ROLE_ADMIN)
        );
        roleRepository.saveAll(roles);
        persistUsers();
    }

    private void persistUsers() {

        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("Admin Role not set."));

        User user1 = User.builder()
                .id(1L)
                .firstName("Rowland")
                .lastName("Kanu")
                .dateOfBirth(LocalDate.of(2007, 11,20))
                .email("kanurowland92@gmail.com")
                .branchChurch("Kubwa")
                .phoneNumber("08143358911")
                .password(passwordEncoder.encode("flames"))
                .build();

        User user2 = User.builder()
                .id(2L)
                .firstName("Samuel")
                .lastName("Kanu")
                .dateOfBirth(LocalDate.of(1999, 4,15))
                .email("kanurow12@gmail.com")
                .branchChurch("Kubwa")
                .phoneNumber("012345678")
                .password(passwordEncoder.encode("flames"))
                .build();

        user1.setRoles(new HashSet<>(List.of(roleUser, roleAdmin)));
        user2.setRoles(new HashSet<>(List.of(roleAdmin)));


        userRepository.saveAll(List.of(
                user1, user2));
    }
}
