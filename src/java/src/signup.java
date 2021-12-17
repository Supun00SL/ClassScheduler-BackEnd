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
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pojos.Student;
import pojos.Studentlogin;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class signup extends HttpServlet {

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
            reader.close();

            JSONObject jObj = new JSONObject((Map) new JSONParser().parse(sb.toString()));

            String fname = jObj.get("fname").toString();
            String lname = jObj.get("lname").toString();
            String nic = jObj.get("nic").toString();
            String email = jObj.get("email").toString();
            String mobile = jObj.get("mobile").toString();
            String telephone = jObj.get("telephone").toString();
            String password = jObj.get("reenterpassword").toString();

            Criteria signupCriteria = session.createCriteria(pojos.Student.class);
            signupCriteria.add(Restrictions.eq("state", "active"));
            
            Criterion niccriterion = Restrictions.eq("nic", nic);
            Criterion emailcriterion = Restrictions.eq("email", email);

            LogicalExpression orExpression = Restrictions.or(niccriterion, emailcriterion);

            signupCriteria.add(orExpression);

            List<pojos.Student> students=signupCriteria.list();
            if(students.isEmpty()){
               //save karanna 
                pojos.Student student=new Student();
                student.setEmail(email);
                student.setFname(fname);
                student.setLname(lname);
                student.setMobile(mobile);
                student.setNic(nic);
                student.setState("inactive");
                student.setTelephone(telephone);
                session.save(student);
                
                pojos.Studentlogin studentlogin=new Studentlogin();
                studentlogin.setNic(nic);
                studentlogin.setPassword(password);
                studentlogin.setStudent(student);
                session.save(studentlogin);
                
                Transaction t=session.beginTransaction();
                t.commit();
                
                out.write("1");
                
            }else{
               //user already found
                out.write("0");
            }
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
