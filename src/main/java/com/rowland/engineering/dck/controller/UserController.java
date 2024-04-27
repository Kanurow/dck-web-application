package com.rowland.engineering.dck.controller;

import com.rowland.engineering.dck.dto.UserSummary;
import com.rowland.engineering.dck.model.User;
import com.rowland.engineering.dck.security.CurrentUser;
import com.rowland.engineering.dck.security.UserPrincipal;
import com.rowland.engineering.dck.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Gets summary data of actively logged in user"
    )
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
                currentUser.getPhoneNumber(), currentUser.getEmail());
    }

    @Operation(
            description = "Get user by Id",
            summary = "Returns user by providing user id"
    )
    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long id) {

        return userService.findUserById(id);
    }



    @Operation(
            description = "Get all registered users - user must be an admin",
            summary = "Returns all registered users - must be logged in as admin"
    )
    @GetMapping("/all-users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }



}
