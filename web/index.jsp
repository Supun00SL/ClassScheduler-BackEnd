<%-- 
    Document   : index
    Created on : Nov 13, 2017, 12:07:02 PM
    Author     : Supun Madushanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Login</title>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid col-md-12" style="height: 20%;background-color:  #3498DB   ;">
            <h4 class="text-center" style="color: whitesmoke;"><b>C L A S S &nbsp; N O T I F I E R</b></h4>
        </div>
        <br>
        <div class="container">
            <div class="well well-sm">
                <h3>Login</h3>
            </div>
            <hr>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <% if (request.getParameter("id") != null) {
                            if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-danger alert-dismissable fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Your Username or Password is Incorrect !
                    </div>
                    <%
                            }
                        }
                    %>

                    <form action="serverlogin" method="post">
                        <div class="form-group">
                            <label>Username</label>
                            <input required type="text" name="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input required type="password" name="password" class="form-control">
                        </div>
                        <input type="submit" value="Login" class="btn btn-info">
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
