<%@page import="java.io.File"%>
<%@page import="rescueDrive.properties.ReadFromPropertiesFile"%>
<%@page import="rescueDrive.beans.user.ShareBean"%>
<%@page import="rescueDrive.services.user.FileServices"%>
<%@page import="rescueDrive.email.SendSMTP"%>
<%
    String mail = request.getParameter("taSharesFilewith");
    String str[] = mail.split(";");
    String ck = "";
    for (String string : str) {
        System.out.println("email:: " + string);
        ck = new SendSMTP().sendMail(string, "Click on the following link to download the file: " + request.getParameter("path"), "Download File");
    }
    System.out.println("check:: " + ck);
    ShareBean obj = new ShareBean();
    obj.setFileId(Integer.parseInt(request.getParameter("fileId")));
    obj.setPassword(request.getParameter("code"));
    obj.setSharePathCode(request.getParameter("code"));
    obj.setEmailsSharedwith(mail);
    obj.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
    new FileServices().shareFile(obj);
    File f = new File(new ReadFromPropertiesFile().getSharePath());
    if (!f.isDirectory()) {
        f.mkdirs();
    }
    new FileServices().transferFile(new ReadFromPropertiesFile().getUploadPath() + "/" + session.getAttribute("userId") + "/" + session.getAttribute("path") + "/" + request.getParameter("fileName"));

    response.sendRedirect("ManageAllFiles.jsp?msg=File Shared");
%>