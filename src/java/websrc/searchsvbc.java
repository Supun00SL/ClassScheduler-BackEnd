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
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojos.StudentHasSubjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "searchsvbc", urlPatterns = {"/searchsvbc"})
public class searchsvbc extends HttpServlet {

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
        String value = "";

        try {
            Criteria svbCriteria = session.createCriteria(pojos.StudentHasSubjectvisebatch.class);

            svbCriteria.add(Restrictions.eq("subjectvisebatch", (pojos.Subjectvisebatch) session.load(pojos.Subjectvisebatch.class, Integer.parseInt(request.getParameter("svbc")))));

            List<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = svbCriteria.list();

            pojos.Subjectvisebatch svb = (pojos.Subjectvisebatch) session.load(pojos.Subjectvisebatch.class, Integer.parseInt(request.getParameter("svbc")));
            if (!hasSubjectvisebatchs.isEmpty()) {
                value += " <table class=\"table table-bordered\">\n"
                        + "                                <tr>\n"
                        + "                                    <th>Student NIC</th>\n"
                        + "                                    <th>Student Name</th>\n"
                        + "                                </tr>\n";

                for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
                    value += "<tr>"
                            + "<td>" + studentHasSubjectvisebatch.getStudent().getNic() + "</td>"
                            + "<td>" + studentHasSubjectvisebatch.getStudent().getFname() + " " + studentHasSubjectvisebatch.getStudent().getLname() + "</td>"
                            + "</tr>";

                }
                value += "</table>";
            }

            out.write(value);
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
