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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "calenderexamdays", urlPatterns = {"/calenderexamdays"})
public class calenderexamdays extends HttpServlet {

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

            String id = jObj.get("id").toString();

            pojos.Student student = (pojos.Student) session.load(pojos.Student.class, Integer.parseInt(id));

            Calendar c = new GregorianCalendar();
            c.setTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
            System.out.println(sdf.format(c.getTime()));   // NOW

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdfthismonth = new SimpleDateFormat("yyyy-MM");
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(sdfthismonth.format(new Date()) + "-01"));
            int hiscounteka = (calendar.get(Calendar.DAY_OF_WEEK));
            int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

            JSONArray jSONArray=new JSONArray();
            for (int i = -hiscounteka; i < (monthMaxDays + 1); i++) {
                JSONObject jSONObject=new JSONObject();

                if (hiscounteka > 1) {
                    hiscounteka--;
                    jSONObject.put("day", "");
                    jSONObject.put("type", "normal");
                    jSONArray.add(jSONObject);
                } else {
                    if (i > 0) {
                        //me dawasata cls thyenawada kiyala blanawa
                        if (new returnanythinghere().ada_dawasata_exam_thyenawa(new SimpleDateFormat("yyyy-MM-dd").parse(sdfthismonth.format(new Date()) + "-" + i), student)) {
                            jSONObject.put("day", i);
                            jSONObject.put("type", "special");
                            jSONArray.add(jSONObject);
                        } else {
                            jSONObject.put("day", i);
                            jSONObject.put("type", "normal");
                            jSONArray.add(jSONObject);
                        }
                    }
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
