package com.example.grocerylistmanager;

import androidx.room.*;

import java.util.List;

@Dao
public interface GroceryListDao {
    @Query("SELECT * FROM grocery_list")
    List<GroceryListTable> getLists();

    @Insert
    void addList(GroceryListTable groceryList);

    @Query("Select * from grocery_list where list_name = :name")
    GroceryListTable getListByName(String name);

    @Query("Delete from grocery_list where list_name like :name;")
    void deleteListByName(String name);

    @Query("update item set list_name = null where list_name = :listName")
    void updateItemsInList(String listName);

    @Update
    void updateList(GroceryListTable groceryListTable);

    @Query("UPDATE grocery_list set list_name = :newName where list_name = :oldName")
    void renameList(String oldName, String newName);

    @Delete
    void deleteList(GroceryListTable groceryListTable);

}
