package lostandfoundapp;

import java.util.Stack;

public class UndoManager {

    private Stack<Item> deletedItems;

    public UndoManager() {
        deletedItems = new Stack<>();
    }

    public void pushDeletedItem(Item item) {
        deletedItems.push(item);
    }

    public Item undoDelete() {
        if (!deletedItems.isEmpty()) {
            return deletedItems.pop();
        }
        return null;
    }
}
