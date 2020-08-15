<%-- 
    Document   : newFolder
    Created on : Oct 29, 2015, 6:21:59 AM
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
                       
                        <strong><b> Folder Name:</b></strong>    
                    </td>
                    <td>
                        <input type="text" name="folderName" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong><b> Description:</b></strong>
                    </td>
                    <td>
                        <textarea name="description"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <input type="submit" name="sbtnCreateFolder" value="Create Folder" class="button"/>
                    </td>
                </tr>
            </table>
           
        </form>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>
