<%-- 
    Document   : CancelExam
    Created on : Nov 10, 2017, 3:05:13 PM
    Author     : Supun Madushanka
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Cancel Scheduled Exams</title>
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
                        <!--<a href="#">-->
                        Class Notifier
                        <!--                        </a>-->
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
            <div id="page-content-wrapper">
                <div class="container-fluid col-md-12" style="height: 20%;background-color:  #3498DB   ;">
                    <div class="col-md-1"><a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle"><span class="glyphicon glyphicon-home"></span></a></div>
                    <div class="col-md-10"><h4 class="text-center" style="color: whitesmoke;"><b>C L A S S &nbsp; N O T I F I E R</b></h4></div>
                </div>
                <br>
                <div class="container">

                    <div class="well well-sm">
                        <h3>Cancel Exams</h3>

                    </div>
                    <hr>
                    <% if (request.getParameter("id") != null) {
                           if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Successfully Cancel the Exam...!
                    </div>
                    <%
                            }
                        }
                    %>
                    <table class="table table-bordered">
                        <tr>
                            <th>Subject</th>
                            <th>Batch</th>
                            <th>Type</th>
                            <th>Lecturer</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Cancel</th>
                        </tr>
                        <%
                            Session session1 = connection.Connection.getSession();
                            Criteria examsCriteria = session1.createCriteria(pojos.Examvivashedule.class);
                            examsCriteria.add(Restrictions.eq("state", "active"));
                            examsCriteria.add(Restrictions.ge("date", new Date()));

                            List<pojos.Examvivashedule> examvivashedules = examsCriteria.list();

                            if (!examvivashedules.isEmpty()) {
                                for (pojos.Examvivashedule examvivashedule : examvivashedules) {
                        %>
                        <form action="cancelexam" method="post">
                            <tr>
                            <input type="hidden" name="id" value="<%= examvivashedule.getIdexamvivashedule() %>">
                            <td><%= examvivashedule.getSubjectvisebatch().getSubject().getSubject()%></td>
                            <td><%= examvivashedule.getSubjectvisebatch().getSubjectvisebatchcode()%></td>
                            <td><%= examvivashedule.getType() %></td>
                            <td><%= examvivashedule.getSubjectvisebatch().getLecturer().getFname() + " " + examvivashedule.getSubjectvisebatch().getLecturer().getLname()%></td>
                            <td><%= new SimpleDateFormat("yyyy-MM-dd").format(examvivashedule.getDate())%></td>
                            <td><%= new SimpleDateFormat("HH:mm:ss").format(examvivashedule.getStarttime()) + " to " + new SimpleDateFormat("HH:mm:ss").format(examvivashedule.getEndtime())%></td>
                            <td><input type="submit" value="cancel" class="btn btn-danger"></td>
                            </tr>
                        </form>
                        <%
                                }
                            }
                        %>
                    </table>
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
