public class Item {
    private int id;
    private String name;
    private String category;
    private int quantity;

    public Item(int id,String name,String category,int quantity){
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override
    public String toString(){
        return String.format("Item{id=%d, name='%s', category='%s', quantity=%d}", id, name, category, quantity);
    }
}
