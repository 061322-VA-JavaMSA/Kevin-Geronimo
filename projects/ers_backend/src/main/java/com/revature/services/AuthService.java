package com.revature.services;

import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ERSUser;

public class AuthService {
    public ERSUser login(String username, String password) throws UserNotFoundException, LoginException {

        UserService userService = new UserService();
        ERSUser principal = userService.getUser(username);

        if(principal == null) {
            throw new UserNotFoundException();
        }

        if(!principal.getPassword().equals(password)){
            throw new LoginException();
        }

        return principal;
    }
}
