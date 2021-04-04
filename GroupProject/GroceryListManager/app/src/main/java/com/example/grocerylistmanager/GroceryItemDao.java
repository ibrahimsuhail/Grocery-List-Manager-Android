package com.example.grocerylistmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GroceryItemDao {
    @Query("SELECT * FROM item")
    List<GroceryItemTable> getItems();

    @Query("SELECT * FROM item where item_type like :itemType")
    List<GroceryItemTable> getItemsInItemType(String itemType);

    @Query("select * from item where list_name like :list")
    List<GroceryItemTable> getItemsOfList(String list);

    @Insert
    void addItem(GroceryItemTable groceryItemTable);

    @Query("update item set list_name = '' where list_name = :listName;")
    void removetemsInList(String listName);

    @Delete
    void deleteItem(GroceryItemTable groceryItemTable);

    @Query("Select DISTINCT * from item where name like :firstLetter group by name   ")
    List<GroceryItemTable> getSearchResults(String firstLetter);

    @Query("update item set item_type = :typeOfItem")
    void setItemTypeOfItem(String typeOfItem);

    @Query("Update item set list_name = :listName where list_name = :oldName")
    void setListName(String listName, String oldName);

    @Query("Update item set list_name = :listName where name = :itemName ")
    void addToList(String itemName , String listName);

    @Query("update item set quantity = :newQ where list_name = :ln")
    void updateQinList(float newQ, String ln);

    @Query("update item set list_name = null where name = :n and list_name = :ln")
    void deleteItemByName(String n, String ln);

}
