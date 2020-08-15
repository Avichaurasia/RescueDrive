<%-- 
    Document   : Rename
    Created on : Oct 29, 2015, 6:42:09 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="stylesheet.jsp"/>

    </head>

    <body>
        <jsp:include page="mp_header.jsp"/>

        <form action="CreateFolder.jsp" method="GET">
            <table>
                <tr>
                    <td>
                        <strong><b> Old Name:</b></strong>

                    </td>
                    <td>
                        <input type="hidden" value="${param.folderId}" name="folderId"/>
                        <input type="text" value="${param.folderName}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>
                       <strong><b>  New Name:</b></strong>
                    </td>
                    <td>
                        <input type="text" name="folderName"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" name="sbtnRename" value="Rename">
                    </td>
                </tr>
            </table>

        </form>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>
