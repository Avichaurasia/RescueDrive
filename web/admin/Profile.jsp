<%@page import="rescueDrive.beans.admin.UserBean"%>
<%@page import="rescueDrive.services.admin.AdminServices"%>
<%@page import="rescueDrive.beans.admin.AdminBean"%>
<%@page import="rescueDrive.beans.admin.CityBean"%>
<%@page import="rescueDrive.services.common.CityServices"%>
<%@page import="rescueDrive.beans.admin.StateBean"%>
<%@page import="rescueDrive.services.common.StateServices"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="rescueDrive.beans.admin.CountryBean"%>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.common.CountryServices"%>
<html>
    <head>
        <title></title>
        <jsp:include page="stylesheet.jsp"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#frm1').submit(function() {
                    var name = $('#txtName').val();
                    var ss = "";

                    //alert("name " + name);
                    for (var i = 0; i < name.length; i++)
                    {
                        if (name.charAt(i) != ' ')
                        {
                            ss = ss + name.charAt(i);
                        }
                    }
                    //alert("sdfsf " + ss);
//                     alert("ss " + ss);
                    var contact = $('#txtContactNumber').val();
                    var address = $('#taAddress').val();
                    var pinCode = $('#txtZipCode').val();
                    var date = $('#txtDOJ').val();
                    var country = $('#ddlCountry').val();
                    var state = $('#ddlState').val();
                    var city = $('#ddlCity').val();
                    alert("dfghjk");
                    if (isEmpty(name))
                    {
                        
                        alert("name required");
                        $('#txtName').focus();
                        return false;
                    }
                    //alert("123456");
//                    if (isNumeric2(ss) == 1)
//                    {
//
//                        //                        alert(isName(name));
//                        alert("only alphabetes are allowed for name");
//                        $('#txtName').focus();
//                        return false;
//
//                    }
                    if (isEmpty(contact))
                    {
                        alert("contact field cannot be blank");
                        $('#txtContactNumber').focus();
                        return false;
                    }
                    if (!isNumeric(contact))
                    {
                        alert("contact should be in numeric");
                        $('#txtContactNumber').focus();
                        return false;
                    }
                    if (!isInRange(10, 15, contact))
                    {
                        alert("numbers are not in range for conatct field");
                        $('#txtContactNumber').focus();
                        return false;


                    }

                    if (isEmpty(address))
                    {
                        alert("address field cannot be blank");
                        $('#taAddress').focus();
                        return false;

                    }
                    if (noValue(country))
                    {

                        alert("please select any country");
                        $('#ddlCountry').focus();
                        return  false;
                    }

                    if (noValue(state))
                    {


                        alert("please select any state");
                        $('#ddlState').focus();
                        return  false;
                    }

                    if (noValue(city))
                    {

                        alert("please select any city");
                        $('#ddlCity').focus();
                        return  false;
                    }

                    if (!isNumeric(pinCode))
                    {
                        alert("pin Code should be in numeric");
                        $('#txtZipCode').focus();
                        return false;
                    }

                    if (isEmpty(date))
                    {
                        alert("date filed cannot be blank");
                        $('#txtDOJ').focus();
                        return false;
                    }

                    /*if (isNumeric(date))
                     {
                     alert("only numeric are allowed for date field");
                     $('#txtDOJ').focus();
                     return false;
                     }*/






                    return  true;
                });
            });

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#ddlCountry').change(function() {


                    var cId = $('#ddlCountry').val();

                    $.get('state.jsp?countryId=' + cId, function(response) {
                        $('#ddlState').html(response);
                    });
                });
                $('#ddlState').change(function() {
                    var sId = $('#ddlState').val();
                    $.get('city.jsp?stateId=' + sId, function(response) {
                        $('#ddlCity').html(response);
                    });
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        <form name="frm1" action="Controller.jsp" method="post" id="frm1">
            <%
                UserBean obj = new AdminServices().getUsersInfoById(Integer.parseInt(session.getAttribute("userId").toString()));

                pageContext.setAttribute("obj", obj);

                CountryServices obJServices = new CountryServices();
                List<CountryBean> lst = obJServices.getAllCountries();
                pageContext.setAttribute("LIST", lst);

                StateServices obStateServices = new StateServices();
                List<StateBean> lst1 = obStateServices.getAllStates();
                pageContext.setAttribute("LIST1", lst1);

                CityServices oCityServices = new CityServices();
                List<CityBean> lst2 = oCityServices.getAllCities();
                pageContext.setAttribute("LIST2", lst2);

                //ountryServices obCountryServices=new CountryServices();

            %>







            <table border="0" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td align="center" height="30" valign="top"><strong>Profile</strong></td>
                </tr>
                <tr>
                    <td align="left">
                        <label id="lblMsg" style="color: red">${param.msg}</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong><em>Account Information</em></strong> </legend>
                            <table border="0" cellpadding="5" cellspacing="0">

                                <tr>
                                    <td><strong>Username</strong></td>
                                    <td>${obj.userName}</td>
                                </tr>

                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong><em>Personal Information</em></strong> </legend>
                            <table border="0" cellpadding="5" cellspacing="0">
                                <tr>
                                    <td><strong>Name</strong></td>
                                    <td>
                                        <input id="userId"  name="userId" type="hidden" value="${obj.userId}"/>
                                        <input id="txtName"  name="name" type="text" value="${obj.name}"/>
                                    </td>
                                </tr> 
                                <tr>
                                    <td><strong>Contact Number</strong></td>
                                    <td><input id="txtContactNumber"  name="contact" type="text" value="${obj.contact}"/></td>
                                </tr> 
                                <tr>
                                    <td valign="top"><strong>Address</strong></td>
                                    <td><textarea cols="23" rows="3" name="address" id="taAddress">${obj.address}</textarea>
                                </tr>
                                <tr>
                                    <td><strong>Pin Code</strong></td>
                                    <td><input id="txtZipCode"  name="zipcode" type="text" value="${obj.zipcode}"  /></td>
                                </tr>

                                <tr>
                                    <td><strong>Date Of Joining</strong></td>
                                    <td><input id="txtDOJ"  name="date"  type="text" value="${obj.date}"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Country</strong></td>
                                    <td>
                                        <select id="ddlCountry" name="country" >
                                            <option value="select">Select</option>
                                            <c:forEach items="${LIST}" var="objC">
                                                <c:set var="str" value=""/>
                                                <c:if test="${objC.countryId == obj.country}">
                                                    <c:set var="str" value="selected"/>
                                                </c:if>
                                                <option value="${objC.countryId}" ${str}>${objC.countryName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><strong>State</strong></td>
                                    <td>
                                        <select id="ddlState" name="state">
                                            <option value="select">Select</option>
                                            <c:forEach items="${LIST1}" var="objS">
                                                <c:set var="str1" value=""/>
                                                <c:if test="${objS.stateId==obj.state}">
                                                    <c:set var="str1" value="selected"/>
                                                </c:if>
                                                <option value="${objS.stateId}" ${str1}>${objS.stateName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><strong>City</strong></td>
                                    <td>
                                        <select id="ddlCity" name="city">
                                            <option value="select" >Select</option>
                                            <c:forEach items="${LIST2}" var="objC1">
                                                <c:set var="str2" value=""/>
                                                <c:if test="${objC1.cityId==obj.city}">
                                                    <c:set var="str2" value="selected"/>

                                                </c:if>
                                                <option value="${objC1.cityId}" ${str2}>${objC1.cityName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>

                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <input type="submit" name="sbtnUpdate" value="Update" id="sbtnUpdate" class="button">&nbsp;
                        <input type="button" name="btnCancel" value="Cancel" id="btnCancel" class="button" onclick="window.location = 'Homepage.jsp'">
                    </td>
                </tr>
            </table>
        </form>
       
    </body>
</html>
