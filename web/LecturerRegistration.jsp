<%-- 
    Document   : LecturerRegistration
    Created on : Nov 8, 2017, 6:29:15 AM
    Author     : Supun Madushanka
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Lecturer Registration</title>
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
                        <h3>Lecturer Registration</h3>
                    </div>
                    <hr>
                    <% if (request.getParameter("id") != null) {
                            if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Successfully Registered Lecturer...!
                    </div>
                    <%
                            }
                        }
                    %>
                    <form action="SaveLecturer" method="post" class="col-md-6">
                        <div class="form-group col-md-12">
                            <label>NIC</label>
                            <input required type="text" name="nic" class="form-control">
                        </div>
                        <div class="form-group col-md-12">
                            <label>First Name</label>
                            <input required type="text" name="fname" class="form-control">
                        </div>
                        <div class="form-group col-md-12">
                            <label>Last Name</label>
                            <input required type="text" name="lname" class="form-control">
                        </div>
                        <div class="form-group col-md-12">
                            <label>Email</label>
                            <input type="text" name="email" class="form-control">
                        </div>
                        <div class="form-group col-md-12">
                            <label>Mobile</label>
                            <input required type="text" name="mobile" class="form-control">
                        </div>
                        <div class="form-group col-md-12">
                            <label>Telephone</label>
                            <input type="text" name="telephone" class="form-control">
                        </div>
                        <input type="submit" value="Save" class="btn btn-info">
                    </form>

                    <div class="col-md-6">
                        <table class="table table-bordered">
                            <tr>
                                <th>NIC</th>  
                                <th>First Name</th>  
                                <th>Last Name</th>  
                            </tr>
                            <%
                                Session session1 = connection.Connection.getSession();

                                Criteria lecturereCriteria = session1.createCriteria(pojos.Lecturer.class);
                                lecturereCriteria.add(Restrictions.eq("state", "active"));
                                List<pojos.Lecturer> lecturers = lecturereCriteria.list();
                                if (!lecturers.isEmpty()) {
                                    for (pojos.Lecturer lecturer : lecturers) {
                            %>
                            <tr>
                                <td><%= lecturer.getNic()%></td>
                                <td><%= lecturer.getFname()%></td>
                                <td><%= lecturer.getLname()%></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>
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
