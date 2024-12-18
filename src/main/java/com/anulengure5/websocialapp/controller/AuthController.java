package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Repository.UserRepository;
import com.anulengure5.websocialapp.Response.AuthResponse;
import com.anulengure5.websocialapp.config.JwtProvider;
import com.anulengure5.websocialapp.models.User;
import com.anulengure5.websocialapp.Request.LoginRequest;
import com.anulengure5.websocialapp.Service.CustomUserDetailsService;
import com.anulengure5.websocialapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//extended api endpoint with /auth/***
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetails;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User exists = userRepository.findByEmail(user.getEmail());

        if (exists != null) {
            throw new Exception("Account already exist with the email");

        }


        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setGender(user.getGender());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));


        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Register Sucess");

        return res;

    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Login Sucess");

   return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customUserDetails.loadUserByUsername(email);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password for Username");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }


}
