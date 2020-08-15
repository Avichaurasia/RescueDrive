/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.servlets.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rescueDrive.properties.ReadFromPropertiesFile;

/**
 *
 * @author Toshiba
 */
@WebServlet(name = "DownloadFile", urlPatterns = {"/DownloadFile"})
public class DownloadFile extends HttpServlet {

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
        try {
            //to download open the server startup file in bin folder in tomcat apache folder in D
                System.out.println("11111111111");
            if (request.getParameter("fileName") != null) {
                System.out.println("11111111111");
//                String filePath = new ReadFromPropertiesFile().getDownloadPath();
                String fileName = request.getParameter("fileName");
                System.out.println("fileName is:" + fileName);
                response.setHeader("content-disposition", "attachment;fileName=\"" + fileName);
                response.addHeader("content-type", "binary");
                try {
                    String compPath = "";
                    if (request.getParameter("fromUser") != null) {
                        compPath = new ReadFromPropertiesFile().getDownloadPath() + "/download/";
                        compPath = compPath.replaceAll(" ", "%20");
                    } else {
                        compPath = new ReadFromPropertiesFile().getDownloadPath() + "/" + request.getSession().getAttribute("userId") + "/" + request.getSession().getAttribute("path")
                                + "/";
                        compPath = compPath.replaceAll(" ", "%20");

                    }
                    System.out.println("ccc " + compPath);
                    fileName = fileName.replaceAll(" ", "%20");
                    URLConnection urlc = new URL(compPath + fileName).openConnection();
//                    InputStream is = new FileInputStream(new File(compPath));
                    InputStream is = urlc.getInputStream();
                    OutputStream os = response.getOutputStream();
                    int read = 0;
                    byte[] buffer = new byte[4096];
                    while ((read = is.read(buffer)) != -1) {
                        os.write(buffer, 0, read);
                    }
                    os.close();
                    is.close();
                } catch (Exception e) {
                    System.out.println("DownloadFileServlet:" + e);
                }
            }
        } catch (Exception e) {
            System.out.println("In DownloadFile:" + e);
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
