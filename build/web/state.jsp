<%@page import="rescueDrive.beans.admin.StateBean"%>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.common.CountryStateCityServices"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    CountryStateCityServices objServices = new CountryStateCityServices();
    List<StateBean> lstStates = objServices.getAllStates(Integer.parseInt(request.getParameter("countryId")));
    pageContext.setAttribute("lstStates", lstStates);
    System.out.println(lstStates.size());
%>
<option value="0" selected="selected">Select</option>
<c:forEach items="${lstStates}" var="objBean">
    <option value="${objBean.stateId}">${objBean.stateName}</option>
</c:forEach>