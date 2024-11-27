# Inventory Management System

An advanced **Inventory Management System** implemented in Java, utilizing the **Java Collections Framework** for efficient operations. This system supports inventory tracking, category-based sorting, restocking notifications, bulk operations, and an interactive console interface for seamless management.

---

## Features

### 1. Inventory Management
- Add, search, update, and delete items by their unique ID.
- Each item has the following attributes:
  - **ID**: Unique identifier for the item.
  - **Name**: Name of the item.
  - **Category**: Category of the item (e.g., Electronics, Furniture, Groceries).
  - **Quantity**: Number of units available.

### 2. Category-based Sorting and Retrieval
- Items within each category are sorted in descending order by quantity.
- Easily retrieve all items in a specific category.

### 3. Restocking Notifications
- Notifies (console output) when any item’s quantity falls below a specified threshold.

### 4. Bulk Inventory Merging
- Merge two inventories while ensuring:
  - No duplicate IDs exist.
  - If duplicate IDs are found, retain the item with the higher quantity.

### 5. Top K Items Retrieval
- Retrieve the top **K items** with the highest quantities, irrespective of category.

### 6. Display All Products
- View a complete list of all items in the inventory.

---


