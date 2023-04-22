package com.draen.security.data.user.dto;

import com.draen.annotation.validation.groups.Query;
import com.draen.security.data.user.entity.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    @Null(groups = {Query.class})
    private Long id;
    private String phoneNumber;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Null(groups = {Query.class})
    private Set<UserRole> roles;
}
