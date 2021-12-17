<%-- 
    Document   : SendSpecialNotifications
    Created on : Sep 9, 2017, 7:59:57 PM
    Author     : Supun Madushanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Special Notifications</title>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <div id="wrapper">
            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand" style="color: #3498DB;">
                        Class Notifier
                    </li>
                    <li>
                        <a href="ClassSchedule.jsp">Schedule Classes</a>
                    </li>
                    <li>
                        <a href="CancelClass.jsp">Cancel Classes</a>
                    </li>
                    <li>
                        <a href="ExamSchedule.jsp">Schedule Exam</a>
                    </li>
                    <li>
                        <a href="CancelExam.jsp">Cancel Exam</a>
                    </li>
                    <li>
                        <a href="SendSpecialNotifications.jsp">Send Special Notifications</a>
                    </li>
                    <li>
                        <a href="ActivateStudents.jsp">Activate Students</a>
                    </li>
                    <li>
                        <a href="SubjectRegistrations.jsp">Subject Registration</a>
                    </li>
                    <li>
                        <a href="LecturerRegistration.jsp">Lecturer Registration</a>
                    </li>
                    <li>
                        <a href="AssignStudentstoSubjectBatch.jsp">Assign Student to Batch</a>
                    </li>
                    <li>
                        <a href="ManageSubjectViseBatches.jsp">Subject wise Batch Register</a>
                    </li>
                </ul>
            </div>
            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid col-md-12" style="height: 20%;background-color:  #3498DB   ;">
                    <div class="col-md-1"><a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle"><span class="glyphicon glyphicon-home"></span></a></div>
                    <div class="col-md-10"><h4 class="text-center" style="color: whitesmoke;"><b>C L A S S &nbsp; N O T I F I E R</b></h4></div>
                </div>
                <br>
                <div class="container">

                    <div class="well well-sm">
                        <h3>Send Special Notifications </h3>

                    </div>
                    <hr>

                    <% if (request.getParameter("id") != null) {
                            if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Successfully Send Special Notifications...!
                    </div>
                    <%
                            }
                        }
                    %>
                    <form action="specialnoti" method="post" class="col-md-6">
                        <div class="form-group col-md-12">
                            <label>Header :</label>
                            <input required type="text" name="header" class="form-control">              
                        </div>
                        <div class="form-group col-md-12">
                            <label>Description :</label>
                            <textarea required name="description" class="form-control"></textarea>
                        </div>
                        <input type="submit" value="send" class="btn btn-info">
                    </form>
                </div>
            </div>
        </div>
        <script>
            $("#menu-toggle").click(function(e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>
    </body>
</html>
