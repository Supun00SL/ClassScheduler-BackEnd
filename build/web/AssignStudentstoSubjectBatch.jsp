<%-- 
    Document   : AssignStudentstoSubjectBatch
    Created on : Nov 10, 2017, 10:27:49 AM
    Author     : Supun Madushanka
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Criteria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Assign Students to Subject vise Batch</title>
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
                        <h3>Assign Students to Subject vise Batch</h3>
                    </div>
                    <hr>
                    <div class="col-md-6">

                        <div class="form-group">
                            <label>Subject vise Batch Code</label>
                            <select class="form-control" name="svbc" id="svbc" onchange="showdetails(), showdetails2()">
                                <%
                                    Session session1 = connection.Connection.getSession();
                                    Criteria svbcCriteria = session1.createCriteria(pojos.Subjectvisebatch.class);

                                    List<pojos.Subjectvisebatch> subjectvisebatchs = svbcCriteria.list();

                                    if (!subjectvisebatchs.isEmpty()) {
                                        for (pojos.Subjectvisebatch subjectvisebatch : subjectvisebatchs) {
                                %>
                                <option value="<%= subjectvisebatch.getIdsubjectvisebatch()%>"><%= subjectvisebatch.getSubject().getSubject() +"-"+ subjectvisebatch.getSubjectvisebatchcode()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>

                    </div>
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>Registered Students For the Batch</h4>
                            <div id="table">                                
                            </div>

                        </div>
                        <div class="col-md-6">
                            <h4>Register new Students For the Batch</h4>
                            <div id="addtable">                                
                            </div>

                        </div>
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
        function showdetails() {
            CreateXHTTPRequest();
            var svbc = document.getElementById("svbc").value;

            xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 & xhttp.status === 200) {
//                    document.getElementById("sub").value = subject;

                    document.getElementById("table").innerHTML = xhttp.responseText;
                }

            };
            xhttp.open("POST", "searchsvbc", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("svbc=" + svbc);

        }
        //-----------------------------------------------------
        function showdetails2() {
            CreateXHTTPRequest();
            var svbc = document.getElementById("svbc").value;

            xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 & xhttp.status === 200) {
//                    document.getElementById("sub").value = subject;

                    document.getElementById("addtable").innerHTML = xhttp.responseText;
                }

            };
            xhttp.open("POST", "searchsvbc2", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("svbc=" + svbc);

        }
        //-----------------------------------------------------
        function adddata() {
            CreateXHTTPRequest();
            var svbc = document.getElementById("svbc").value;

            var list = document.getElementsByTagName("input");

            var arr = [];
            var a = 0;
            for (var i = 0; i < list.length; i++) {
                if (document.getElementsByTagName("input")[i].id.split('-')[0] === "stu") {

                    if (document.getElementById(document.getElementsByTagName("input")[i].id).checked) {

                        arr[a] = document.getElementsByTagName("input")[i].id.split('-')[1];
                        a++;
                    }

                }

            }
            // alert(arr);

            xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 & xhttp.status === 200) {
                    alert("Successfully Saved!");
                    showdetails();
                    showdetails2();

                }

            };
            xhttp.open("POST", "SaveToBatch", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("svbc=" + svbc + "&arr=" + arr);

        }

        function test() {
            var list = document.getElementsByTagName("input");

            var arr = [];
            var a = 0;
            for (var i = 0; i < list.length; i++) {
                if (document.getElementsByTagName("input")[i].id.split('-')[0] === "stu") {

                    if (document.getElementById(document.getElementsByTagName("input")[i].id).checked) {
                        //alert("in");
                        arr[a] = document.getElementsByTagName("input")[i].id.split('-')[1];
                        a++;
                    }

                }

            }
            alert(arr);


        }

    </script>

</html>
