<%-- 
    Document   : Controller1
    Created on : Oct 9, 2015, 6:29:32 PM
    Author     : User
--%>
<%@page import="rescueDrive.services.admin.AdminServices"%>

<jsp:useBean id="objBean" class="rescueDrive.beans.user.UserBean">
    <jsp:setProperty name="objBean" property="*"/>
</jsp:useBean>

<%

    AdminServices oServices = new AdminServices();
    String result = "";

    if (request.getParameter("sbtnUpdate") != null) {
        result = oServices.UpdateAdminInfo(objBean);
        response.sendRedirect("Profile.jsp?msg=" + result);
    } else if (request.getParameter("btnUpdate") != null) {
        result = oServices.UpadteUserStatus(Integer.parseInt(request.getParameter("userId")), request.getParameter("rbStatus"));
        response.sendRedirect("ManageUsers.jsp?msg=" + result);
    } else  {
        result = oServices.changeUserStatus(Integer.parseInt(request.getParameter("userId")), request.getParameter("status"));
        response.sendRedirect("ManageUsers.jsp?msg=" + result);
    }

%>
