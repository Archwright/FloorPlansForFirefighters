package fp4f.floorplans;

public class AddressItem {
    public int HouseId;
    public String Address;

    public AddressItem() {
        HouseId = -1;
        Address = "";
    }

    public AddressItem(int houseId, String address) {
        HouseId = houseId;
        Address = address;
    }

    @Override
    public String toString()
    {
        return Address;
    }
}