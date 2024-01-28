package com.java.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get or create session
        HttpSession session = request.getSession();

        // Set session attribute
        session.setAttribute("sessionAttribute", "Hello from session!");

        // Create and add a cookie with a modified value
        String cookieValue = "Cookie_Value_Without_Spaces";
        Cookie cookie = new Cookie("myCookie", cookieValue);
        response.addCookie(cookie);

        // Set response headers
        response.setHeader("Custom-Header", "Custom Header Value");

        // Send HTML response
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Session, Cookies, and Headers Demo</h2>");
        response.getWriter().println("<p>Session attribute: " + session.getAttribute("sessionAttribute") + "</p>");
        response.getWriter().println("<p>Cookie value: " + getCookieValue(request, "myCookie") + "</p>");
        response.getWriter().println("<p>Custom Header value: " + request.getHeader("Custom-Header") + "</p>");
        response.getWriter().println("</body></html>");
    }

    // Utility method to get the value of a cookie by name
    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
