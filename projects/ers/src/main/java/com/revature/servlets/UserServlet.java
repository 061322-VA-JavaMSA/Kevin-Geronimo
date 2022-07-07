package com.revature.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>A Simple Servlet</title></head><body>");
        out.println("It worked!! "+(new java.util.Date()));
        out.println("</body></html>");
        out.close();
    }

}
