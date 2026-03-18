package lostandfoundapp;

public class LostItem extends Item {

    private String ownerName;

    public LostItem(int id, String name, String description, String location, String date, String ownerName) {
        super(id, name, description, location, date);
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String getType() {
        return "Lost";
    }
}
