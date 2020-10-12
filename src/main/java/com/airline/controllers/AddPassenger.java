package com.airline.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddPassenger", urlPatterns = {"/AddPassenger"})
public class AddPassenger extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Write directly to the view
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//        out.println("<html><body>");
//        out.println("<h3>Hello From Servlet</h3>");
//        out.println("</html></body>");

        // Forward to JSP
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add-passenger.jsp");
        view.forward(request, response);
    }
}
