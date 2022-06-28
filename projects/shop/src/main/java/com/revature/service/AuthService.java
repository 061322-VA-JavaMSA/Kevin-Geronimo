package com.revature.service;

import com.revature.dao.UserPostgres;
import com.revature.model.User;

public class AuthService {
    private final UserPostgres userPostgres = new UserPostgres();

    public User login(String username, String password) throws Exception {
        // if username/password passed are null, throws an exception
        if (username == null || password == null) {
            throw new Exception();
        }

        User user = userPostgres.get(username);
        // if no user of that name has been retrieved/if pass don't match, throw an exception
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception();
        }
        return user;
    }

    public void register(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("Username can't be null");
        }

        if (userPostgres.get(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(User.Role.CUSTOMER);

            userPostgres.insert(user);
        } else {
            throw new Exception("Username already taken");
        }
    }
}
