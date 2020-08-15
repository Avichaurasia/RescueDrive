/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rescueDrive.services.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rescueDrive.beans.admin.CountryBean;
import rescueDrive.beans.admin.StateBean;

/**
 *
 * @author User
 */
public class StateServices {
    public List<StateBean> getAllStates()
    {
        List<StateBean> lstState=new ArrayList<>();
         try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from state");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StateBean obBean=new StateBean();
                obBean.setStateId(rs.getInt("state_id"));
                obBean.setStateName(rs.getString("state_name"));
                lstState.add(obBean);
                
        
        
        
    }
    
    
}
         catch(Exception e)
         {
             System.out.println("Exception in getAllStates"+e);
         }
        return lstState;
    }
   
    
    
}
