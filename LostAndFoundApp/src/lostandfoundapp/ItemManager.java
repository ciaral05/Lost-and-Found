package lostandfoundapp;

import java.util.ArrayList;

public class ItemManager implements CRUDOperations<Item> {

    private ArrayList<Item> items;

    public ItemManager() {
        items = new ArrayList<>();
    }

    @Override
    public void create(Item item) {
        items.add(item);
    }

    @Override
    public Item read(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Item newItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.set(i, newItem);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public ArrayList<Item> searchItems(String keyword) {
        ArrayList<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (item.matchesKeyword(keyword)) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }
}
