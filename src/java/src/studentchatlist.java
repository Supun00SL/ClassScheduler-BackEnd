/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import pojos.Chatlist;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "studentchatlist", urlPatterns = {"/studentchatlist"})
public class studentchatlist extends HttpServlet {

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
            HashMap hm = new HashMap();
            String val = "";
            int chatcount=0;
            String id = request.getParameter("p");

            pojos.Student student = (pojos.Student) session.load(pojos.Student.class, Integer.parseInt(id));

            Criteria chatlistCriteria = session.createCriteria(pojos.Chatlist.class);
            chatlistCriteria.add(Restrictions.eq("student", student));
            chatlistCriteria.add(Restrictions.eq("state", "active"));
            chatlistCriteria.addOrder(Order.desc("lastmsgtime"));
            List<pojos.Chatlist> chatlists = chatlistCriteria.list();

            if (!chatlists.isEmpty()) {
                for (Chatlist chatlist : chatlists) {
                    chatcount++;
                    val += "<li>"
                            + "	<a href='#' class='item-link item-content'>"
                            + "     <div class='item-media'><i class='icon f7-icons'>person_fill</i></div>"
                            + "		<div class='item-inner'>"
                            + "             <div class='item-title-row'>"
                            + "                 <div class='item-title'>" + chatlist.getLecturer().getFname() + " " + chatlist.getLecturer().getLname() + "</div>"
                            + "                 <div class='item-after'>";
                    if (chatlist.getMsgcount() != 0) {
                        val += "                     <span class='badge bg-green'>" + chatlist.getMsgcount() + "</span>";
                    }
                    val += "                 </div>"
                            + "             </div>"
                            + "		<div class='item-text'>"+chatlist.getLastmsgtext()+"</div>"
                            + "     </div>"
                            + " </a>"
                            + "</li>";
                }

            }
            hm.put("value", val);
            hm.put("chatcount", chatcount);
            
            JSONObject json = new JSONObject();
            json.put("chatlist", hm);
            out.print(json);
            out.flush();
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
