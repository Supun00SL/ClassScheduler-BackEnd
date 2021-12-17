<%-- 
    Document   : CancelClass
    Created on : Sep 9, 2017, 9:08:21 PM
    Author     : Supun Madushanka
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Criteria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Cancel Sheduled Classes</title>
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
                        <h3>Cancel Classes</h3>

                    </div>
                    <hr>
                    <% if (request.getParameter("id") != null) {
                            if (request.getParameter("id").equals("1")) {
                    %>
                    <div class="alert alert-success fade in" id="status"><a href="#" class="close" data-dismiss="alert" aria-label="close" id="a">&times;</a>  
                        Successfully Cancel the Class...!
                    </div>
                    <%
                            }
                        }
                    %>
                    <table class="table table-bordered">
                        <tr>
                            <th>Subject</th>
                            <th>Batch</th>
                            <th>Lecturer</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Cancel</th>
                        </tr>
                        <%
                            Session session1 = connection.Connection.getSession();
                            Criteria classCriteria = session1.createCriteria(pojos.Normalclassshedule.class);
                            classCriteria.add(Restrictions.eq("state", "active"));
                            classCriteria.add(Restrictions.ge("date", new Date()));

                            List<pojos.Normalclassshedule> normalclassshedules = classCriteria.list();

                            if (!normalclassshedules.isEmpty()) {
                                for (pojos.Normalclassshedule normalclassshedule : normalclassshedules) {
                        %>
                        <form action="cancelclass" method="post">
                            <tr>
                            <input type="hidden" name="id" value="<%= normalclassshedule.getIdnormalclassshedule()%>">
                            <td><%= normalclassshedule.getSubjectvisebatch().getSubject().getSubject()%></td>
                            <td><%= normalclassshedule.getSubjectvisebatch().getSubjectvisebatchcode()%></td>
                            <td><%= normalclassshedule.getSubjectvisebatch().getLecturer().getFname() + " " + normalclassshedule.getSubjectvisebatch().getLecturer().getLname()%></td>
                            <td><%= new SimpleDateFormat("yyyy-MM-dd").format(normalclassshedule.getDate())%></td>
                            <td><%= new SimpleDateFormat("HH:mm:ss").format(normalclassshedule.getStarttime()) + " to " + new SimpleDateFormat("HH:mm:ss").format(normalclassshedule.getEndtime())%></td>
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
