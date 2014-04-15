/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fp4f.floorplans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author river
 */
public class DatabaseLayer {
    
    Connection con;
    DatabaseLayer() {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:floorplans.sqlite");
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    ArrayList<AddressItem> getMatchingAddresses(String address)
    {
        ArrayList items = new ArrayList<AddressItem>();
        String[] params = address.trim().split(" ");
        ArrayList list = new ArrayList<String>();
        list.add("select * from houses");
        if(params.length > 0) list.add("where");
        
        for(int i = 0; i < params.length; i++)
        {
            list.add("address like ?");
            if(i + 1 != params.length) list.add("and");
        }
        list.add("order by address");
        String query = StringUtils.join(list.toArray(), " ");
        
        try {
            
            PreparedStatement getter = con.prepareStatement(query);
            for(int i = 0; i < params.length; i++) {
                getter.setString(i+1, "%" + params[i] + "%");
            }
            ResultSet rs = getter.executeQuery();
            while(rs.next()) {
                AddressItem item = new AddressItem();
                item.Address = rs.getString("address");
                item.HouseId = rs.getInt("id");
                items.add(item);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return items;
    }

    HouseItem getHouse(AddressItem selected) {
        HouseItem house = new HouseItem();
        try {
            PreparedStatement getter = con.prepareStatement("select * from houses where id = ?");
            getter.setInt(1, selected.HouseId);
            ResultSet rs = getter.executeQuery();
            
            house.Id = rs.getInt("id");
            
            house.Address = rs.getString("address");
            house.Phone = rs.getString("phone");
            
            house.Adults = rs.getInt("adults");
            house.Children = rs.getInt("children");
            house.Elderly = rs.getInt("elderly");
            house.Pets = rs.getInt("pets");
            house.Salvage = rs.getInt("salvage");
            house.Complexity = rs.getInt("complexity");
            
            house.Weapons = rs.getBoolean("weapons");
            
            house.Disabilities = rs.getString("disability");
            house.Combustables = rs.getString("combustables");
            house.Community = rs.getString("community");
            
            house.Floors = getFloors(house.Id);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return house;
    }
    
    ArrayList<FloorItem> getFloors(int houseId) {
        ArrayList<FloorItem> floors = new ArrayList<FloorItem>();
        try {
            PreparedStatement getter = con.prepareStatement("select * from floors where house = ? order by level desc");
            getter.setInt(1, houseId);
            ResultSet rs = getter.executeQuery();
            
            while(rs.next()) {
                FloorItem floor = new FloorItem();
                floor.Id = rs.getInt("id");
                floor.HouseId = houseId;
                floor.Level = rs.getInt("level");
                floor.FloorPlan = rs.getString("image");
                floor.load();
                
                floors.add(floor);
            }
            
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        return floors;
    }
}
