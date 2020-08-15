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

/**
 *
 * @author User
 */
public class CountryServices {
    public List<CountryBean> getAllCountries()
    {
        List<CountryBean> lstCount=new ArrayList<>();
         try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from country");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CountryBean obBean=new CountryBean();
                obBean.setCountryId(rs.getInt("country_id"));
                obBean.setCountryName(rs.getString("country_name"));
                lstCount.add(obBean);
                
        
        
        
    }
    
    
}
         catch(Exception e)
         {
             System.out.println("Exception in getAllCountries"+e);
         }
        return lstCount;
    }
   
}
