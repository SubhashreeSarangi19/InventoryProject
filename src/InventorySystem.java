import java.util.*;

public class InventorySystem {
    private Map<String, CategoryManager> categoryMap;
    private Map<Integer, Item> itemMap;
    private int restockThreshold;

    public InventorySystem(int restockThreshold) {
        this.categoryMap = new HashMap<>();
        this.itemMap = new HashMap<>();
        this.restockThreshold = restockThreshold;
    }

    public void addItem(int id, String name, String category, int quantity) {
        Item item = new Item(id, name, category, quantity);

        if (itemMap.containsKey(id)) {
            removeItem(id);
        }

        itemMap.put(id, item);
        categoryMap.computeIfAbsent(category, k -> new CategoryManager()).addItem(item);

        if (quantity < restockThreshold) {
            triggerRestockNotification(item);
        }
    }

    public List<Item> getItemsByCategory(String category){
        CategoryManager manager = categoryMap.get(category);
        return manager == null ? new ArrayList<>():manager.getItems();
    }

    private void triggerRestockNotification(Item item) {
        System.out.println("Restock Notification: Item '" + item.getName() + "' (ID: " + item.getId() +
                ", Category: " + item.getCategory() + ") has quantity below the threshold (" + restockThreshold + ").");
    }

    public List<Item> getTopKItems(int k){
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingInt(Item::getQuantity).reversed());
        pq.addAll(itemMap.values());

        List<Item> result = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++){
            result.add(pq.poll());
        }
        return result;
    }

    public Item getItemById(int id) {
        return itemMap.get(id);
    }

    public void updateItemQuantity(int id, int quantity) {
        if (itemMap.containsKey(id)) {
            Item item = itemMap.get(id);
            removeItem(id);
            addItem(id, item.getName(), item.getCategory(), quantity);

        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }
    public boolean removeItem(int id) {
        if (itemMap.containsKey(id)) {
            Item item = itemMap.get(id);
            itemMap.remove(id);
            categoryMap.get(item.getCategory()).removeItem(item);
            return true;
        }
        return false;
    }
    public void mergeInventories(InventorySystem otherInventory) {
        for (Item otherItem : otherInventory.itemMap.values()) {
            if (this.itemMap.containsKey(otherItem.getId())) {
                Item currentItem = this.itemMap.get(otherItem.getId());
                if (otherItem.getQuantity() > currentItem.getQuantity()) {
                    this.removeItem(currentItem.getId());
                    this.addItem(otherItem.getId(), otherItem.getName(), otherItem.getCategory(), otherItem.getQuantity());
                }
            } else {
                this.addItem(otherItem.getId(), otherItem.getName(), otherItem.getCategory(), otherItem.getQuantity());
            }
        }
    }

    public void showAllProducts() {
        if (itemMap.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            System.out.println("All products in the inventory:");
            itemMap.values().forEach(System.out::println);
        }
    }


}
