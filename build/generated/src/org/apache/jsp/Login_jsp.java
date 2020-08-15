package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("    <head>\n");
      out.write("        <title>Login</title>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "stylesheet.jsp", out, false);
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            $(document).ready(function() {\n");
      out.write("                $('#loginForm').submit(function() {\n");
      out.write("                    var userName=$('#txtUsername').val();\n");
      out.write("                    var password=$('#txtPassword').val();\n");
      out.write("                    var userType=$('#ddlUserType').val();\n");
      out.write("                   \n");
      out.write("                    if(isEmpty(userName))\n");
      out.write("                    {\n");
      out.write("                         \n");
      out.write("                        alert(\"please enter Username\");\n");
      out.write("                        $('#txtUsername').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if (hasSpace(userName))\n");
      out.write("                    {\n");
      out.write("                        alert(\"space not allowed for User Name\");\n");
      out.write("                        $('#txtUsername').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if(!isEmail(userName))\n");
      out.write("                    {\n");
      out.write("                        alert(\"Eneter valid email\");\n");
      out.write("                        $('#txtUsername').focus();\n");
      out.write("                        return false;\n");
      out.write("                        \n");
      out.write("                    }\n");
      out.write("                    if(isEmpty(password))\n");
      out.write("                    {\n");
      out.write("                        alert(\"please enter the passwoed\");\n");
      out.write("                        $('#txtPassword').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if (!isInRange(8, 15, password))\n");
      out.write("                    {\n");
      out.write("                        alert(\"Password should be between 8-15 characters\");\n");
      out.write("                        $('#txtPassword').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if (hasSpace(password))\n");
      out.write("                    {\n");
      out.write("                        alert(\"space not allowed for password\");\n");
      out.write("                        $('#txtPassword').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if (!isAlphaNumeric(password))\n");
      out.write("                    {\n");
      out.write("                        alert(\"only alphabetes and numbers are allowed\");\n");
      out.write("                        $('#txtPassword').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                    if(noValue(userType))\n");
      out.write("                    {\n");
      out.write("                        alert(\"please select any User Type\");\n");
      out.write("                        $('#ddlUserType').focus();\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                     if (noValue(userType))\n");
      out.write("                    {\n");
      out.write("\n");
      out.write("                        alert(\"please select any UserType\");\n");
      out.write("                        $('#ddlUserType').focus();\n");
      out.write("                        return  false;\n");
      out.write("                    }\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    return true;\n");
      out.write("                    \n");
      out.write("                });\n");
      out.write("                    \n");
      out.write("                });\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            $(document).ready(function() {\n");
      out.write("                $('#ddlCountry').change(function() {\n");
      out.write("                    \n");
      out.write("\n");
      out.write("                    var cId = $('#ddlCountry').val();\n");
      out.write("\n");
      out.write("                    $.get('state.jsp?countryId=' + cId, function(response) {\n");
      out.write("                        $('#ddlState').html(response);\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("                $('#ddlState').change(function() {\n");
      out.write("                    var sId = $('#ddlState').val();\n");
      out.write("                    $.get('city.jsp?stateId=' + sId, function(response) {\n");
      out.write("                        $('#ddlCity').html(response);\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            $(document).ready(function() {\n");
      out.write("                $('#ddlCountry').change(function() {\n");
      out.write("                    \n");
      out.write("\n");
      out.write("                    var cId = $('#ddlCountry').val();\n");
      out.write("\n");
      out.write("                    $.get('state.jsp?countryId=' + cId, function(response) {\n");
      out.write("                        $('#ddlState').html(response);\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("                $('#ddlState').change(function() {\n");
      out.write("                    var sId = $('#ddlState').val();\n");
      out.write("                    $.get('city.jsp?stateId=' + sId, function(response) {\n");
      out.write("                        $('#ddlCity').html(response);\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "mp_header.jsp", out, false);
      out.write("\n");
      out.write("        <form name=\"loginForm\" action=\"AuthServlet\" method=\"post\" id=\"loginForm\">\n");
      out.write("            <table align=\"right\"> \n");
      out.write("                <tr>\n");
      out.write("                    <td>\n");
      out.write("                        <fieldset><legend><strong><em>&nbsp;Login&nbsp;</em></strong></legend>\n");
      out.write("                            <table cellpadding=\"5\">\n");
      out.write("                                <tr>\n");
      out.write("                                    <td align=\"left\">\n");
      out.write("                                        <label id=\"lblMsg\" style=\"color: red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.msg}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</label>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr> \n");
      out.write("                                    <td colspan=\"2\" align=\"left\">\n");
      out.write("                                        <label id =\"lblMsg\"></label>\n");
      out.write("                                    </td>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>User&nbsp;Name</td>\n");
      out.write("                                    <td><input  id=\"txtUsername\" name=\"txtUsername\"  type=\"text\" /></td>\n");
      out.write("\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>Password</td>\n");
      out.write("                                    <td><input  id=\"txtPassword\" name=\"txtPassword\" type=\"password\" /></td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>User Type</td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <select name=\"ddlUserType\" id=\"ddlUserType\">\n");
      out.write("                                            <option value=\"Select\">Select</option>\n");
      out.write("                                            <option value=\"employee\">Administrator</option>\n");
      out.write("                                            <option value=\"User\">User</option>\n");
      out.write("                                        </select>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td colspan=\"2\" align=\"right\" >\n");
      out.write("                                        <input id=\"sbtnLogin\"  name=\"sbtnLogin\" type=\"submit\" value=\"Login\"/>\n");
      out.write("                                        <input id=\"rbtnReset\" name=\"rbtnReset\"  type=\"reset\" value=\"Reset\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td colspan=\"2\" align=\"center\" ><a href=\"ForgotPassword.jsp\">Forgot Password ?</a>&nbsp;&nbsp<a href=\"SignupForm.jsp\">Sign Up</a></td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        </fieldset>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "mp_footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
