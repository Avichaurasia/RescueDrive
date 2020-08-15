<%@page import="rescueDrive.beans.admin.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="rescueDrive.beans.admin.AdminBean"%>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.admin.AdminServices"%>
<html>
    <head>
        <title></title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <%
            UserBean obj = new AdminServices().getInfoUserByID(Integer.parseInt(request.getParameter("userId")));
            pageContext.setAttribute("obj", obj);


        %>
        <jsp:include page="mp_header.jsp"/>

        <form action="Controller.jsp" method="GET" >
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong><em>User Details</em></strong></legend>
                            <table border="0" cellpadding="5" cellspacing="0" width="100%" >
                                <tr>
                                    <td align="left">
                                        <label id="lblMsg" style="color: red">${param.msg}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td><strong>UserName</strong></td>
                                    <td>${obj.userName}</td>
                                    <td><strong>Name</strong></td>
                                    <td>${obj.name}</td>
                                </tr>
                                <tr>
                                    <td valign="top"><strong>Address</strong></td>
                                    <td>
                                        <input type="hidden" name="userId" value="${obj.userId}"/>
                                        ${obj.address}
                                    </td>
                                </tr>

                                <tr>
                                    <td><strong>Country</strong></td>
                                    <td>${obj.countryName}</td>
                                </tr>
                                <tr>
                                    <td><strong>State</strong></td>
                                    <td>${obj.stateName}</td>
                                </tr>
                                <tr>

                                    <td><strong>City</strong></td>
                                    <td>${obj.cityName}</td>
                                </tr>
                                <tr>
                                    <td><strong>Zip Code</strong></td>
                                    <td>${obj.zipcode}</td>
                                </tr>
                                <tr>
                                    <td><strong>Status</strong></td>
                                <c:choose>
                                    <c:when test="${obj.status.equalsIgnoreCase('active')}">
                                        <c:set var="active" value="checked"/>
                                    </c:when>
                                    <c:when test="${obj.status.equalsIgnoreCase('inactive')}">
                                        <c:set var="inactive" value="checked"/>
                                    </c:when>
                                </c:choose>
                                    <td><input type="radio" name="rbStatus" id ="rbActive" ${active} value="Active"  /> Active
                                        <input type="radio" name="rbStatus" id="rbInactive"  ${inactive} value="InActive"  /> Inactive</td>
                                </tr>
                                <tr>
                                    <td  align="center" colspan="2">
                                        <input type="submit" value="Update" id="btnUpdate" name="btnUpdate" class="button">
                                        <input type="button" value="Back" id="btnBack" name="btnBack"class="button" onclick="history.go(-1)">
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>

            </table>
        </form>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>
