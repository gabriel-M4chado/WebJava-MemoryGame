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
                response.sendRedirect("/memorygame/game.html");
            } else if ("/login".equals(urlPattern)) {
                handleLogin(request, response);
            } else if ("/signup".equals(urlPattern)) {
                handleSignUp(request, response);
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

    private void handleSignUp(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("SignUp Failed");
            return;
        }

        if (FirebaseService.validateUserCredentials(username, password)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            out.println("O nome já existe");
            return;
        }

        boolean registrationSuccessful = FirebaseService.registerUser(username, password);

        response.setContentType("text/plain;charset=UTF-8");

        if (registrationSuccessful) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_CREATED); // 201 Created
            response.setHeader("Success-Redirect", "/memorygame/index.jsp");
            out.println("Registration Successful");
        } else {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal
                                                                                                   // Server Error
            out.println("Registration Failed");
        }
    }
}
