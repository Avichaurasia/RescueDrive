<head>
    <title>Login</title>
    <jsp:include page="stylesheet.jsp"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#loginForm').submit(function() {
                var userName = $('#txtUsername').val();
                var password = $('#txtPassword').val();
                var userType = $('#ddlUserType').val();

                if (isEmpty(userName))
                {

                    alert("please enter Username");
                    $('#txtUsername').focus();
                    return false;
                }
                if (hasSpace(userName))
                {
                    alert("space not allowed for User Name");
                    $('#txtUsername').focus();
                    return false;
                }
                    if(!isEmail(userName))
                    {
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

                if (hasSpace(password))
                {
                    alert("space not allowed for password");
                    $('#txtPassword').focus();
                    return false;
                }
                if (!isInRange(4, 15, password))
                {
                    alert("Password should be between 8-15 characters");
                    $('#txtPassword').focus();
                    return false;
                }
                if (!isAlphaNumeric(password))
                {
                    alert("only alphabetes and numbers are allowed");
                    $('#txtPassword').focus();
                    return false;
                }
//                    if(noValue(userType))
//                    {
//                        alert("please select any User Type");
//                        $('#ddlUserType').focus();
//                        return false;
//                    }
                if (noValue(userType))
                {

                    alert("please select any UserType");
                    $('#ddlUserType').focus();
                    return  false;
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
    <form name="loginForm" action="AuthServlet" method="post" id="loginForm">
        <table align="right"> 
            <tr>
                <td>
                    <fieldset><legend><strong><em>&nbsp;Login&nbsp;</em></strong></legend>
                        <table cellpadding="5">
                            <tr>
                                <td align="left">
                                    <label id="lblMsg" style="color: red">${param.msg}</label>
                                </td>
                            </tr>
                            <tr> 
                                <td colspan="2" align="left">
                                    <label id ="lblMsg"></label>
                                </td>
                            <tr>
                                <td>User&nbsp;Name</td>
                                <td><input  id="txtUsername" name="txtUsername"  type="text" /></td>

                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input  id="txtPassword" name="txtPassword" type="password" /></td>
                            </tr>
                            <tr>
                                <td>User Type</td>
                                <td>
                                    <select name="ddlUserType" id="ddlUserType">
                                        <option value="Select">Select</option>
                                        <option value="employee">Administrator</option>
                                        <option value="User">User</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right" >
                                    <input id="sbtnLogin"  name="sbtnLogin" type="submit" value="Login"/>
                                    <input id="rbtnReset" name="rbtnReset"  type="reset" value="Reset"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center" ><a href="ForgotPassword.jsp">Forgot Password ?</a>&nbsp;&nbsp<a href="SignupForm.jsp">Sign Up</a></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>