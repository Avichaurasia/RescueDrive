/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.services.admin;

import static com.sun.mail.imap.protocol.BODY.name;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rescueDrive.beans.admin.UserBean;
import rescueDrive.services.common.ConnectDB;

/**
 *
 * @author User
 */
public class AdminServices {

    private UserBean obj1;

    public List<UserBean> getAllUsers() {
        List<UserBean> lstUsers = new ArrayList<>();
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from  usermaster un,usercontactmaster ucn where un.user_id=ucn.user_id and un.is_temp=1");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserBean obj = new UserBean();
                obj.setUserId(rs.getInt("user_id"));
                obj.setName(rs.getString("name"));
                obj.setContact(rs.getString("phone"));
                obj.setUserStatus(rs.getString("user_status"));
                lstUsers.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllUsers" + e);
        }
        return lstUsers;
    }

    public UserBean getUsersInfoById(int userId) {
        UserBean obj = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from employeemaster where user_id = ?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new UserBean();
                obj.setUserId(rs.getInt("user_id"));
                obj.setUserName(rs.getString("username"));
                obj.setContact(rs.getString("contact_number"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCountry(rs.getInt("country_id"));
                obj.setState(rs.getInt("state_id"));
                obj.setCity(rs.getInt("city_id"));
                obj.setZipcode(rs.getString("zipcode"));
                obj.setDate(rs.getString("date_of_joining"));
            }
        } catch (Exception e) {
            System.out.println("Exception in getUsersInfoById" + e);
        }
        return obj;
    }

    public UserBean getInfoUserByID(int userId) {
        UserBean obj1 = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from usercontactmaster uc inner join usermaster um on uc.user_id = um.user_id inner join country cm on uc.country_id = cm.country_id inner join state sm on uc.state_id = sm.state_id inner join city cim on cim.city_id = uc.city_id and um.user_id=?");
            pstmt.setInt(1, userId);
            System.out.println("sdfdf " + pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj1 = new UserBean();
                obj1.setUserName(rs.getString("UserName"));
                obj1.setUserId(rs.getInt("user_id"));
                obj1.setName(rs.getString("name"));
                obj1.setAddress(rs.getString("address"));
                obj1.setCountry(rs.getInt("country_id"));
                obj1.setCountryName(rs.getString("country_name"));
                obj1.setStateName(rs.getString("state_name"));
                obj1.setCityName(rs.getString("city_name"));
                obj1.setState(rs.getInt("state_id"));
                obj1.setCity(rs.getInt("city_id"));
                obj1.setZipcode(rs.getString("zipcode"));
                obj1.setStatus(rs.getString("user_status"));

            }
        } catch (Exception e) {
            System.out.println("Exception in getInfoUserById" + e);
        }
        return obj1;

    }

    public String UpdateAdminInfo(rescueDrive.beans.user.UserBean obj) {
        String result = "failed to upadte record";
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("update employeemaster set name=?,contact_number=?,address=?,zipcode=?,date_of_joining=?,country_id=?,state_id=?,city_id=? where user_id=?");
            pstmt.setString(1, obj.getName());
            pstmt.setString(2, obj.getContact());
            pstmt.setString(3, obj.getAddress());
            pstmt.setString(4, obj.getZipcode());
            pstmt.setString(5, obj.getDate());
            pstmt.setInt(6, obj.getCountry());
            pstmt.setInt(7, obj.getState());
            pstmt.setInt(8, obj.getCity());
            pstmt.setInt(9, obj.getUserId());
            System.out.println("dfghjkl: " + pstmt.toString());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "record updated successfully";

            }

        } catch (Exception e) {
            System.out.println("Exception in UpdateInfo" + e);
        }
        return result;

    }

    public String UpadteUserStatus(int userId, String userStatus) {
        String result = "failed to update User id";

        try (Connection conn = ConnectDB.connect();) {

            PreparedStatement pstmt = conn.prepareStatement("update usermaster set user_status=? where user_id=?");

            pstmt.setString(1, userStatus);
            pstmt.setInt(2, userId);
            System.out.println("sdfdsfg " + pstmt.toString());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "User Status updated successfully";

            }
        } catch (Exception e) {
            System.out.println("Exception  in UpdateUserStatus" + e);
        }
        return result;

    }

    public String changeUserStatus(int userId, String userStatus) {
        String result = "failed to change user status";
        if (userStatus.equalsIgnoreCase("active")) {
            userStatus = "inactive";
        } else {
            userStatus = "active";
        }
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("update usermaster set user_status=? where user_id=?");
            pstmt.setString(1, userStatus);
            pstmt.setInt(2, userId);
            System.out.println("vghxsvahg" + pstmt.toString());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "Status Changed successfully";

            }
        } catch (Exception e) {
            System.out.println("Exception in changeUserStatus" + e);
        }
        return result;

    }

}
