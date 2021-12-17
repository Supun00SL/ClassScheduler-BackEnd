/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websrc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import pojos.Subjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "GetBatches", urlPatterns = {"/GetBatches"})
public class GetBatches extends HttpServlet {

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
        String val = "";
        try {
            int subject = Integer.parseInt(request.getParameter("subject"));

            pojos.Subject subject1 = (pojos.Subject) session.load(pojos.Subject.class, subject);

            Set<pojos.Subjectvisebatch> subjectvisebatchs = subject1.getSubjectvisebatches();
            val += "<input type='hidden' value=" + subject + " name='subject'>";
            val += "<tr>"
                    + "                <th>Subject Vise Batch</th>"
                    + "                <th>Select</th>"
                    + "            </tr>";
            for (Subjectvisebatch subjectvisebatch : subjectvisebatchs) {
                val += "<tr>"
                        + "                <td>" + subjectvisebatch.getSubjectvisebatchcode() + "</td>"
                        + "                <td><input type='checkbox' name='" + subjectvisebatch.getIdsubjectvisebatch() + "'></td>"
                        + "            </tr>";
            }

            out.write(val);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
