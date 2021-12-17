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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pojos.Normalclassshedule;
import pojos.StudentHasSubjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "popover", urlPatterns = {"/popover"})
public class popover extends HttpServlet {

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

            HashMap iddata = (HashMap) jObj.get("id");
            HashMap daydata = (HashMap) jObj.get("day");

            System.out.println("id :" + iddata.get("id"));
            System.out.println("day :" + daydata.get("day"));

            String id = iddata.get("id").toString();
            String day = "-" + daydata.get("day").toString();

            SimpleDateFormat sdfthismonth = new SimpleDateFormat("yyyy-MM");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sdfthismonth.format(new Date()) + day);

            pojos.Student student = (pojos.Student) session.load(pojos.Student.class, Integer.parseInt(id));

            Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = student.getStudentHasSubjectvisebatches();

            JSONArray jSONArray = new JSONArray();
            for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
                Criteria normalclassCriteria = session.createCriteria(pojos.Normalclassshedule.class);
                normalclassCriteria.add(Restrictions.eq("subjectvisebatch", studentHasSubjectvisebatch.getSubjectvisebatch()));
                normalclassCriteria.add(Restrictions.eq("date", date));
                normalclassCriteria.add(Restrictions.eq("state", "active"));
                normalclassCriteria.addOrder(Order.asc("starttime"));

                List<pojos.Normalclassshedule> normalclassshedules = normalclassCriteria.list();

                JSONArray jSONArray1 = new JSONArray();
                if (!normalclassshedules.isEmpty()) {
                    for (Normalclassshedule normalclassshedule : normalclassshedules) {
                        JSONObject jSONObject = new JSONObject();

                        jSONObject.put("title", studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubject());
                        jSONObject.put("code", studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubjectcode());
                        jSONObject.put("time", " Start :" + new SimpleDateFormat("HH:mm").format(normalclassshedule.getStarttime()) + " End :" + new SimpleDateFormat("HH:mm").format(normalclassshedule.getEndtime()));
                        jSONObject.put("lecturer", studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getFname() + " " + studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getLname());
                        jSONObject.put("location", normalclassshedule.getLocation());
                        jSONObject.put("lecturehall", normalclassshedule.getLecturehall().getLecturehallnumber());

                        jSONArray1.add(jSONObject);
                    }
                    jSONArray.add(jSONArray1);
                }
            }
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
