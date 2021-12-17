/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Models.returnanythinghere;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "drawnoti", urlPatterns = {"/drawnoti"})
public class drawnoti extends HttpServlet {

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
            
            String id = jObj.get("notiid").toString();
            
            pojos.Specialnotifications specialnotifications = (pojos.Specialnotifications) session.load(pojos.Specialnotifications.class, Integer.parseInt(id));
            
            specialnotifications.setState("read");
            
            session.update(specialnotifications);
            Transaction t = session.beginTransaction();
            t.commit();
            JSONArray jSONArray=new JSONArray();
            JSONObject jsono = new JSONObject();
            jsono.put("id", specialnotifications.getIdspecialnotifications());
            jsono.put("header", specialnotifications.getHeader());
            jsono.put("desc", specialnotifications.getDescription());
            jsono.put("state", specialnotifications.getState());
            jsono.put("time", new returnanythinghere().getdaysCount(new SimpleDateFormat("yyyy-MM-dd").format(specialnotifications.getDate()), new SimpleDateFormat("HH:mm:ss").format(specialnotifications.getDate())));
            
            jSONArray.add(jsono);
            session.flush();
            out.write(jSONArray.toString());
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
