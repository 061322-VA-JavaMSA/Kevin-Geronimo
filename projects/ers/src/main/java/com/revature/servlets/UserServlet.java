package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.UserService;

import java.io.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        ObjectMapper objectMapper = new ObjectMapper();
//        //testing - it works!
//        UserService userService = new UserService();
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.write(objectMapper.writeValueAsString(userService.getALL()));
//        printWriter.close();

    }

}
