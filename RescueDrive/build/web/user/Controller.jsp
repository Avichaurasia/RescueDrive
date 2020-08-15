

<%@page import="rescueDrive.properties.ReadFromPropertiesFile"%>
<%@page import="java.io.File"%>
<%@page import="rescueDrive.services.user.FileServices"%>
<%@page import="rescueDrive.services.user.FolderServices"%>
<%@page import="rescueDrive.services.user.UserServices"%>
<%@page import="rescueDrive.services.admin.AdminServices"%>

<jsp:useBean id="objBean" class="rescueDrive.beans.user.UserBean">
    <jsp:setProperty name="objBean" property="*"/>
</jsp:useBean>

<%

    UserServices oServices = new UserServices();
    FolderServices oFolderServices = new FolderServices();
    FileServices oFileServices = new FileServices();
    String result = "";
    String msg = "";
    if (request.getParameter("sbtnDelete") != null) {
        String folders[] = request.getParameterValues("chkFolder");
        String files[] = request.getParameterValues("chkFile");
        if (folders != null && folders.length > 0) {
            for (int k = 0; k < folders.length; k++) {
                File f = new File(new ReadFromPropertiesFile().getUploadPath() + File.separator + session.getAttribute("userId") + File.separator + session.getAttribute("path") + File.separator + new FolderServices().getFolderName(Integer.parseInt(folders[k]), Integer.parseInt(session.getAttribute("userId").toString())) + File.separator);
                oFolderServices.deleteFolder(f.getAbsolutePath());
                msg = "Folder Deleted Successfully";
            }
            oFolderServices.deleteFolder(folders);
        }
        if (files != null && files.length > 0) {
            msg = oFileServices.deleteFiles(files);
        }
        response.sendRedirect("ManageAllFiles.jsp?msg=" + msg);

    } else if (request.getParameter("sbtnUpdate") != null) {
        result = oServices.UpdateUserInfo(objBean);
        response.sendRedirect("Profile.jsp?msg=" + result);
    } else if (request.getParameter("isStarred") != null) {
        result = oFolderServices.changeIsStarred(Integer.parseInt(request.getParameter("folderId")), Integer.parseInt(request.getParameter("isStarred")));
        response.sendRedirect("ManageAllFiles.jsp?msg=" + result);

    } else if (request.getParameter("isStarredFiles") != null) {

        result = oFileServices.changeIsStarredFiles(Integer.parseInt(request.getParameter("fileId")), Integer.parseInt(request.getParameter("isStarredFiles")));
        response.sendRedirect("ManageAllFiles.jsp?msg=" + result);
    }

%>
