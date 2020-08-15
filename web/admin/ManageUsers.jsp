<%@page import="rescueDrive.beans.admin.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="rescueDrive.beans.admin.AdminBean"%>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.admin.AdminServices"%>
<html>
    <head>
        <title>Manage User With Expired Trail</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <%
            AdminServices objServices = new AdminServices();
            List<UserBean> lstUsers = objServices.getAllUsers();
            pageContext.setAttribute("LIST_USERS", lstUsers);

//            AdminServices obAdminServices=new AdminServices();
//             List<AdminBean> lstUsers1=obAdminServices.getInfoUserByID(userId);
//             pageContext.setAttribute("LIST_USERS1", lstUsers1);

        %>
        <jsp:include page="mp_header.jsp"/>
        <table border="0" cellpadding="3" cellspacing="0" align="center" width="70%">
            <tr>

                <td align="center">
                    <h3><strong>Manage User With Expired Trail</strong></h3>
                </td>
            </tr>
            <tr>
                <td align="left">
                    <label id="lblMsg" style="color: red">${param.msg}</label>
                </td>
            </tr>
            <tr>
                <td>
                    <table border="1" cellpadding="3" cellspacing="0" width="100%">
                        <tr>
                            <th>Name</th>
                            <th>Contact</th>

                            <th>Status</th>


                        </tr>
                        <c:forEach items="${LIST_USERS}" var="obj">
                            <tr>
                                <td><a href="ViewUserDetails.jsp?userId=${obj.userId}">${obj.name}</a></td>
                                <td>${obj.contact}</td>
                                <td>${obj.userStatus}<a href="Controller.jsp?userId=${obj.userId}&status=${obj.userStatus}">Changed</a></td>

                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>

    </body>
</html>

