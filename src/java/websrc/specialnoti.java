/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websrc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojos.Specialnotifications;
import pojos.Student;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "specialnoti", urlPatterns = {"/specialnoti"})
public class specialnoti extends HttpServlet {

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
        try {
            String header = request.getParameter("header");
            String desc = request.getParameter("description");

            Criteria studentCriteria = session.createCriteria(pojos.Student.class);

            List<pojos.Student> students = studentCriteria.list();

            if (!students.isEmpty()) {
                for (Student student : students) {
                    pojos.Specialnotifications specialnotifications = new Specialnotifications();

                    specialnotifications.setStudent(student);
                    specialnotifications.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                    specialnotifications.setDescription(desc);
                    specialnotifications.setHeader(header);
                    specialnotifications.setState("new");

                    Criteria speCriteria = session.createCriteria(pojos.Specialnotifications.class);
                    speCriteria.add(Restrictions.eq("student", student));
                    speCriteria.add(Restrictions.eq("state", "new"));

                    int count = 0;
                    if (!speCriteria.list().isEmpty()) {
                        count = speCriteria.list().size();

                    }

                    pushnoti.AndroidPush.sendPushNotification(header, desc, student.getDevicetoken(), count);
                    session.save(specialnotifications);
                }
            }

            Transaction t = session.beginTransaction();
            t.commit();
            session.flush();
            response.sendRedirect("SendSpecialNotifications.jsp?id=1");
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
