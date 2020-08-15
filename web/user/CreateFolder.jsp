<%@page import="java.io.File"%>
<%@page import="rescueDrive.properties.ReadFromPropertiesFile"%>
<%@page import="rescueDrive.services.user.FolderServices"%>
<jsp:useBean id="obj" class="rescueDrive.beans.user.FolderBean">
    <jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
    String msg = "";
    if (request.getParameter("sbtnRename") != null) {
        msg = new FolderServices().renameFolder(obj);
    } else {
        obj.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        obj.setParentFolderId(Integer.parseInt(session.getAttribute("folderId").toString()));
        msg = new FolderServices().addFolder(obj);
        String uploadPath = new ReadFromPropertiesFile().getUploadPath();
        uploadPath = uploadPath + File.separator + session.getAttribute("userId") + File.separator + session.getAttribute("path") + File.separator + obj.getFolderName();
        System.out.println(uploadPath + "uploadPath::");
        File f = new File(uploadPath);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
    response.sendRedirect("ManageAllFiles.jsp?msg=" + msg);
%>