package com.draen.security.data.user.dto;

import com.draen.annotation.validation.groups.Create;
import com.draen.annotation.validation.groups.Query;
import com.draen.security.data.user.entity.UserRole;
import jakarta.annotation.Nullable;
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
    @Null(groups = {Create.class, Query.class})
    private Long id;
    @Null(groups = {Query.class})
    private String phoneNumber;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Null(groups = {Create.class, Query.class})
    private Set<UserRole> roles;
}
