<%@page import="rescueDrive.services.user.FileServices"%>
<%@page import="rescueDrive.beans.user.FileBean"%>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            String code = request.getParameter("c");
            FileBean obj = new FileServices().getFileDetail(Integer.parseInt(code));
            pageContext.setAttribute("obj", obj);
        %>

        <form action="DownloadFile?fileName=${obj.fileName}" method="POST" enctype="multipart/form-data">
            <table align="center">
                <tr>
                    <td>
                        <input type="hidden" name="fromUser" value=""/>
                        <input type="hidden" name="fileName" value="${obj.fileName}"/>
                        <table border="1" rules='none' align="center">
                            <tr>
                                <td valign="top"><img src="download.png" alt=""  width="200" height="200"/></td>
                                <td>
                                    <strong> ${obj.fileName}</strong>
<!--                                    <p>${obj.description}</p>-->
                                    <p>&nbsp;</p>
                                    <p><input type="submit" name="sbtnDownloadNow" id="sbtnDownloadNow" value="Download Now"/></p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>