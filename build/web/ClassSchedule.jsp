<%-- 
    Document   : ClassSchedule
    Created on : Aug 10, 2017, 9:08:31 PM
    Author     : Supun Madushanka
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Class Notifier Admin Panel</title>
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
                        <h3>Shedule classes</h3>

                    </div>
                    <hr>
                    <% if (request.getParameter("id") != null) {
                            if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Successfully Scheduled Class...!
                    </div>
                    <%
                            }
                        }
                    %>

                    <div class="form-group col-md-12">
                        <div class="col-md-6">
                            <label>Select Subject :</label>
                            <select name="subject" id="subject" class="form-control">
                                <%
                                    Session session1 = connection.Connection.getSession();

                                    Criteria subjectCriteria = session1.createCriteria(pojos.Subject.class);

                                    List<pojos.Subject> subjects = subjectCriteria.list();

                                    if (!subjects.isEmpty()) {
                                        for (pojos.Subject subject : subjects) {
                                %>
                                <option value="<%=subject.getIdsubject()%>"><%=subject.getSubject()%></option>
                                <%
                                        }

                                    }
                                %>

                            </select>
                            <br>
                            <button onclick="showbatches()" class="btn btn-info col-md-offset-10">Next</button>
                            <hr>
                            <form method="post" action="sheduleclass">
                                <table id="table" class="table table-bordered">
                                    <tr>
                                        <th>Subject Vise Batch</th>
                                        <th>Select</th>
                                    </tr>

                                </table>

                        </div>
                        <div class="col-md-6">
                            <input type="hidden" id="sub" name="sub">
                            <h3>Add Class Details</h3>
                            <hr>
                            <div class="form-group">
                                <label>Date</label>
                                <input type="date" name="date" class="form-control">                           
                            </div>
                            <div class="form-group">
                                <label>Start Time</label>
                                <input type="text" name="starttime" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>End Time</label>
                                <input type="text" name="endtime" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Lecture Hall</label>
                                <select name="lecturehall" class="form-control">
                                    <%
                                        Criteria lecturehallCriteria = session1.createCriteria(pojos.Lecturehall.class);

                                        List<pojos.Lecturehall> lecturehalls = lecturehallCriteria.list();

                                        if (!lecturehalls.isEmpty()) {
                                            for (pojos.Lecturehall lecturehall : lecturehalls) {
                                    %>
                                    <option value="<%=lecturehall.getIdlecturehall()%>"><%=lecturehall.getLecturehallnumber()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Location</label>
                                <input type="text" name="location" class="form-control">
                            </div>
                            <input type="submit" value="Shedule Class" class="btn btn-success">

                            </form>
                        </div>
                    </div>
                    <br>
                    <hr>
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
    <script>
        //Creating Xhttp Request------------------------------
        var xhttp;
        function CreateXHTTPRequest() {

            if (window.XMLHttpRequest) {
                xhttp = new XMLHttpRequest();
            } else {
                xhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        //-----------------------------------------------------
        function showbatches() {
            CreateXHTTPRequest();
            var subject = document.getElementById("subject").value;

            xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 & xhttp.status === 200) {
                    document.getElementById("sub").value = subject;
                    document.getElementById("table").innerHTML = xhttp.responseText;
                }

            };
            xhttp.open("POST", "GetBatches", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("subject=" + subject);

        }

    </script>
</html>
