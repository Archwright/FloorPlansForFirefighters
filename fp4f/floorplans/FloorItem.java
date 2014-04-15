/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fp4f.floorplans;

import static java.awt.MediaTracker.COMPLETE;
import javax.swing.ImageIcon;

/**
 *
 * @author river
 */
public class FloorItem {
    public int Id;
    public int HouseId;
    public int Level;
    public String FloorPlan;
    
    public String FriendlyLevel;
    public ImageIcon FloorPlanImage;

    public FloorItem() {
        Id = -1;
        HouseId = -1;
        Level = 0;
        FloorPlan = "";
    }

    public FloorItem(int id, int houseId, int level) {
        Id = id;
        HouseId = houseId;
        Level = level;
        FloorPlan = "";
    }
    
    public void load() {
        // Get the right Floor Value
        if(Level < 0) {
            FriendlyLevel = "Basement - " + Math.abs(Level);
        }
        else if (Level == 0 || Level == 1) {
            FriendlyLevel = "Ground Floor";
        }
        else {
            FriendlyLevel = "Floor - " + Level;
        }
        
        FloorPlanImage = new ImageIcon(FloorPlan);
        
        if(FloorPlanImage.getImageLoadStatus() != COMPLETE)
        {
            System.out.println("Image: " + FloorPlan + " failed to load.");
        }
    }
}
