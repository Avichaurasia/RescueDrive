<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <head>
        <title>Rescue Drive</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body style="margin: 0 auto; padding: 0 auto; background-color: #795275; background-image: url(images/envelope_bg.gif);
    background-repeat: repeat-x; background-position: top center;">
        <table border="0" cellpadding="0" cellspacing="0" style="margin: 0 auto; padding: 0 auto;
        width: 900px;">
            <tr>
                <td style="height: 65px;">
                    <jsp:include page="header.jsp"/>
                </td>
            </tr>
            <tr>
                <td style="min-height: 1px; max-height: 1px; height: 1px; background-color: #0e87b6;">
                </td>
            </tr>
            <tr>
                <td style="height: 20px; background-color: #009a76;">
                    <jsp:include page="topmenu.jsp"/>
                </td>
            </tr>
            <tr>
                <td style="height: 450px; border-left: solid 1px #f0f0f0; border-right: solid 1px #f0f0f0;
                background-color: White;" valign="top">
                    <jsp:include page="topsubmenu.jsp"/>
                    <jsp:include page="homecontent.jsp"/>


                </td>
            </tr>
        </table>
        <div style="margin: 0 auto; padding: 0 auto; width: 900px; padding-top: 5px;">
                    <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
