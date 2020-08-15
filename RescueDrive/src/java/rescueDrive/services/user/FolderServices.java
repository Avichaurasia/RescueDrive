/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.services.user;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rescueDrive.beans.user.FolderBean;
import rescueDrive.services.common.ConnectDB;

/**
 *
 * @author User
 */
public class FolderServices {

    public List<FolderBean> getAllFolders(int userId, int folderId, int isStarred) {
        List<FolderBean> lstFolders = new ArrayList<>();
        StringBuffer str = new StringBuffer("select * from foldermaster where user_id=? and parent_folder_id=?");
        if (isStarred == 1) {
            str.append(" and is_starred = " + isStarred);
        }
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement(str.toString());
            pstmt.setInt(1, userId);
            pstmt.setInt(2, folderId);
            System.out.println("sfgsfgfdgdf " + pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FolderBean obj = new FolderBean();
                obj.setDescription(rs.getString("description"));
                obj.setCreationDate(rs.getString("creation_date"));
                obj.setFolderId(rs.getInt("folder_id"));
                obj.setFolderName(rs.getString("folder_name"));
                obj.setIsStarred(rs.getInt("is_starred"));
                obj.setParentFolderId(rs.getInt("parent_folder_id"));
                obj.setUserId(rs.getInt("user_id"));
                lstFolders.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllFolders" + e);
        }
        return lstFolders;
    }

    public void deleteFolder(String path) {
        File f = new File(path);
        File[] farry = f.listFiles();
        for (File f1 : farry) {
            if (f1.isDirectory()) {
                deleteFolder(f1.getAbsolutePath());
            }
            if (f1.isFile()) {
                f1.delete();
            }
            f1.delete();
        }
        f.delete();
    }

    public String createFolder(rescueDrive.beans.user.FolderBean obj) {
        String result = "failed to create folder";
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("insert into foldermaster (folder_name,description,creation_date,is_starred,parent_folder_id,user_id) values(?,?,sysdate(),0,-1,?)");
            pstmt.setString(1, obj.getFolderName());
            pstmt.setString(2, obj.getDescription());
            pstmt.setInt(3, obj.getUserId());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "folder created successfully";
            }

        } catch (Exception e) {
            System.out.println("Exception in CreateFolder" + e);

        }
        return result;

    }

    public int getParentFolderId(int userId) {
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select folder_id from foldermaster where user_id=? and parent_folder_id=-1");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Exception in getParentFolderId" + e);
        }
        return 0;
    }

    public int getParentFolderIdByFolderId(int folderId, int userId) {
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select parent_folder_id from foldermaster where user_id=? and folder_id=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, folderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Exception in getParentFolderId" + e);
        }
        return 0;
    }

    public String getFolderName(int folderId, int userId) {
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select folder_name from foldermaster where user_id=? and folder_id=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, folderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Exception in getParentFolderId" + e);
        }
        return "Nothing";
    }

    public String changeIsStarred(int folderId, int isStarred) {
        String result = "failed to change isStarred";
        if (isStarred == 0) {
            isStarred = 1;
        } else {
            isStarred = 0;
        }
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("update foldermaster set is_starred=? where folder_id=?");
            pstmt.setInt(1, isStarred);
            pstmt.setInt(2, folderId);
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

    /*public List<FolderBean> showStarredFolders(int isStarred) {
     List<FolderBean> lstStarredFolders = new ArrayList<>();
     try (Connection conn = ConnectDB.connect();) {
     PreparedStatement pstmt = conn.prepareStatement("select * from foldermaster fd inner join filemaster fn on fd.parent_folder_id=fn.folder_id where is_starred=?");
     pstmt.setInt(1, isStarred);
     ResultSet rs = pstmt.executeQuery();
     while (rs.next()) {
     FolderBean obj = new FolderBean();
     obj.setDescription(rs.getString("description"));
     obj.setCreationDate(rs.getString("creation_date"));
     obj.setFolderId(rs.getInt("folder_id"));
     obj.setFolderName(rs.getString("folder_name"));
     obj.setIsStarred(rs.getInt("is_starred"));
     obj.setParentFolderId(rs.getInt("parent_folder_id"));
     obj.setUserId(rs.getInt("user_id"));
     lstStarredFolders.add(obj);

     }
     } catch (Exception e) {
     System.out.println("Exception in ShowAllFolders" + e);
     }
     return lstStarredFolders;

     }*/
    public String deleteFolder(String ids[]) {
        int check1 = 0;
        int check2 = 0;
        PreparedStatement pstmt = null;
        try (Connection conn = ConnectDB.connect();) {
            for (int i = 0; i < ids.length; i++) {
                int folderId = Integer.parseInt(ids[i]);
                pstmt = conn.prepareStatement("delete from foldermaster where folder_id=?");
                pstmt.setInt(1, folderId);
                int j = pstmt.executeUpdate();
                if (j > 0) {
                    pstmt = conn.prepareStatement("select folder_id from foldermaster where parent_folder_id=?");
                    pstmt.setInt(1, folderId);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        pstmt = conn.prepareStatement("delete from foldermaster where folder_id=?");
                        pstmt.setInt(1, rs.getInt(1));
                        pstmt.executeUpdate();
                        pstmt = conn.prepareStatement("select file_id from filemaster where folder_id=?");
                        pstmt.setInt(1, rs.getInt(1));
                        ResultSet rsF = pstmt.executeQuery();
                        while (rsF.next()) {
                            pstmt = conn.prepareStatement("delete from filemaster where file_id=?");
                            pstmt.setInt(1, rsF.getInt(1));
                            pstmt.executeUpdate();
                        }
                    }
                }
                pstmt = conn.prepareStatement("select file_id from filemaster where folder_id=?");
                pstmt.setInt(1, folderId);
                ResultSet rs2 = pstmt.executeQuery();
                while (rs2.next()) {
                    pstmt = conn.prepareStatement("delete from filemaster where file_id=?");
                    pstmt.setInt(1, rs2.getInt(1));
                    pstmt.executeUpdate();
                }
            }
            return "Folder Deleted Successfully";
        } catch (Exception e) {
            System.out.println("Exception in DeleteFolder" + e);
        }
        return "failed to delete";
    }

    public String addFolder(rescueDrive.beans.user.FolderBean obj) {
        String result = "failed to create folder";
        PreparedStatement pstmt = null;
        try (Connection conn = ConnectDB.connect();) {
            pstmt = conn.prepareStatement("insert into foldermaster (folder_name,description,creation_date,is_starred,parent_folder_id,user_id) values(?,?,sysdate(),0,?,?)");
            pstmt.setString(1, obj.getFolderName());
            pstmt.setString(2, obj.getDescription());
            pstmt.setInt(3, obj.getParentFolderId());
            pstmt.setInt(4, obj.getUserId());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "folder created successfully";
            }
        } catch (Exception e) {
            System.out.println("Exception in addFolder" + e);
        }
        return result;
    }

    public String renameFolder(rescueDrive.beans.user.FolderBean obj) {
        String result = "failed to rename folder";
        PreparedStatement pstmt = null;
        try (Connection conn = ConnectDB.connect();) {
            pstmt = conn.prepareStatement("update foldermaster  set folder_name = ? where folder_id = ?");
            pstmt.setString(1, obj.getFolderName());
            pstmt.setInt(2, obj.getFolderId());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "folder Renamed successfully";
            }
        } catch (Exception e) {
            System.out.println("Exception in renameFolder" + e);
        }
        return result;
    }

    public int getMaxFolderId(int userId) {
        PreparedStatement pstmt = null;
        try (Connection conn = ConnectDB.connect();) {
            pstmt = conn.prepareStatement("select max(folder_id) from foldermaster where user_id=?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);

            }

        } catch (Exception e) {
            System.out.println("Exception in getMaxFolderId" + e);
        }
        return 0;

    }
}
