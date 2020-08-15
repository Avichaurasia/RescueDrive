/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.services.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rescueDrive.beans.admin.UserBean;
import rescueDrive.beans.user.FolderBean;
import rescueDrive.services.common.ConnectDB;

/**
 *
 * @author User
 */
public class UserServices {

    private UserBean obj;
    private int userId;
    private String userName;

    public UserBean getInfoUser() {
        List<UserBean> lstUsers = new ArrayList<>();
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from  usermaster un,usercontactmaster ucn where un.user_id=ucn.user_id");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserBean obj = new UserBean();

                obj.setName(rs.getString("name"));
                obj.setContact(rs.getString("phone"));
                obj.setAddress(rs.getString("address"));
                obj.setUserStatus(rs.getString("zipcode"));

                lstUsers.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Exception in getInfoUser" + e);
        }
        return obj;
    }

    public UserBean getUsersInfoById(int userId) {
        UserBean obj = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from usercontactmaster where user_id = ?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new UserBean();
                obj.setUserId(rs.getInt("user_id"));

                obj.setContact(rs.getString("phone"));
                obj.setName(rs.getString("name"));

                obj.setAddress(rs.getString("address"));
                obj.setCountry(rs.getInt("country_id"));
                obj.setState(rs.getInt("state_id"));
                obj.setCity(rs.getInt("city_id"));
                obj.setZipcode(rs.getString("zipcode"));

            }
        } catch (Exception e) {
            System.out.println("Exception in getUsersInfoById" + e);
        }
        return obj;
    }

    public String UpdateUserInfo(rescueDrive.beans.user.UserBean obj) {
        String result = "failed to upadte record";
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("update usercontactmaster set name=?,address=?,country_id=?,state_id=?,city_id=?,zipcode=? where user_id=?");
            pstmt.setString(1, obj.getName());

            pstmt.setString(2, obj.getAddress());

            pstmt.setInt(3, obj.getCountry());
            pstmt.setInt(4, obj.getState());
            pstmt.setInt(5, obj.getCity());
            pstmt.setString(6, obj.getZipcode());
            pstmt.setInt(7, obj.getUserId());
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

    public int addUser(rescueDrive.beans.user.UserBean obj) {
        String result = "failed to insert user";

        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("insert into usermaster (username,password,user_type,user_status,is_temp,registration_date)values(?,?,'user','active',1,sysdate())");
            pstmt.setString(1, obj.getUserName());
            pstmt.setString(2, obj.getPassword());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                PreparedStatement pstmt1 = conn.prepareStatement("insert into usercontactmaster values((select max(user_id) from usermaster),?,?,?,?,?,?,?)");
                pstmt1.setString(1, obj.getName());
                pstmt1.setString(2, obj.getContact());
                pstmt1.setString(3, obj.getAddress());
                pstmt1.setInt(4, obj.getCountry());
                pstmt1.setInt(5, obj.getState());
                pstmt1.setInt(6, obj.getCity());
                pstmt1.setString(7, obj.getZipcode());
                int rs = pstmt1.executeUpdate();
                if (rs > 0) {
                    result = "user inserted successfully ";
                    pstmt = conn.prepareStatement("select max(user_id) from usermaster");
                    ResultSet rs2 = pstmt.executeQuery();
                    if (rs2.next()) {
                        return rs2.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in addUser" + e);
        }
        return 0;

    }

}
