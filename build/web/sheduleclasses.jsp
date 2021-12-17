<%-- 
    Document   : sheduleclasses
    Created on : Sep 6, 2017, 7:33:04 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shedule classes</h1>

        <label>Select Subject :</label>
        <select name="subject" id="subject">
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
        <button onclick="showbatches()">Next</button>
        <br>
        <hr>
        <form method="post" action="sheduleclass">
            <table>
                <tr>
                    <td>
                        <table border="1" id="table">
                            <tr>
                                <th>Subject Vise Batch</th>
                                <th>Select</th>
                            </tr>

                        </table>
                    </td>
                    <td   width="50%">
                        <h3>Add Class Details</h3>
                        <label>Date</label>
                        <input type="date" name="date"><br><br>
                        <label>Start Time</label>
                        <input type="text" name="starttime"><br><br>
                        <label>End Time</label>
                        <input type="text" name="endtime"><br><br>
                        <label>Lecture Hall</label>
                        <select name="lecturehall">
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
                        </select><br><br>

                        <label>Location</label>
                        <input type="text" name="location"><br><br>
                        <input type="submit" value="Shedule Class">
                    </td>
                <tr>
            </table>
        </form>

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

                    document.getElementById("table").innerHTML = xhttp.responseText;
                }

            };
            xhttp.open("POST", "GetBatches", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("subject=" + subject);

        }

    </script>
</html>
