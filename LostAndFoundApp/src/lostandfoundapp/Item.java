package lostandfoundapp;

public abstract class Item {

    protected int id;
    protected String name;
    protected String description;
    protected String location;
    protected String date;

    public Item(int id, String name, String description, String location, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public abstract String getType();

    public boolean matchesKeyword(String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase()) ||
               description.toLowerCase().contains(keyword.toLowerCase()) ||
               location.toLowerCase().contains(keyword.toLowerCase());
    }

    public boolean isMatch(Item other) {
        return this.name.equalsIgnoreCase(other.name) &&
               this.location.equalsIgnoreCase(other.location);
    }
}