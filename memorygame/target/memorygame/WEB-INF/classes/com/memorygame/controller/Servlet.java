package com.memorygame.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import com.memorygame.service.FirebaseService;

public class Servlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String urlPattern = request.getServletPath();

            if ("/api".equals(urlPattern)) {
                response.setHeader("Success-Redirect", "/memorygame/game.html");
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(null);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
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

    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // String email = request.getParameter("email");

        PrintWriter out = response.getWriter();

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST);
            out.println("SignUp Failed");
            return;
        }

        if (FirebaseService.validateUserCredentials(username)) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_CONFLICT);
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