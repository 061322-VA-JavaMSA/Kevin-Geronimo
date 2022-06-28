package com.revature.controller;

import com.revature.dao.UserPostgres;
import com.revature.view.UserView;

public class UserController {
    private UserPostgres userPostgres;
    private UserView userView;

    public UserController(UserPostgres userPostgres, UserView userView) {
        this.userPostgres = userPostgres;
        this.userView = userView;
    }

    public void updateView() {

        userView.printLoginView();
    }
}
