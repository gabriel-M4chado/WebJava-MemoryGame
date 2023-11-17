package com.memorygame.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import com.memorygame.service.FirebaseService;

import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends jakarta.servlet.http.HttpServlet {
    public void processRequest(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String urlPattern = request.getServletPath();

            if ("/api".equals(urlPattern)) {
                out.println("<html>");
                out.println("<head><title>Memory Game</title></head>");
                out.println("<body>");
                out.println("<h1>Welcome to the API!</h1>");
                out.println("</body>");
                out.println("</html>");
            } else if ("/login".equals(urlPattern)) {
                handleLogin(request, response);
            } else {
                out.println("Failed no pattern URL defined");
            }

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

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (loginSuccessful) {
            response.setStatus(HttpServletResponse.SC_OK); 
            response.setHeader("Success-Redirect", "/memorygame/index.jsp");
            out.println("Login Successful");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.println("Login Failed");
        }
    }
}
