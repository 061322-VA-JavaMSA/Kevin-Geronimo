package com.revature.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserView {
    private static final Logger logger = LogManager.getLogger(UserView.class);

    public static void welcomeScreen() {
        logger.info("""
                Welcome to Random Shop!
                1) Login
                2) Register
                """);
    }

    public static void userMenuScreen(String username) {
        logger.info(String.format("""
                --------MENU--------
                Customer: %s
                                
                1) My items
                2) My Pending offers
                3) Available items
                """, username));
    }

    public void printLoginView() {

    }
}
