/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
            InputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            reader.close();

            JSONObject jObj = new JSONObject((Map) new JSONParser().parse(sb.toString()));

            String username = jObj.get("username").toString();
            String password = jObj.get("password").toString();

            //String username = request.getParameter("username");
            //String password = request.getParameter("password");
            System.out.println("uname :" + username);
            System.out.println("pword :" + password);

            Criteria loginCriteria = session.createCriteria(pojos.Studentlogin.class);

            loginCriteria.add(Restrictions.eq("nic", username));
            loginCriteria.add(Restrictions.eq("password", password));

            List<pojos.Studentlogin> studentlogins = loginCriteria.list();

            JSONObject jSONObject = new JSONObject();
            if (!studentlogins.isEmpty()) {
                //log wenna puluwan
                pojos.Studentlogin studentlogin = studentlogins.get(0);

                if (studentlogin.getStudent().getState().equals("active")) {
                    //out.write("1_" + studentlogin.getStudent().getIdstudent());
                    jSONObject.put("id", studentlogin.getStudent().getIdstudent());
                    jSONObject.put("fname", studentlogin.getStudent().getFname());
                    jSONObject.put("lname", studentlogin.getStudent().getLname());
                    jSONObject.put("nic", studentlogin.getStudent().getNic());
                    jSONObject.put("email", studentlogin.getStudent().getEmail());
                    jSONObject.put("mobile", studentlogin.getStudent().getMobile());
                    jSONObject.put("telephone", studentlogin.getStudent().getTelephone());
                    jSONObject.put("state", "1");
                    
                    out.write(jSONObject.toString());
                } else {
                    //out.write("2_");
                    jSONObject.put("state", "2");
                    out.write(jSONObject.toString());
                }

            } else {
                System.out.println("username or password incorrect");
                //username or password incorrect
                //out.write("0_");
                jSONObject.put("state", "0");
                out.write(jSONObject.toString());
            }

            session.flush();
            // out.write("supun");
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
