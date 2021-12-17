/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websrc;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "serverlogin", urlPatterns = {"/serverlogin"})
public class serverlogin extends HttpServlet {

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
               

        Session session = connection.Connection.getSession();
        HttpSession hs = request.getSession();
        try {
            Criteria loginCriteria = session.createCriteria(pojos.Login.class);
            loginCriteria.add(Restrictions.eq("username", request.getParameter("username")));
            loginCriteria.add(Restrictions.eq("password", request.getParameter("password")));

            List<pojos.Login> logins = loginCriteria.list();

            if (!logins.isEmpty()) {
                pojos.Login login = logins.get(0);
                hs.setAttribute("user", login.getUsername());

                RequestDispatcher rd = request.getRequestDispatcher("ClassSchedule.jsp");
                response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                rd.forward(request, response);
                //response.sendRedirect("ClassSchedule.jsp");
   
            } else {
                response.sendRedirect("index.jsp?id=1");
            }
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
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
