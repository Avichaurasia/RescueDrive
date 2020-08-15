/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rescueDrive.beans.user;

/**
 *
 * @author User
 */
public class ShareBean {
    private int shareId,fileId,userId,isPassowrdProtected;
   
    private String shareDate,password,sharePathCode,emailsSharedwith;

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsPassowrdProtected() {
        return isPassowrdProtected;
    }

    public void setIsPassowrdProtected(int isPassowrdProtected) {
        this.isPassowrdProtected = isPassowrdProtected;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSharePathCode() {
        return sharePathCode;
    }

    public void setSharePathCode(String sharePathCode) {
        this.sharePathCode = sharePathCode;
    }

    public String getEmailsSharedwith() {
        return emailsSharedwith;
    }

    public void setEmailsSharedwith(String emailsSharedwith) {
        this.emailsSharedwith = emailsSharedwith;
    }
    
    
    
}
