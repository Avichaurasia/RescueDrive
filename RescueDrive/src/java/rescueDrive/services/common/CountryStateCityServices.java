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
import rescueDrive.beans.admin.StateBean;
import rescueDrive.services.common.ConnectDB;

/**
 *
 * @author User
 */
public class CountryStateCityServices {

    public List<CountryBean> getAllCountries() {
        List<CountryBean> lstCountries = new ArrayList<>();
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from countrymaster");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CountryBean obBean = new CountryBean();
                obBean.setCountryId(rs.getInt("country_id"));
                obBean.setCountryName(rs.getString("country_name"));
                lstCountries.add(obBean);

            }

        } catch (Exception e) {
            System.out.println("Exception in getAllCountries():" + e);
        }
        return lstCountries;
    }

    public List<StateBean> getAllStates(int countryId) {
        List<StateBean> lstStates = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = ConnectDB.connect();) {
            PreparedStatement pstmt = conn.prepareStatement("select * from statemaster where country_id=?");
            pstmt.setInt(1, countryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StateBean obBean = new StateBean();
                obBean.setStateId(rs.getInt("state_id"));
                obBean.setStateName(rs.getString("state_name"));
                lstStates.add(obBean);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllStates" + e);
        }
        return lstStates;
    }

    public List<CityBean> getAllCities(int stateId) {
        List<CityBean> lstCities = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = ConnectDB.connect(); PreparedStatement pstmt = conn.prepareStatement("select * from citymaster where state_id=?");) {
            pstmt.setInt(1, stateId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CityBean obBean = new CityBean();
                obBean.setCityId(rs.getInt("city_id"));
                obBean.setCityName(rs.getString("city_name"));
                lstCities.add(obBean);

            }

        } catch (Exception e) {
            System.out.println("Exception in getAllCountries" + e);
        }
        return lstCities;
    }

}
