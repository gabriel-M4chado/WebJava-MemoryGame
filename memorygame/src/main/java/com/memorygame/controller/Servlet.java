package com.memorygame.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import com.memorygame.service.FirebaseService;


public class Servlet extends jakarta.servlet.http.HttpServlet {
    public void processRequest(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Memory Game</title></head>");
            out.println("<body>");

            String urlPattern = request.getServletPath();

            if ("/api".equals(urlPattern)) {
                out.println("<h1>Welcome to the API!</h1>");
            } else if ("/custom-url".equals(urlPattern)) {
                out.println("<h1>Welcome to the Custom URL!</h1>");
            } else if ("/login".equals(urlPattern)) {
                handleLogin(request, response);
            } else {
                out.println("<h1>Welcome to Memory Game! " + urlPattern + "</h1>");
                out.println("<form method=\"post\">");
                out.println("<input type=\"submit\" name=\"action\" value=\"start game\"/>");
                out.println("</form>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(null);
        }
    }

    @Override
    public void doPost(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(null);
        }
    }

    private void handleLogin(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws IOException {
                
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginSuccessful = FirebaseService.validateUserCredentials(username, password);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login Result</title></head>");
        out.println("<body>");

        if (loginSuccessful) {
            out.println("<h1>Login Successful!</h1>");
        } else {
            out.println("<h1>Login Failed!</h1>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}