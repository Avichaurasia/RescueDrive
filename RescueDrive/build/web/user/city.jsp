<%@page import="rescueDrive.services.common.CountryStateCityServices"%>
<%@page import="rescueDrive.beans.admin.CityBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%
    CountryStateCityServices objServices = new CountryStateCityServices();
    System.out.println("   City");
    List<CityBean> lstCities = objServices.getAllCities(Integer.parseInt(request.getParameter("stateId")));
    pageContext.setAttribute("lstCities", lstCities);
    System.out.println(lstCities.size() + "   City");
%>
<option value="0" selected="selected">Select</option>
<c:forEach items="${lstCities}" var="objBean">
    <option value="${objBean.cityId}">${objBean.cityName}</option>
</c:forEach>
