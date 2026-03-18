package lostandfoundapp;

public class FoundItem extends Item {

    private String finderName;

    public FoundItem(int id, String name, String description, String location, String date, String finderName) {
        super(id, name, description, location, date);
        this.finderName = finderName;
    }

    public String getFinderName() {
        return finderName;
    }

    @Override
    public String getType() {
        return "Found";
    }
}
