import java.util.*;
import java.util.stream.Collectors;

public class CategoryManager {
    private TreeMap<Integer,List<Item>> itemsByQuantity;

    public CategoryManager(){
        this.itemsByQuantity = new TreeMap<>(Comparator.reverseOrder());
    }
    public void addItem(Item item){
        itemsByQuantity.computeIfAbsent(item.getQuantity(),k->new ArrayList<>()).add(item);
    }
    public void removeItem(Item item){
        List<Item> items = itemsByQuantity.get(item.getQuantity());
        if (items != null){
            items.removeIf(i->i.getId() == item.getId());
            if (items.isEmpty()){
                itemsByQuantity.remove(item.getQuantity());
            }
        }
    }
    public List<Item> getItems(){
        return itemsByQuantity.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
