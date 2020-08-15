<%@page import="rescueDrive.beans.admin.CityBean"%>
<%@page import="rescueDrive.services.common.CityServices"%>
<%@page import="rescueDrive.beans.admin.StateBean"%>
<%@page import="rescueDrive.services.common.StateServices"%>
<%@page import="rescueDrive.beans.admin.CountryBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="rescueDrive.services.common.CountryServices"%>
<%@page import="rescueDrive.services.user.UserServices"%>
<%@page import="rescueDrive.beans.admin.UserBean"%>
<html>
    <head>
        <title></title>
        <jsp:include page="stylesheet.jsp"/>
       
        <script type="text/javascript">
            $(document).ready(function() {
                $('#signUp').submit(function() {
                    //alert("11111");
                    var name = $('#txtName').val();
                    /*var ss = "";
                     for (var i = 0; i < name.length; i++)
                     {
                     if (name.charAt(i) != ' ')
                     {
                     ss = ss + name.charAt(i);
                     }
                     }
                     alert("ss " + ss);*/
                    //alert("2342544");
                    var userName = $('#txtEmail').val();
                    //alert("222");
                    var password = $('#txtPassword').val();
                    var ConfirmPass = $('#txtConfirmPassword').val();

                    var contact = $('#txtPhone').val();
                    var address = $('#taAddress').val();
                    

                    var country = $('#ddlCountry').val();
                    var state = $('#ddlState').val();
                    var city = $('#ddlCity').val();
                    var pinCode = $('#txtZipCode').val();




//                    alert("name     " + name);
//                    alert("&&&&&&&&&&&");

                     //alert("3456789");
                    if (isEmpty(name))
                    {
                        alert("name required");
                        $('#txtName').focus();
                        return false;
                    }
                     
                    
//                    if (!isName(name))
//                    {
//                        alert("abvghvsshgavshagy");
//                       
//
////                        alert(isName(name));
//                        alert("only alphabetes are allowed for name");
//                        $('#txtName').focus();
//                        return false;
//
//                    }
//                    
                    
                    if (isEmpty(userName))
                    {
                        alert("please enter Username");
                        $('#txtUsername').focus();
                        return false;
                    }
                    
                    if (hasSpace(userName))
                    {
                        alert("space not allowed for UserName");
                        $('#txtUsername').focus();
                        return false;
                    }
                    
                    if (!isEmail(userName))
                    {
                        alert("322222222");
                        alert("Eneter valid email");
                        $('#txtUsername').focus();
                        return false;

                    }
                    if (isEmpty(password))
                    {
                        alert("please enter the password");
                        $('#txtPassword').focus();
                        return false;
                    }
                    if (!isInRange(6, 15, password))
                    {
                        alert("Password should be between 6-15 characters");
                        $('#txtPassword').focus();
                        return false;
                    }
                    if (hasSpace(password))
                    {
                        alert("space not allowed for password");
                        $('#txtPassword').focus();
                        return false;
                    }
                    if (!isAlphaNumeric(password))
                    {
                        alert("only alphabetes and numbers are allowed");
                        $('#txtPassword').focus();
                        return false;
                    }
                    if (isEmpty(ConfirmPass))
                    {
                        alert("Password Reqd.");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!isInRange(8, 15, ConfirmPass))
                    {
                        alert("Password Not in Range");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!matchPassword(password, ConfirmPass))
                    {
                        alert("password do not match");
                        $('#txtNewPassword').focus();
                        return false;
                    }
                    if (hasSpace(ConfirmPass))
                    {
                        alert("space not allowed");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!isAlphaNumeric(ConfirmPass))
                    {
                        alert("only alphabetes and numbers are allowed");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }

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
                    if (!isInRange(6, 15, contact))
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
                    return true;


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
        <form name="signUp" action="SignUpController.jsp" method="post" id="signUp">
            <%
//                UserBean obj = new UserServices().getUsersInfoById(Integer.parseInt(session.getAttribute("userId").toString()));

//                pageContext.setAttribute("obj", obj);
                CountryServices obJServices = new CountryServices();
                List<CountryBean> lst = obJServices.getAllCountries();
                pageContext.setAttribute("LIST", lst);

                StateServices obStateServices = new StateServices();
                List<StateBean> lst1 = obStateServices.getAllStates();
                pageContext.setAttribute("LIST1", lst1);

                CityServices oCityServices = new CityServices();
                List<CityBean> lst2 = oCityServices.getAllCities();
                pageContext.setAttribute("LIST2", lst2);


            %>
            <table>
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong><em>Sign Up for a 30 day`s trail</em></strong></legend>
                            <table>
                                <tr>
                                    <td align="left">
                                        <label id="lblMsg" style="color: red">${param.msg}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td><strong>Name</strong></td>
                                    <td><input type="text" class="txtStyle" name="Name" id="txtName" /></td>
                                </tr>
                                <tr>
                                    <td><strong>Username</strong></td>
                                    <td><input type="text" class="txtStyle" name="userName" id="txtEmail"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Password</strong></td>
                                    <td><input type="password" class="txtStyle" name="password" id="txtPassword"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Confirm Password</strong></td>
                                    <td><input type="password" class="txtStyle" name="txtConfirmPassword" id="txtConfirmPassword"  /></td>
                                </tr>
                                <tr>
                                    <td><strong>Phone</strong></td>
                                    <td><input type="text" class="txtStyle" name="contact" id="txtPhone" /></td>
                                </tr>

                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong><em>First Login</em></strong></legend>
                            <table border="0" cellpadding="5" cellspacing="0" width="100%" >

<!--                                <tr>
                                    <td><strong>Name</strong></td>
                                    <td><input type="text" class="txtStyle" name="name" id="Name" /></td>
                                </tr>-->
                                <tr>
                                    <td valign="top"><strong>Address</strong></td>
                                    <td>
                                        <textarea name="address" id="taAddress" cols="30" rows="5"></textarea>
                                    </td>
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
                                        <div id="divState">
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
                                        </div>
                                    </td>
                                </tr>
                                <tr>

                                    <td><strong>City</strong></td>
                                    <td>
                                        <div id="divCity">
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
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><strong>Zip Code</strong></td>
                                    <td><input type="text" class="txtStyle"  name="zipcode" id="txtZipCode" size="10"></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center" height="40" valign="bottom">
                                        <input type="submit" name="sbtnCountinue" value="Continue" id="sbtnCountinue" class="button">
                                        <input type="button" name="btnCancel" value="Cancel" id="btnCancel" class="button" onclick="history.go(-1)">
                                    </td>
                                </tr>


                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>

    </body>
</html>
