/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.servlets.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import rescueDrive.properties.ReadFromPropertiesFile;
import rescueDrive.services.user.FileServices;
import rescueDrive.services.user.FolderServices;

/**
 *
 * @author User
 */
@MultipartConfig
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
public class UploadFile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int fileSize = 0;

        try {
            System.out.println("dfghjk");
            if (request.getParameter("sbtnUpload") != null) {

                //we make a new folder for the user if not there already
                System.out.println(new ReadFromPropertiesFile().getUploadPath());
                File f = new File(new ReadFromPropertiesFile().getUploadPath() + File.separator + request.getSession().getAttribute("userId") + File.separator + request.getSession().getAttribute("path"));

                if (!f.isDirectory()) {
                    f.mkdirs();
                }

                Part filePart = request.getPart("flFileToBeUploaded");//file is received in three parts header footer and content
                String fileName = getFileName(filePart);
                long limit = Long.parseLong(new ReadFromPropertiesFile().getLimit());
                List<Integer> lstHomeFolder = new FolderServices().countFolderSize(new ReadFromPropertiesFile().getUploadPath() + "/" + request.getSession().getAttribute("userId") + "/Home Folder");
                int sum = 0;
                for (int i = 0; i < lstHomeFolder.size(); i++) {
                    sum += lstHomeFolder.get(i);
                }
                String msg = "Limit is over. Delete some items to create space in your drive...";
                long bal = limit - sum;
                if (filePart.getSize() < bal) {
                    File f2 = new File(new ReadFromPropertiesFile().getUploadPath() + File.separator + request.getSession().getAttribute("userId") + File.separator + request.getSession().getAttribute("path") + File.separator + fileName);
                    InputStream is = null;
                    FileOutputStream fos = new FileOutputStream(f2);
                    is = filePart.getInputStream();
                    int temp = 0;
                    byte buffer[] = new byte[8056];
                    while ((temp = is.read(buffer, 0, 8056)) != -1) {
                        fos.write(buffer, 0, temp);
                    }
                    is.close();
                    fos.close();
                    FileServices obj = new FileServices();
                    fileSize = (int) f2.length();
                    System.out.println("fileSize():" + fileSize);
                    String result = obj.uploadFile(fileName, request.getParameter("taDescription"), Integer.parseInt(request.getSession().getAttribute("folderId").toString()), fileSize, Integer.parseInt(request.getSession().getAttribute("userId").toString()));
                    System.out.println(result);
                    msg = "Uploaded Successfully";
                }
                response.sendRedirect("user/ManageAllFiles.jsp?msg=" + msg);
            }
        } catch (Exception e) {
            System.out.println("upload::; " + e);
        }
    }

    public String getFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");//to get the header from filePart
        String content[] = header.split(";");//header has three contents and its splitted using semi colon
        for (int i = 0; i < content.length; i++) {
            System.out.println("value:" + content[i]);
        }//the second part contains the file name
        System.out.println("aaa " + content[2].substring(11, content[2].length() - 1));//it starts with 11th index
        return content[2].substring(11, content[2].length() - 1);//-1 to avoid the commas
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
