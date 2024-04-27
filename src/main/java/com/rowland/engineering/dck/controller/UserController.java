package com.rowland.engineering.dck.controller;

import com.rowland.engineering.dck.dto.UserSummary;
import com.rowland.engineering.dck.security.CurrentUser;
import com.rowland.engineering.dck.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    @Operation(
            summary = "Gets summary data of actively logged in user"
    )
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
                currentUser.getPhoneNumber(), currentUser.getEmail());
    }

}
