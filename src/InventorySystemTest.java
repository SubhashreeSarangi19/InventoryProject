import java.util.Scanner;

public class InventorySystemTest {
    public static void main(String[] args) {
        InventorySystem inventory = new InventorySystem(10);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Inventory Management System!");

        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add an item");
            System.out.println("2. Search for an item by ID");
            System.out.println("3. Update an item by ID");
            System.out.println("4. Delete an item by ID");
            System.out.println("5. View items by category");
            System.out.println("6. View top K items by quantity");
            System.out.println("7. Merge with another inventory");
            System.out.println("8. Show all products");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addItem(inventory, scanner);
                        break;
                    case 2:
                        searchItem(inventory, scanner);
                        break;
                    case 3:
                        updateItem(inventory, scanner);
                        break;
                    case 4:
                        deleteItem(inventory, scanner);
                        break;
                    case 5:
                        viewItemsByCategory(inventory, scanner);
                        break;
                    case 6:
                        viewTopKItems(inventory, scanner);
                        break;
                    case 7:
                        mergeInventories(inventory, scanner);
                        break;
                    case 8:
                        showAllProducts(inventory);
                        break;
                    case 9:
                        exit = true;
                        System.out.println("Exiting the Inventory Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void addItem(InventorySystem inventory, Scanner scanner) {
        try {
            System.out.print("Enter item ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            System.out.print("Enter item category: ");
            String category = scanner.nextLine();
            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();

            inventory.addItem(id, name, category, quantity);
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private static void searchItem(InventorySystem inventory, Scanner scanner) {
        try {
            System.out.print("Enter item ID to search: ");
            int id = scanner.nextInt();

            Item item = inventory.getItemById(id);
            if (item != null) {
                System.out.println("Item found: " + item);
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine();
        }
    }

    private static void updateItem(InventorySystem inventory, Scanner scanner) {
        try {
            System.out.print("Enter item ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Item item = inventory.getItemById(id);
            if (item != null) {
                System.out.print("Enter new quantity: ");
                int quantity = scanner.nextInt();
                inventory.updateItemQuantity(id, quantity);
                System.out.println("Item updated successfully!");
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private static void deleteItem(InventorySystem inventory, Scanner scanner) {
        try {
            System.out.print("Enter item ID to delete: ");
            int id = scanner.nextInt();

            if (inventory.removeItem(id)) {
                System.out.println("Item deleted successfully!");
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine();
        }
    }

    private static void viewItemsByCategory(InventorySystem inventory, Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.println("Items in category '" + category + "':");
        inventory.getItemsByCategory(category).forEach(System.out::println);
    }

    private static void viewTopKItems(InventorySystem inventory, Scanner scanner) {
        try {
            System.out.print("Enter the value of K: ");
            int k = scanner.nextInt();

            System.out.println("Top " + k + " items by quantity:");
            inventory.getTopKItems(k).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static void mergeInventories(InventorySystem mainInventory, Scanner scanner) {
        InventorySystem secondaryInventory = new InventorySystem(10);
        System.out.println("Adding sample items to the secondary inventory...");
        secondaryInventory.addItem(101, "IPad", "Electronics", 25);
        secondaryInventory.addItem(102, "Dinning Table", "Furniture", 15);
        secondaryInventory.addItem(103, "Flour", "Groceries", 30);
        System.out.println("Merging inventories...");
        mainInventory.mergeInventories(secondaryInventory);
        System.out.println("Merge complete. All items are now in the main inventory.");
    }

    private static void showAllProducts(InventorySystem inventory) {
        inventory.showAllProducts();
    }

}
