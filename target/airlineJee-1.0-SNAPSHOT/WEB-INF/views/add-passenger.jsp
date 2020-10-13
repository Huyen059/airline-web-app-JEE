<%--
  Created by IntelliJ IDEA.
  User: becode
  Date: 10/11/20
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Airline</title>
</head>
<body>
<div class="container">
    <h3>Add-passenger JSP</h3>

    <form action="AddPassenger" method="post">
        <fieldset>
            <legend>Passenger details</legend>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName">
            </div>
            <% if (request.getAttribute("firstNameError") != null) { %>
            <div class="alert alert-danger" role="alert">
                Firstname is required!
            </div>
            <% } %>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName">
            </div>
            <% if (request.getAttribute("lastNameError") != null) { %>
            <div class="alert alert-danger" role="alert">
                Lastname is required!
            </div>
            <% } %>
            <div class="form-group">
                <label for="dateOfBirth">Date of birth (dd/mm/YYYY)</label>
                <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth">
            </div>
            <% if (request.getAttribute("dateError") != null) { %>
            <div class="alert alert-danger" role="alert">
                Date is required!
            </div>
            <% } %>
            <% if (request.getAttribute("dateFormatError") != null) { %>
            <div class="alert alert-danger" role="alert">
                Incorrect format!
            </div>
            <% } %>
            <% if (request.getAttribute("dateValidityError") != null) { %>
            <div class="alert alert-danger" role="alert">
                Date is not valid!
            </div>
            <% } %>
            <div class="form-group">
                <label for="gender">Gender</label>
                <select class="form-control" id="gender" name="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary">Add new passenger</button>
    </form>
</div>
</body>
</html>
