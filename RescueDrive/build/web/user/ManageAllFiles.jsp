<%@page import="rescueDrive.services.user.FileServices"%>
<%@page import="rescueDrive.beans.user.FileBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.user.FolderServices"%>
<%@page import="rescueDrive.beans.admin.UserBean"%>
<%@page import="rescueDrive.beans.user.FolderBean"%>
<%@page import="rescueDrive.services.user.UserServices"%>
<html>
    <head>
        <title>Manage All Files</title>
        <jsp:include page="stylesheet.jsp"/>
        <!--        <script type=text/javascript>
                    function checkFolders()
                    {
                        var chkHead = document.getElementsByName("chkFolder");
        
                        var arr = new int[20];
                        var j = 0;
        
                        for (var i = 0; i < chkHead.length; i++)
                        {
                            if (chkHead[i].checked)
                            {
                                arr[j] = i;
        
                                j++;
                            }
        
                        }
                    }
                </script>-->
    </head>

    <body>
        <jsp:include page="mp_header.jsp"/>
        <form action="Controller.jsp" method="post">
            <%
                List<FolderBean> lstFolders = null;
                List<FileBean> lstFiles = null;

                if (request.getParameter("star") != null) {
                    lstFolders = new FolderServices().getAllFolders(Integer.parseInt(session.getAttribute("userId").toString()), Integer.parseInt(session.getAttribute("folderId").toString()), 1);
                    lstFiles = new FileServices().getAllFiles(Integer.parseInt(session.getAttribute("folderId").toString()), Integer.parseInt(session.getAttribute("userId").toString()), 1);
                } else {
                    lstFolders = new FolderServices().getAllFolders(Integer.parseInt(session.getAttribute("userId").toString()), Integer.parseInt(session.getAttribute("folderId").toString()), 0);
                    lstFiles = new FileServices().getAllFiles(Integer.parseInt(session.getAttribute("folderId").toString()), Integer.parseInt(session.getAttribute("userId").toString()), 0);
                }
                pageContext.setAttribute("LIST_FOLDERS", lstFolders);
                System.out.println("sdfdsf " + lstFolders.size());

                pageContext.setAttribute("LIST_FILES", lstFiles);
            %>
            <table align="center" width="70%">
                <tr>
                <div style="color: red;">
                    ${sessionScope.path}
                </div>
                <td>
                    <fieldset>
                        <table width="100%">
                            <tr>
                                <td><img src="images/folder_open.png" width="50" height="50"></td>
                                <td style="font-size:20px">${sessionScope.folderName}</td>
                                <td>
                                    <a href="Navigation.jsp?folderId=${sessionScope.folderId}&back">Back</a>
                                </td>
                                <td align=right>



                                </td>	

                                <td align=right>
                                    <input type="submit" name="sbtnUpload" value="Upload" class="button">

                                    <input type="button" name="sbtnNewFolder"  id="sbtnNewFolder" value="New Folder" class="button" onclick="window.location = 'newFolder.jsp'"> 
                                </td>	
                            </tr>
                        </table>
                    </fieldset>
                </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <table width="100%">
                                <tr>
                                    <td align="right">
                                        <c:choose>
                                            <c:when test="${param.star!=null}">
                                                <a href="ManageAllFiles.jsp?showAll">Show All</a> &nbsp;&nbsp;|&nbsp;&nbsp
                                            </c:when>
                                            <c:otherwise>
                                                <a href="ManageAllFiles.jsp?star">Show Stared</a> &nbsp;&nbsp;|&nbsp;&nbsp
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="submit"  name="sbtnDelete" id="sbtnDelete" value="Delete">
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>			
                            <table width="100%">
                                <c:forEach items="${LIST_FOLDERS}" var="obj">

                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td><img src="images/folder.png"></td>
                                                    <td><a href="Navigation.jsp?folderId=${obj.folderId}&folderName=${obj.folderName}">${obj.folderName}</a>
                                                        <br/>Created on: ${obj.creationDate}
                                                    </td>

                                                </tr>
                                            </table>
                                        </td>
                                        <td align="right">
                                            <table>
                                                <tr> 
                                                    <td><a href="Rename.jsp?folderName=${obj.folderName}&folderId=${obj.folderId}">Rename</a></td>
                                                    <c:choose>
                                                        <c:when test="${obj.isStarred == 1}">
                                                            <td><a href="Controller.jsp?folderId=${obj.folderId}&isStarred=${obj.isStarred}"><img src="images/star_on.jpg" width="25" height="20"></a></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                            <td><a href="Controller.jsp?folderId=${obj.folderId}&isStarred=${obj.isStarred}"><img src="images/star_off.jpg" width="25" height="20"></a></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                    <td><input type="checkbox" name="chkFolder" id="chkFolder0" value="${obj.folderId}" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:forEach items="${LIST_FILES}" var="obj1">


                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td><img src="images/pdf.png" width="35" height="35"></td>
                                                    <td><a href="#">${obj1.fileName}</a><br>Created On:${obj1.creationDate} Size:${obj1.fileSize} kb</td>

                                                </tr>
                                            </table>
                                        </td>
                                        <td align="right">
                                            <table>
                                                <tr>
                                                    <td><a href="#"><img src="images/download1.png" width="30" height="30"></a></td>
                                                            <c:choose>
                                                                <c:when test="${obj1.isStarred==1}">
                                                            <td><a href="Controller.jsp?fileId=${obj1.fileId}&isStarredFiles=${obj1.isStarred}"><img src="images/star_on.jpg" width="25" height="20"></a></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                            <td><a href="Controller.jsp?fileId=${obj1.fileId}&isStarredFiles=${obj1.isStarred}"><img src="images/star_off.jpg" width="25" height="20"></a></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                    <td><input type="checkbox" name="chkFile" id="chk1" value="${obj1.fileId}"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>	
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>

