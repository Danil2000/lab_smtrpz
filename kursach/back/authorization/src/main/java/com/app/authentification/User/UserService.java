package com.app.authentification.User;
import com.app.authentification.ExceptionHandler;
import com.app.authentification.Roles;
import com.app.authentification.auth.TokenProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Bean
    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
    private PasswordEncoder passwordEncoder = getEncoder();

    @Autowired
    private TokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String userInfo(User user, String token) {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("token", token);
        json.add("user", gson.toJsonTree(user));
        return json.toString();
    }

    public String login(User u) {
        String username = u.getEmail();
        String password = u.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user =  userRepository.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, user.getRole());
            return userInfo(user, token);
        } catch (AuthenticationException e) {
            throw new ExceptionHandler("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String register(User user) {
        if (!isUser(user.getEmail(), userRepository.findAll())) {
            if(user.getEmail().equals("admin@mail.com") &&
                    user.getName().equals("admin") &&
                    user.getPassword().equals("admin")) {
                List<Roles> role = new ArrayList<Roles>() ;
                user.setRole(Roles.ADMIN);
            } else {
                System.out.println("CLIENT");
                user.setRole(Roles.CLIENT);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return userInfo(user, jwtTokenProvider.createToken(user.getEmail(), user.getRole()));
        } else {
            throw new ExceptionHandler("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private boolean isUser(
            String email, Iterable<User> users) {

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
