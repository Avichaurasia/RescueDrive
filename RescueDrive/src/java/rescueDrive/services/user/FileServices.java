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
import rescueDrive.beans.user.FileBean;
import rescueDrive.services.common.ConnectDB;

/**
 *
 * @author User
 */
public class FileServices {

    public List<FileBean> getAllFiles(int folderId, int userId, int isStarred) {
        List<FileBean> lstFiles = new ArrayList<>();
        StringBuffer str = new StringBuffer("select * from filemaster where folder_id=? and user_id=?");
        if (isStarred == 1) {
            str.append(" and is_starred = " + isStarred);
        }

        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement(str.toString());
            pstmt.setInt(1, folderId);
            pstmt.setInt(2, userId);
            System.out.println("sfgsfgfdgdf " + pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FileBean obj = new FileBean();
                obj.setCreationDate(rs.getString("creation_date"));
                obj.setDescription(rs.getString("description"));
                obj.setFileId(rs.getInt("file_id"));
                obj.setFileName(rs.getString("file_name"));
                obj.setFileSize(rs.getInt("file_size"));
                obj.setFolderId(rs.getInt("folder_id"));
                obj.setIsStarred(rs.getInt("is_starred"));
                obj.setUserId(rs.getInt("user_id"));
                lstFiles.add(obj);

            }
        } catch (Exception e) {
            System.out.println("Exception in getAllFiles" + e);
        }
        return lstFiles;

    }

    public String changeIsStarredFiles(int fileId, int isStarredFiles) {
        String result = "failed to change isStarred";
        if (isStarredFiles == 0) {
            isStarredFiles = 1;
        } else {
            isStarredFiles = 0;
        }
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("update filemaster set is_starred=? where file_id=?");
            pstmt.setInt(1, isStarredFiles);
            pstmt.setInt(2, fileId);
            System.out.println("vghxsvahg" + pstmt.toString());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "isStarred changed successfully";
            }

        } catch (Exception e) {
            System.out.println("Exception in changeisStarred" + e);

        }
        return result;
    }

    public String deleteFiles(String ids[]) {
        String result = "failed to delete files";
        boolean flag = false;
        try (Connection conn = ConnectDB.connect();) {
            for (int i = 0; i < ids.length; i++) {
                int fileId = Integer.parseInt(ids[i]);
                PreparedStatement pstmt = conn.prepareStatement("delete from filemaster where file_id=?");
                pstmt.setInt(1, fileId);
                System.out.println("vghxsvahg" + pstmt.toString());
                pstmt.executeUpdate();
                flag = true;
            }
            if (flag) {
                result = "files deleted successfully";
            }
        } catch (Exception e) {
            System.out.println("Exception in DeleteFiles" + e);
        }
        return result;

    }
    


}
