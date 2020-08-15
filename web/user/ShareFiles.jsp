<%@page import="rescueDrive.services.user.FolderServices"%>
<html>
    <head>
        <title>Share Files</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <%
            String code = new FolderServices().generateCode();
            pageContext.setAttribute("code", code);
        %>
        <jsp:include page="mp_header.jsp"/>
        <form action="ShareController.jsp" method="Post">
            <table>
                <tr>
                    <td>
                        <fieldset><legend><strong><em>Share Files</em></strong></legend>
                            <table>
                                <tr>
                                    <td><strong>File Name</strong></td>
                                    <td>${param.fileName}</td>			
                                </tr>	
                                <tr>
                                    <td><strong>Secure Link</strong></td>
                                    <td><span>http://localhost:8084/RescueDrive/ViewAndDownloadSharedFiles.jsp?c=${code}</span></td>			
                                <input type="hidden" name="fileId" value="${param.fileId}"/>
                                <input type="hidden" name="code" value="${code}"/>
                                <input type="hidden" name="fileName" value="${param.fileName}"/>
                                <input type="hidden" name="path" value="http://localhost:8084/RescueDrive/ViewAndDownloadSharedFiles.jsp?c=${code}"/>
                                </tr>
                                <tr>
                                    <td colspan="2"><strong>Share Files with</strong></td>

                                </tr>	
                                <tr>
                                    <td colspan="2"><textarea id="taSharesFilewith" name="taSharesFilewith" rows="5"  cols="50"></textarea></td>
                                </tr>

                                <tr>

                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" name="sbtnSend" value="Send"> <input type="button" name="sbtnCancel" value="Cancel"></td>			
                                </tr>	
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
