<%-- 
    Document   : ManageSubjectViseBatches
    Created on : Nov 10, 2017, 7:44:31 AM
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

        <title>Manage Subject Vise Batches</title>
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
                        <h3>Manage Subject Vise Batches</h3>
                    </div>
                    <hr>

                    <div class="col-md-6">
                        <h3>Add Subject Vise Batch</h3>
                        <hr>
                        <% if (request.getParameter("id") != null) {
                                if (request.getParameter("id").equals("1")) {
                        %>
                        <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                            Successfully Added Subject vise batch...!
                        </div>
                        <%
                                }
                            }
                        %>
                        <form action="AddSubjectViseBatch" method="post">
                            <div class="form-group">
                                <label>Lecturer</label>
                                <select  name="lecturer" id="lecturer" class="form-control">
                                    <%
                                        Session session1 = connection.Connection.getSession();
                                        Criteria lectCriteria = session1.createCriteria(pojos.Lecturer.class);
                                        lectCriteria.add(Restrictions.eq("state", "active"));
                                        List<pojos.Lecturer> lecturers = lectCriteria.list();

                                        if (!lecturers.isEmpty()) {
                                            for (pojos.Lecturer lecturer : lecturers) {
                                    %>
                                    <option value="<%= lecturer.getIdlecturer()%>"><%= lecturer.getFname() + " " + lecturer.getLname()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Subject</label>
                                <select  name="subject" id="subject" class="form-control">
                                    <%
                                        Criteria subjCriteria = session1.createCriteria(pojos.Subject.class);
                                        subjCriteria.add(Restrictions.eq("state", "active"));
                                        List<pojos.Subject> subjects = subjCriteria.list();

                                        if (!subjects.isEmpty()) {
                                            for (pojos.Subject subject : subjects) {
                                    %>
                                    <option value="<%= subject.getIdsubject()%>"><%= subject.getSubjectcode() + "-" + subject.getSubject()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Subject Vise Batch Code</label>
                                <input type="text" name="svbc" class="form-control">
                            </div>
                            <input type="submit" value="save" class="btn btn-info">
                        </form>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-bordered">
                            <tr>
                                <th>Subject Vise Batch</th>
                                <th>Subject</th>
                                <th>Lecturer</th>
                            </tr>
                            <%
                                Criteria svbcCriteria = session1.createCriteria(pojos.Subjectvisebatch.class);

                                List<pojos.Subjectvisebatch> subjectvisebatchs = svbcCriteria.list();

                                if (!subjectvisebatchs.isEmpty()) {
                                    for (pojos.Subjectvisebatch subjectvisebatch : subjectvisebatchs) {
                            %>
                            <tr>
                                <td><%= subjectvisebatch.getSubjectvisebatchcode()%></td>
                                <td><%= subjectvisebatch.getSubject().getSubject()%></td>
                                <td><%= subjectvisebatch.getLecturer().getFname() + " " + subjectvisebatch.getLecturer().getLname()%></td>
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
