package com.memorygame;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Memory Game</title></head>");
            out.println("<body>");

            String urlPattern = request.getServletPath();

            if ("/api".equals(urlPattern)) {
                // Logic specific to the /api URL pattern
                out.println("<h1>Welcome to the API!</h1>");
            } else if ("/custom-url".equals(urlPattern)) {
                // Logic specific to the /custom-url URL pattern
                out.println("<h1>Welcome to the Custom URL!</h1>");
            } else if ("/login".equals(urlPattern)) {
                // Logic for handling the login URL pattern
                // You might want to return a different HTML response for successful login
                out.println("<h1>Login Successful!</h1>");
            } else {
                // Default logic for other URL patterns
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

}