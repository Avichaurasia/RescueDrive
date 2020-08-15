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
import rescueDrive.beans.admin.CityBean;
import rescueDrive.beans.admin.CountryBean;

/**
 *
 * @author User
 */
public class CityServices {
    public List<CityBean> getAllCities()
    {
        List<CityBean> lstCity=new ArrayList<>();
         try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from city");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CityBean obBean=new CityBean();
                obBean.setCityId(rs.getInt("city_id"));
                obBean.setCityName(rs.getString("city_name"));
                lstCity.add(obBean);
                
        
        
        
    }
    
    
}
         catch(Exception e)
         {
             System.out.println("Exception in getAllCountries"+e);
         }
        return lstCity;
    }
    
    
}
