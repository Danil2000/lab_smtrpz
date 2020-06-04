package com.app.authentification.User;

import com.app.authentification.auth.GetDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    @RequestMapping(path="/register",  method = RequestMethod.POST)
    public @ResponseBody
    String register(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.register(user);
    }


}

