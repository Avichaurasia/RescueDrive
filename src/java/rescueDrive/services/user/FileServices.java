/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.services.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rescueDrive.beans.user.FileBean;
import rescueDrive.beans.user.ShareBean;
import rescueDrive.properties.ReadFromPropertiesFile;
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

    public String transferFile(String input) {
        try {
            System.out.println("input:: " + input);
            File f = new File(input);
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream("F:\\Apache Tomcat 7.0.41\\webapps\\RescueDriveData\\download\\" + f.getName());
            byte[] buffer = new byte[4096];
            int read = 0;
            while ((read = fis.read(buffer, 0, 4096)) != -1) {
                fos.write(buffer, 0, read);
            }
            fis.close();
            fos.close();
            return "Success";
        } catch (Exception e) {
            System.out.println("trnasfer: " + e);
        }
        return "Failed";
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

    public FileBean getFileDetail(int code) {
        FileBean obj = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from filemaster where file_id = (select file_id from file_share_master where share_path_code = " + code + ")");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new FileBean();
                obj.setCreationDate(rs.getString("creation_date"));
                obj.setDescription(rs.getString("description"));
                obj.setFileId(rs.getInt("file_id"));
                obj.setFileName(rs.getString("file_name"));
                obj.setFileSize(rs.getInt("file_size"));
                obj.setFolderId(rs.getInt("folder_id"));
                obj.setIsStarred(rs.getInt("is_starred"));
                obj.setUserId(rs.getInt("user_id"));
            }
        } catch (Exception e) {
            System.out.println("Exception in getFileDetail" + e);
        }
        return obj;
    }

    public String shareFile(ShareBean obj) {
        String result = "Failed to share";

        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("insert into file_share_master(file_id,share_date,user_id,share_path_code,is_password_protected,password,emails_shared_with)values(?,sysdate(),?,?,1,?,?)");
            pstmt.setInt(1, obj.getFileId());
            pstmt.setInt(2, obj.getUserId());
            pstmt.setString(3, obj.getSharePathCode());
            pstmt.setString(4, obj.getPassword());
            pstmt.setString(5, obj.getEmailsSharedwith());
            pstmt.executeUpdate();
            result = "Files Shared Successfully";
        } catch (Exception e) {
            System.out.println("Exception in getFileDetail" + e);
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

    public String uploadFile(String fileName, String description, int folderId, int fileSize, int userId) {
        String result = "Failed";
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("insert into filemaster(file_name,creation_date,file_size,description,is_starred,folder_id,user_id) values(?,sysdate(),?,?,?,?,?)");
            pstmt.setString(1, fileName);
            pstmt.setInt(2, fileSize);
            pstmt.setString(3, description);
            pstmt.setInt(4, 0);
            pstmt.setInt(5, folderId);
            pstmt.setInt(6, userId);
            int i = pstmt.executeUpdate();
            System.out.println("query^^^^^^^^^: " + pstmt.toString());

            if (i > 0) {
                result = "File Upload Successfully Successfully";
            }

        } catch (Exception e) {
            System.out.println("updateFile():" + e);
        }
        return result;
    }

}
