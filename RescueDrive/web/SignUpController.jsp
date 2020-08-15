

<%@page import="java.io.File"%>
<%@page import="rescueDrive.properties.ReadFromPropertiesFile"%>
<%@page import="rescueDrive.beans.user.FolderBean"%>
<%@page import="rescueDrive.services.user.FolderServices"%>
<%@page import="rescueDrive.services.user.UserServices"%>
<%@page import="rescueDrive.services.admin.AdminServices"%>

<jsp:useBean id="objBean" class="rescueDrive.beans.user.UserBean">
    <jsp:setProperty name="objBean" property="*"/>
</jsp:useBean>

<%

    UserServices oServices = new UserServices();
    String result = "";

    if (request.getParameter("sbtnCountinue") != null) {
        int userId = oServices.addUser(objBean);
        FolderBean obj = new FolderBean();
        obj.setDescription("regarding home folder");
        obj.setFolderName("Home Folder");
        obj.setUserId(userId);
        new FolderServices().createFolder(obj);
        String uploadPath = new ReadFromPropertiesFile().getUploadPath();
        uploadPath = uploadPath + File.separator + userId + File.separator + "Home Folder";
        File f = new File(uploadPath);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
    response.sendRedirect("Login.jsp?msg=Sign Up Successfully");


%>
