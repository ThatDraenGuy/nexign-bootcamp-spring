package com.draen.security.controller;

import com.draen.annotation.validation.groups.Create;
import com.draen.annotation.validation.groups.Query;
import com.draen.security.data.user.dto.UserDto;
import com.draen.security.data.user.entity.User;
import com.draen.security.data.user.service.UserService;
import com.draen.security.jwt.JwtUtils;
import com.draen.service.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final Mapper<User, UserDto> userMapper;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService,
                          Mapper<User, UserDto> userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Validated({Query.class}) UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);

            ResponseCookie cookie = ResponseCookie
                    .from("token", token)
                    .path("/auth")
                    .maxAge(24 * 60 * 60)
                    .httpOnly(true)
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(userMapper.toDto(userService.findByUsername(userDto.getUsername())));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Validated({Create.class}) UserDto userDto) {
        try {
            return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(userDto))));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
