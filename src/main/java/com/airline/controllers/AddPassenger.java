package com.airline.controllers;

import com.airline.models.Gender;
import com.airline.models.Passenger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddPassenger", urlPatterns = {"/AddPassenger"})
public class AddPassenger extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        request.setAttribute("errors", false);
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String dateOfBirth = request.getParameter("dateOfBirth").trim();
        String gender = request.getParameter("gender");
        LocalDate date = null;

        // check if these inputs are empty
        if (firstName.isEmpty()) {
            request.setAttribute("errors", true);
            request.setAttribute("firstNameError", true);
        }

        if (lastName.isEmpty()) {
            request.setAttribute("errors", true);
            request.setAttribute("lastNameError", true);
        }

        if (dateOfBirth.isEmpty()) {
            request.setAttribute("errors", true);
            request.setAttribute("dateError", true);
        } else {
            String pattern = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(dateOfBirth);
            if (m.find()) {
                String[] dateOfBirthAsArray = dateOfBirth.split("/");
                int dayOfBirth = Integer.parseInt(dateOfBirthAsArray[0].trim());
                int monthOfBirth = Integer.parseInt(dateOfBirthAsArray[1].trim());
                int yearOfBirth = Integer.parseInt(dateOfBirthAsArray[2].trim());
//                Calendar dateOfBirthAsCalendarObject = Calendar.getInstance();
//                // when the month/day values are out of bound, the calendar takes the extra to the year/month
//                // example: if user input is 31/02/2000, the object created is 02/03/2000
//                dateOfBirthAsCalendarObject.set(Calendar.YEAR, yearOfBirth);
//                dateOfBirthAsCalendarObject.set(Calendar.MONTH, monthOfBirth - 1);
//                dateOfBirthAsCalendarObject.set(Calendar.DAY_OF_MONTH, dayOfBirth);
//                Date dateOfBirthAsDateObject = dateOfBirthAsCalendarObject.getTime();
//                LocalDate date = dateOfBirthAsDateObject.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // If use input is 31/02/2000, the object created is 29/02/2000 (depending on if that year has 29/2 or only 28/2)
                date = LocalDate.parse(dateOfBirth, dateTimeFormatter);
                if (date.getMonthValue() != monthOfBirth || date.getDayOfMonth() != dayOfBirth || date.getYear() != yearOfBirth) {
                    request.setAttribute("errors", true);
                    request.setAttribute("dateValidityError", true);
                }
            } else {
                request.setAttribute("errors", true);
                request.setAttribute("dateFormatError", true);
            }
        }

        if((Boolean) request.getAttribute("errors")) {
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add-passenger.jsp");
            view.forward(request, response);
        } else {
            Passenger passenger = new Passenger();
            passenger.setFirstName(firstName);
            passenger.setLastName(lastName);
            passenger.setDateOfBirth(date);
            passenger.setGender(Gender.valueOf(gender.toUpperCase()));
            ServletContext sc = this.getServletContext();
            ArrayList<Passenger> passengers = (ArrayList<Passenger>) sc.getAttribute("passengers");
            passengers.add(passenger);
            sc.setAttribute("passengers", passengers);
            response.sendRedirect("");
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
