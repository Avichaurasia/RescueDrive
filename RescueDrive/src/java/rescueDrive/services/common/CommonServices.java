/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.services.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rescueDrive.beans.common.LoginBean;
import rescueDrive.email.SendSMTP;

/**
 *
 * @author User
 */
public class CommonServices {

    private LoginBean objBean;

    public LoginBean authUser(String un, String pass, String type) {
        LoginBean obj = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from " + type + "master where username = ? and password = binary(?)");
            pstmt.setString(1, un);
            pstmt.setString(2, pass);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new LoginBean();
                obj.setUserId(rs.getInt("user_id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setUserStatus(rs.getString("user_status"));
                obj.setUserType(rs.getString("user_type"));
            }
        } catch (Exception e) {
            System.out.println("authUser" + e);
        }
        return obj;
    }

    public String changePassword(String oldPass, String newPass, int userId, String type) {
        PreparedStatement pstmt = null;
        String result = "Failed to Change Password";
        try (Connection conn = ConnectDB.connect();) {
            pstmt = conn.prepareStatement("select * from " + type + "master where password = ? and user_id=?");
            pstmt.setString(1, oldPass);
            pstmt.setInt(2, userId);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt = conn.prepareStatement("update " + type + "master set password=binary(?) where user_id=?");
                pstmt.setString(1, newPass);
                pstmt.setInt(2, userId);
                System.out.println(pstmt);
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    result = "Password Changed Successfully";
                }
            } else {
                result = "No such User exists";
            }
        } catch (Exception e) {
            System.out.println("changePassowrd" + e);
        }
        return result;
    }

    public String forgotPassword(String username) {
        String result = "Failed to send email";
        PreparedStatement pstmt = null;
        try (Connection conn = ConnectDB.connect();) {
            pstmt = conn.prepareStatement("select * from usermaster where username=?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SendSMTP().sendMail(rs.getString("username"), "Your recovery password is" + rs.getString("password"), "Your password regarding email");
            } else {
                result = "No such User exists";
            }
        } catch (Exception e) {
            System.out.println("exception in forgot Password" + e);
        }
        return result;
    }

}
