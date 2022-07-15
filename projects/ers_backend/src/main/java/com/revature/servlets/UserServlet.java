package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ERSUserDTO;
import com.revature.models.ERSUser;
import com.revature.services.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

@MultipartConfig
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "application/json");

        List<ERSUser> users = userService.getUsers();
        List<ERSUserDTO> userDTOS = new ArrayList<>();

        users.forEach((user) -> userDTOS.add(new ERSUserDTO(user)));

        try (PrintWriter pw = response.getWriter()) {
            pw.write(objectMapper.writeValueAsString(userDTOS));
            response.setStatus(200);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        ERSUser user = new ERSUser();
        user.setId((Integer) session.getAttribute("userId"));
        user.setUsername(username);
        user.setPassword(password);

        userService.updateUser(user);
        response.setStatus(200);
    }
}
