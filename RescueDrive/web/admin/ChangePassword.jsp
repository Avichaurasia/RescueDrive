<html>
    <head>
        <title>Change Password</title>
        <jsp:include page="stylesheet.jsp"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#frm').submit(function() {
                    var pass = $('#txtCurrentPassword').val();
                    var newPass = $('#txtNewPassword').val();
                    var confirmPass = $('#txtConfirmPassword').val();
                    if (isEmpty(pass))
                    {
                        alert("Enter current Password");
                        $('#txtCurrentPassword').focus();
                        return false;
                    }
                    if (!isInRange(4, 15, pass))
                    {
                        alert("Password sholud be Range of 6-15 for current passowrd");
                        $('#txtCurrentPassword').focus();
                        return false;
                    }
                    if (hasSpace(pass))
                    {
                        alert("space not allowed in current password");
                        $('#txtCurrentPassword').focus();
                        return false;
                    }
                    if (!isAlphaNumeric(pass))
                    {
                        alert("only alphabetes and numbers are allowed in current password");
                        $('#txtCurrentPassword').focus();
                        return false;
                    }
                    if (isEmpty(newPass))
                    {
                        alert("Enter new Password");
                        $('#txtNewPassword').focus();
                        return false;
                    }
                    if (!isInRange(4, 15, newPass))
                    {
                        alert("Password should be in range of 6-15 for new password");
                        $('#txtNewPassword').focus();
                        return false;
                    }
                    if (hasSpace(newPass))
                    {
                        alert("space not allowed in new passowrd");
                        $('#txtNewPassword').focus();
                        return false;
                    }
                    if (!isAlphaNumeric(newPass))
                    {
                        alert("only alphabetes and numbers are allowed in new password");
                        $('#txtNewPassword').focus();
                        return false;
                    }

                    if (isEmpty(confirmPass))
                    {
                        alert("confirm Password ");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!isInRange(4, 15, confirmPass))
                    {
                        alert("Password Not in Range for confirm password");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!matchPassword(newPass, confirmPass))
                    {
                        alert("old and new password do not match");
                        $('#txtNewPassword').focus();
                        return false;
                    }
                    if (hasSpace(confirmPass))
                    {
                        alert("space not allowed in confirm password");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    if (!isAlphaNumeric(confirmPass))
                    {
                        alert("only alphabetes and numbers are allowed in confirm password");
                        $('#txtConfirmPassword').focus();
                        return false;
                    }
                    return true;
                });
            });

        </script>
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        <form name="frm" action="../ChangePasswordServlet" method="post" id="frm">
            <table border="0" align="center" rules="none">
                <tr>
                    <td align="left">
                        <label id="lblMsg" style="color: red">${param.msg}</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend><strong> <em> Change Password</em> </strong></legend>
                            <table  align="center" cellpadding="5" cellspacing="0" width="100%">
                                <tr>
                                    <td><b>Current Password</b></td>
                                    <td><input type="password" class="txtStyle" id="txtCurrentPassword" name="txtCurrentPassword"/></td>
                                </tr>
                                <tr>
                                    <td><b>New Password</b> </td>
                                    <td><input type="password" class="txtStyle"  id="txtNewPassword" name="txtNewPassword"/></td>
                                </tr>
                                <tr>
                                    <td><b>Confirm Password</b></td>
                                    <td><input type="password" class="txtStyle "  id="txtConfirmPassword" name="txtConfirmPassword"/></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <input type="submit"  value="Change" name="sbtnChange" class="button">
                        <input type="reset"  value="Reset" name="rbtnReset" class="button">
                    </td>
                </tr>
            </table>
        </form>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>