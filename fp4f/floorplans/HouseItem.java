/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fp4f.floorplans;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author river
 */
public class HouseItem {
    public int Id;
    public String Address;
    public String Phone;
    
    public int Adults;
    public int Children;
    public int Elderly;
    public int Pets;
    
    public boolean Weapons;
    public String Combustables;
    public String Disabilities;
    
    public int Salvage;
    public int Complexity;
    public String Community;
    
    public ArrayList<FloorItem> Floors;
    
    
    public HouseItem() {
        Id = -1;
        Floors = new ArrayList<FloorItem>();
    }
    
    public int getOccupants() {
        return Adults + Children + Elderly;
    }
    
    @Override
    public String toString() {
        if(Id < 0) return "Invalid House";
        
        ArrayList<String> details = new ArrayList<String>();
        //details.add(Address);
        
        boolean hasBoom = Combustables != null && !Combustables.isEmpty();
        if(Weapons || hasBoom) {
            details.add("--Alerts--");
            if(Weapons) details.add("House Contains Firearms.");
            if(hasBoom) details.add("House Contains Combustables.");
            details.add("");
        }
        
        details.add("Complexity: " + Complexity);
        details.add("");
        details.add("--Occupants--");
        if(Adults > 0) details.add("Adults: " + Adults);
        if(Children > 0) details.add("Children: " + Children);
        if(Elderly > 0) details.add("Elders: " + Elderly);
        details.add("Total: " + getOccupants());
        
        if(Pets > 0)  {
            details.add("");
            details.add("--Pets--");
            details.add("Pets: " + Pets);
        }
        
        boolean hasDisablilities = Disabilities != null && Disabilities.length() > 0;
        if(hasDisablilities || hasBoom) {
            details.add("");
            details.add("--Complications--");
            if(hasDisablilities) details.add(Disabilities);
            if(hasBoom) details.add(Combustables);
        }
        
        details.add("");
        details.add("--Other--");
        details.add("Phone: " + formatPhone());
        if(Salvage > 0) details.add("Salvage: " + Salvage);
        if(Community != null && !Community.isEmpty()) details.add("Community: " + Community);
        return StringUtils.join(details, System.getProperty("line.separator"));
    }
    
    private String formatPhone() {
        if(Phone == null) return "n/a";
        
        String raw = Phone;
        String formatted = "";
        switch(Phone.length()) {
            case 10:
                raw = Phone.substring(3);
                formatted = "(" + Phone.substring(0, 3) + ") ";
            case 7:
                formatted = formatted + raw.substring(0, 3) + "-" + raw.substring(3);
                break;
            default:
                formatted = "n/a";
                break;
        }
        
        return formatted;
    }
}
