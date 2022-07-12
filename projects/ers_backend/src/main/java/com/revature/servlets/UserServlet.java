package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UserNotCreatedException;
import com.revature.models.ERSUser;
import com.revature.models.ERSUserRole;
import com.revature.services.UserService;

import java.io.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

}
