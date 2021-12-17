/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websrc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojos.StudentHasSubjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "searchsvbc2", urlPatterns = {"/searchsvbc2"})
public class searchsvbc2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Session session = connection.Connection.getSession();

        String value2 = "";
        try {
            Criteria stuCriteria = session.createCriteria(pojos.Student.class);

            List<pojos.Student> students = stuCriteria.list();

            pojos.Subjectvisebatch svb1 = (pojos.Subjectvisebatch) session.load(pojos.Subjectvisebatch.class, Integer.parseInt(request.getParameter("svbc")));

            if (!students.isEmpty()) {
                value2 += " <table class=\"table table-bordered\">\n"
                        + "                                <tr>\n"
                        + "                                    <th>Student NIC</th>\n"
                        + "                                    <th>Student Name</th>\n"
                        + "                                    <th>Add This Student</th>\n"
                        + "                                </tr>\n";

                for (pojos.Student student : students) {

                    Criteria shsvbCriteria = session.createCriteria(pojos.StudentHasSubjectvisebatch.class);
                    shsvbCriteria.add(Restrictions.eq("subjectvisebatch", svb1));
                    shsvbCriteria.add(Restrictions.eq("student", student));

                    List<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = shsvbCriteria.list();
                    if (hasSubjectvisebatchs.isEmpty()) {
                        value2 += "<tr>"
                                + "<td>" + student.getNic() + "</td>"
                                + "<td>" + student.getFname() + " " + student.getLname() + "</td>"
                                + "<td><input type='checkbox' id='stu-" + student.getIdstudent() + "'/></td>"
                                + "</tr>";

                    }

                }
                value2 += "</table>";
                value2 += "<input type='button' class='btn btn-info' value='Add To Batch' onclick='adddata()'>";
            }

            out.write(value2);
            session.flush();
            //  response.sendRedirect("AssignStudentstoSubjectBatch.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
