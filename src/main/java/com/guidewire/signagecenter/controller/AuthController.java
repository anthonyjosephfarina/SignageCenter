package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.RoleEntity;
import com.guidewire.signagecenter.model.db.RoleName;
import com.guidewire.signagecenter.model.db.UserEntity;
import com.guidewire.signagecenter.model.dto.ApiResponse;
import com.guidewire.signagecenter.model.dto.JwtAuthenticationResponse;
import com.guidewire.signagecenter.model.dto.LoginRequest;
import com.guidewire.signagecenter.model.dto.SignUpRequest;
import com.guidewire.signagecenter.repository.RoleRepository;
import com.guidewire.signagecenter.repository.UserRepository;
import com.guidewire.signagecenter.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Component
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating userEntity's account
        UserEntity userEntity = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getPassword());

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // RoleEntity userRole = new RoleEntity(RoleName.ROLE_USER);
      //  userRole.setId(new Long(1));

        Optional<RoleEntity> userRole = roleRepository.findByName(RoleName.ROLE_USER);
        //.orElseThrow(() -> new AppException("UserEntity RoleEntity not set."));

        userEntity.setRoles(Collections.singleton(userRole.get()));

        UserEntity result = userRepository.save(userEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "UserEntity registered successfully"));
    }

}
