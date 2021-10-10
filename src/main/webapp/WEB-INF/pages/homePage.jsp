<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../static/styleHomePage.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styleHomePage.css">

    <title>Home</title>
</head>
<body>
<header>
    <div class = "header__logo">
        <p>BUG TRACKER</p>
    </div>
    <nav>
        <div class="topnav" id="myTopnav">
            <a id="createTicket" class="createTicket" href="/createTicket">CREATE TICKET</a>
            <a href="homePage">HOME</a>
            <a href="projects">PROJECTS</a>
            <a href="search">SEARCH</a>
            <a href="logout" th:text="'('+${userName}+') LOGOUT'"> </a>
            <a href="#" id="menu" class = "icon"> &#9776 </a>
        </div>
    </nav>
</header>
<main>
    <style>
        @import url(https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);

        *{
            margin: 0;
            padding: 0;
        }

        body,
        html {
            margin: 0;
            width: 100%;
            height: 100%;
            background: #8e70b4; /* fallback for old browsers */
            background: -webkit-linear-gradient(right, #8e70b4, #8aa3d1);
            background: -moz-linear-gradient(right, #8e70b4, #8aa3d1);
            background: -o-linear-gradient(right, #8e70b4, #8aa3d1);
            background: linear-gradient(to left, #8e70b4, #8aa3d1);
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;

        }

        body{
            font-family: "Open Sans", sans-serif;
        }
        a{
            text-decoration: none;
        }

        header{
            width: 100%;
            background-color: transparent;
            /*background: #455169;*/
            display: flex;
            position: fixed;
        }
        .header__logo{
            text-transform: uppercase;
            text-shadow: 1px 1px 3px black, 0 0 1em #b3b3b3;
            color: #fff;
            font-weight: 800;
            font-size: 26px;
            cursor: pointer;
            margin-top: 26px;
            margin-left: 20%
        }
        nav{
            margin-top: 26px;
            margin-left: 20%;
        }
        .topnav a{

            color: #fff;
            text-align: center;
            padding: 9px 16px;
            font-size: 14px;
            font-weight: bold;
        }

        #createTicket{
            text-shadow: 10px 10px 10px black, 0 0 1em #b3b3b3;
            color: #ffffff;
        }

        .table {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            margin-left: 100px;
            margin-right: 100px;
            margin-top: 100px;
            padding-top: 30px;
            padding-bottom: 30px;
            padding-right: 30px;
            padding-left: 30px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        .table thead th {
            font-weight: bold;
            text-align: left;
            border: none;
            padding: 10px 15px;
            background: #EDEDED;
            font-size: 14px;
            border-top: 1px solid #ddd;
        }
        .table tr th:first-child, .table tr td:first-child {
            border-left: 1px solid #ddd;
        }
        .table tr th:last-child, .table tr td:last-child {
            border-right: 1px solid #ddd;
        }
        .table thead tr th:first-child {
            border-radius: 20px 0 0 0;
        }
        .table thead tr th:last-child {
            border-radius: 0 20px 0 0;
        }
        .table tbody td {
            text-align: left;
            border: none;
            padding: 10px 15px;
            font-size: 14px;
            vertical-align: top;
        }
        .table tbody tr:nth-child(even) {
            background: #F8F8F8;
        }
        .table tbody tr:last-child td{
            border-bottom: 1px solid #ddd;
        }
        .table tbody tr:last-child td:first-child {
            border-radius: 0 0 0 20px;
        }
        .table tbody tr:last-child td:last-child {
            border-radius: 0 0 20px 0;
        }
    </style>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Priority</th>
            <th>Severity</th>
            <th>Status</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="ticket : ${tickets}">
            <td th:text="${ticket.ticketId}"></td>
            <td th:text="${ticket.name}"></td>
            <td th:text="${ticket.priority}"></td>
            <td th:text="${ticket.severity}"></td>
            <td th:text="${ticket.bugStatus}"></td>
            <td><a th:href="@{'/ticket/'+ ${ticket.ticketId}}">open</a></td>
        </tr>
        </tbody>
    </table>
</main>
</body>
<script src="scriptHomePage.js"> </script>
</html>