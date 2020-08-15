/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rescueDrive.beans.common.LoginBean;
import rescueDrive.services.common.CommonServices;
import rescueDrive.services.user.FolderServices;
import rescueDrive.services.user.UserServices;

/**
 *
 * @author User
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/AuthServlet"})
public class AuthServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("sbtnLogin") != null) {
                String un = request.getParameter("txtUsername");
                String pass = request.getParameter("txtPassword");
                String type = request.getParameter("ddlUserType");

                CommonServices objServices = new CommonServices();
                LoginBean obj = objServices.authUser(un, pass, type);
                if (obj != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", obj.getUserId());
                    session.setAttribute("username", obj.getUsername());
                    session.setAttribute("userType", obj.getUserType());
                    session.setAttribute("userStatus", obj.getUserStatus());
                    type = obj.getUserType().toLowerCase();
                    switch (type) {
                        case "administrator":
                            response.sendRedirect("admin/Homepage.jsp");
                            break;
                        case "user":
                            FolderServices obj1 = new FolderServices();
                            int folderId = obj1.getParentFolderId(obj.getUserId());
                            session.setAttribute("folderId", folderId);
                            session.setAttribute("folderName", "Home Folder");
                            session.setAttribute("path", "Home Folder");
                            response.sendRedirect("user/Homepage.jsp");
                            break;
                        default:
                            response.sendRedirect("Login.jsp?msg=No Match Found...");
                            break;
                    }
                } else {
                    response.sendRedirect("Login.jsp?msg=Invalid username and password");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
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
