<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <jsp:include page="stylesheet.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        <table border="1" rules="none" align="center">
            <tr>
                <td>
                    <font size="3"><strong>Please confirm your email address</strong></font>
                </td>
            </tr>
            <tr>
                <td><hr></td>
            </tr>
            <tr>
                <td>
                    <table>
                        <tr>
                            <td><img src="main_img.png" alt=""  width="100" height="100"/></td>
                            <td width="20"></td>
                            <td>


                                A confirmation email has been sent to:<br>
                                <br>
                                <strong>abc@example.com</strong><p>

                                    Click on the confirmation link in the email to activate your account.<br> We do this as a security precaution to verify your credentials.
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td><hr></td>
            </tr>
            <tr>
                <td>
                    <b> If you don't see this email you can:</b>
                    <p>&nbsp;&nbsp;&nbsp;Check your junk mail folder
                </td>
            </tr>
        </table>
        <jsp:include page="mp_footer.jsp"/>
    </body>
</html>

