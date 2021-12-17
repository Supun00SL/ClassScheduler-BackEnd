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
import java.util.Set;
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
import pojos.StudentHasSubjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "cancelclass", urlPatterns = {"/cancelclass"})
public class cancelclass extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));

            pojos.Normalclassshedule normalclassshedule = (pojos.Normalclassshedule) session.load(pojos.Normalclassshedule.class, id);
            pojos.Subjectvisebatch subjectvisebatch = normalclassshedule.getSubjectvisebatch();

            Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = subjectvisebatch.getStudentHasSubjectvisebatches();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {

                pojos.Specialnotifications specialnotifications = new Specialnotifications();

                specialnotifications.setStudent(studentHasSubjectvisebatch.getStudent());
                specialnotifications.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                specialnotifications.setDescription("Your " + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubject() + ""
                        + " Class Has Been Canceled on " + dateFormat.format(normalclassshedule.getDate()) + " at "
                        + "" + timeFormat.format(normalclassshedule.getStarttime()) + " to " + timeFormat.format(normalclassshedule.getEndtime())
                        + " @ " + normalclassshedule.getLocation() + " - " + normalclassshedule.getLecturehall().getLecturehallnumber());
                specialnotifications.setHeader("Sheduled Class Has Been Canceled!");
                specialnotifications.setState("new");

                session.save(specialnotifications);

                Criteria speCriteria = session.createCriteria(pojos.Specialnotifications.class);
                speCriteria.add(Restrictions.eq("student", studentHasSubjectvisebatch.getStudent()));
                speCriteria.add(Restrictions.eq("state", "new"));

                int count=0;
                if (!speCriteria.list().isEmpty()) {
                    count = speCriteria.list().size();

                }

                pushnoti.AndroidPush.sendPushNotification("Sheduled Class Has Been Canceled!", "Your " + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubject() + ""
                        + " Class Has Been Canceled on " + dateFormat.format(normalclassshedule.getDate()) + " at "
                        + "" + timeFormat.format(normalclassshedule.getStarttime()) + " to " + timeFormat.format(normalclassshedule.getEndtime())
                        + " @ " + normalclassshedule.getLocation() + " - " + normalclassshedule.getLecturehall().getLecturehallnumber(), studentHasSubjectvisebatch.getStudent().getDevicetoken(), count);

            }
            normalclassshedule.setState("cancel");
            session.update(normalclassshedule);

            Transaction t = session.beginTransaction();
            t.commit();
            session.flush();
            response.sendRedirect("CancelClass.jsp?id=1");

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
