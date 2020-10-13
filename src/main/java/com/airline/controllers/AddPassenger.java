package com.airline.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddPassenger", urlPatterns = {"/AddPassenger"})
public class AddPassenger extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        // Todo: check if these inputs are empty

        String pattern = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(dateOfBirth);
        if (m.find()) {
            String[] dateOfBirthAsArray = dateOfBirth.split("/");
            try {
                int dayOfBirth = Integer.parseInt(dateOfBirthAsArray[0].trim());
                int monthOfBirth = Integer.parseInt(dateOfBirthAsArray[1].trim());
                int yearOfBirth = Integer.parseInt(dateOfBirthAsArray[2].trim());

                //Todo: check range of day, month, year

//                Calendar dateOfBirthAsCalendarObject = Calendar.getInstance();
//                // when the month/day values are out of bound, the calendar takes the extra to the year/month
//                // example: if user input is 31/02/2000, the object created is 02/03/2000
//                dateOfBirthAsCalendarObject.set(Calendar.YEAR, yearOfBirth);
//                dateOfBirthAsCalendarObject.set(Calendar.MONTH, monthOfBirth - 1);
//                dateOfBirthAsCalendarObject.set(Calendar.DAY_OF_MONTH, dayOfBirth);
//                Date dateOfBirthAsDateObject = dateOfBirthAsCalendarObject.getTime();
//                LocalDate date = dateOfBirthAsDateObject.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(dateOfBirth, dateTimeFormatter);
                String message = "";
                if (date.getMonthValue() != monthOfBirth || date.getDayOfMonth() != dayOfBirth || date.getYear() != yearOfBirth) {
                    message = "date is not correct";
                }
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<html><body>");
                if (message.equals("")) {
                    out.println(date.toString());
                } else {
                    out.println(message);
                }
                out.println("</html></body>");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

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
