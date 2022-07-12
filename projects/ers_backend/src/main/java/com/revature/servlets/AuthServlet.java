package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ERSUserDTO;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ERSUser;
import com.revature.services.AuthService;

public class AuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L; // ?
    private final AuthService as = new AuthService();
    private final ObjectMapper om = new ObjectMapper();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            ERSUser principal = as.login(username, password);

            HttpSession session = req.getSession();
            session.setAttribute("userId", principal.getId());
            session.setAttribute("userRole", principal.getErsUserRole().getRole());

            // To make Chrome work with our cookie
            String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
            res.setHeader("Set-Cookie", cookie);

            ERSUserDTO principalDTO = new ERSUserDTO(principal);
            try(PrintWriter pw = res.getWriter()){
                pw.write(om.writeValueAsString(principalDTO));
                res.setStatus(200);
            }

        } catch (UserNotFoundException | LoginException e) {
            res.sendError(400, "Login unsuccessful.");
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        HttpSession session = req.getSession();

        session.invalidate();
    }

}