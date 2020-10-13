<%@ page import="java.util.ArrayList" %>
<%@ page import="com.airline.models.Passenger" %><%--
  Created by IntelliJ IDEA.
  User: becode
  Date: 10/13/20
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>This is home page</h1>
<h2>Passengers</h2>
<%
    ServletContext sc = request.getServletContext();
    ArrayList<Passenger> passengers = (ArrayList<Passenger>) sc.getAttribute("passengers");
    if (passengers.size() != 0) {
        for (Passenger passenger : passengers) {
            out.println(passenger.getFirstName() + " " + passenger.getLastName() + "<br>");
        }
    } else {
        out.println("No passenger yet.");
    }
%>
</body>
</html>
