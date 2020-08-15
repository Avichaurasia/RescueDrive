<%@page import="java.io.File"%>
<%@page import="rescueDrive.services.user.FolderServices"%>
<%
    if (request.getParameter("back") != null) {
        int parentFolderId = new FolderServices().getParentFolderIdByFolderId(Integer.parseInt(request.getParameter("folderId")), Integer.parseInt(session.getAttribute("userId").toString()));
        if (parentFolderId != -1) {
            session.setAttribute("folderId", parentFolderId);
            session.setAttribute("folderName", new FolderServices().getFolderName(parentFolderId, Integer.parseInt(session.getAttribute("userId").toString())));
            session.setAttribute("path", session.getAttribute("path").toString().substring(0, session.getAttribute("path").toString().lastIndexOf("/")));
        }
    } else {
        session.setAttribute("folderId", request.getParameter("folderId"));
        session.setAttribute("folderName", request.getParameter("folderName"));
        session.setAttribute("path", session.getAttribute("path") + "/" + request.getParameter("folderName"));
    }
    response.sendRedirect("ManageAllFiles.jsp");
%>
