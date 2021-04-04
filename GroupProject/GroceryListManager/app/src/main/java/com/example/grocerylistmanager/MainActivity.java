package com.example.grocerylistmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static GroceryListDatabase groceryListDatabase;

    RecyclerView listView;
    RecycleAdapter recAdapter;

    ArrayList<String> titles = new ArrayList<>();
    Button but;
    Button addDummyData;
    EditText newListName;
// .createFromAsset("database/grocery.db")
    // .fallbackToDestructiveMigration()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        File file = new File("database/grocery.db");
        groceryListDatabase = Room.databaseBuilder(getApplicationContext(), GroceryListDatabase.class, "grocerylistdb")
//                .createFromFile(file)
//                .createFromAsset("database/grocery.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
//        titles.add("Stop n Shop");
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        EditText nameField = findViewById(R.id.listNameField);
        listView = findViewById(R.id.recyclerViewList);

        nameField.setWidth(dm.widthPixels - nameField.getWidth() - 500);

        listView.setMinimumHeight(dm.heightPixels - nameField.getHeight());
        listView.setLayoutManager(new LinearLayoutManager(this));

        recAdapter = new RecycleAdapter(this, titles);
        listView.setAdapter(recAdapter);

        List<GroceryListTable> groceryLists = groceryListDatabase.groceryListDao().getLists();

        for (GroceryListTable list : groceryLists) {
            titles.add(list.getName());

            Log.d("TAG", list.getName());
        }


        Log.d("list size", String.valueOf(titles.size()));


//        titles.add("Western Beef");


        final EditText newListName = findViewById(R.id.listNameField);


        but = findViewById(R.id.addButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newListName.getText().toString().equals("")) {
                    String listName = newListName.getText().toString();

                    titles.add(listName);
                    newListName.setText("");
                    recAdapter.notifyDataSetChanged();

                    GroceryListTable groceryListTable = new GroceryListTable();
                    groceryListTable.setName(listName);
                    groceryListDatabase.groceryListDao().addList(groceryListTable);

                    Toast.makeText(getApplicationContext(), listName + " Successfully Created", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // ---->

        // DELETING ITEMS IF U PRESS ADD DUMMY DATA TWICE
//        List<GroceryListTable> groceryListTables = groceryListDatabase.groceryListDao().getLists();
//        for (GroceryListTable groceryListTable : groceryListTables){
//            groceryListDatabase.groceryListDao().deleteList(groceryListTable);
//        }
//        List<GroceryItemTable> groceryItemTables = groceryListDatabase.groceryItemDao().getItems();
//        Log.d("items size", String.valueOf(groceryItemTables.size()));
//        for (GroceryItemTable groceryItemTable : groceryItemTables){
//            groceryListDatabase.groceryItemDao().deleteItem(groceryItemTable);
//        }

        // <----



        addDummyData = findViewById(R.id.dummyData1);
        addDummyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroceryListTable groceryListTable = new GroceryListTable();
                groceryListTable.setName("Walmart");
                groceryListDatabase.groceryListDao().addList(groceryListTable);
                titles.add("Walmart");
                GroceryListTable groceryListTable2 = new GroceryListTable();
                groceryListTable2.setName("ShopRite");
                groceryListDatabase.groceryListDao().addList(groceryListTable2);
                titles.add("ShopRite");
                GroceryListTable groceryListTable3 = new GroceryListTable();
                groceryListTable3.setName("Key Food");
                groceryListDatabase.groceryListDao().addList(groceryListTable3);
                titles.add("Key Food");

                GroceryItemTable groceryItemTable = new GroceryItemTable();
                groceryItemTable.setItemName("apple");
                groceryItemTable.setList_name("Key Food");
                groceryItemTable.setQuantity((float) 2.34);
                groceryItemTable.setUnits("lb");
                groceryItemTable.setItemType("fruits");
                groceryItemTable.setChecked(true);


                GroceryItemTable groceryItemTable2 = new GroceryItemTable();
                groceryItemTable2.setItemName("almonds");
                groceryItemTable2.setList_name("ShopRite");
                groceryItemTable2.setQuantity((float) 3.50);
                groceryItemTable2.setUnits("lb");
                groceryItemTable2.setItemType("nuts");
                groceryItemTable2.setChecked(false);


                GroceryItemTable groceryItemTable3 = new GroceryItemTable();
                groceryItemTable3.setItemName("asparagus");
                groceryItemTable3.setList_name("ShopRite");
                groceryItemTable3.setQuantity((float) 1.50);
                groceryItemTable3.setUnits("lb");
                groceryItemTable3.setItemType("vegetables");
                groceryItemTable3.setChecked(false);


                GroceryItemTable groceryItemTable4 = new GroceryItemTable();
                groceryItemTable4.setItemName("Derle Farms Milk");
                groceryItemTable4.setList_name("Walmart");
                groceryItemTable4.setQuantity((float) 1.0);
                groceryItemTable4.setUnits("gallons");
                groceryItemTable4.setItemType("dairy");
                groceryItemTable4.setChecked(false);


                GroceryItemTable groceryItemTable5 = new GroceryItemTable();
                groceryItemTable5.setItemName("Bananas");
                groceryItemTable5.setList_name("Walmart");
                groceryItemTable5.setQuantity((float) 1.46);
                groceryItemTable5.setUnits("lb");
                groceryItemTable5.setItemType("fruits");
                groceryItemTable5.setChecked(false);


                groceryListDatabase.groceryItemDao().addItem(groceryItemTable);
                groceryListDatabase.groceryItemDao().addItem(groceryItemTable2);
                groceryListDatabase.groceryItemDao().addItem(groceryItemTable3);
                groceryListDatabase.groceryItemDao().addItem(groceryItemTable4);
                groceryListDatabase.groceryItemDao().addItem(groceryItemTable5);


                addDummyData.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int NAVIGATE_LIST = 0;
        final int RENAME_LIST = 1;
        final int DELETE_LIST = 2;

        switch (item.getItemId()) {
            case NAVIGATE_LIST:
                Intent intent = new Intent(this, listItem.class);
                intent.putExtra("Title", titles.get(item.getGroupId()));
                startActivity(intent);
                return true;
            case RENAME_LIST:
//                String ls = groceryListDatabase.groceryListDao().
//                        getListByName(titles.get(item.getGroupId())).getName();

//                List<GroceryItemTable> groceryItemTables = groceryListDatabase.groceryItemDao().
//                    getItemsOfList(titles.get(item.getGroupId()));
//                titles.remove(item.getGroupId());

                renameItem(item, item.getGroupId());

                groceryListDatabase.groceryListDao().deleteListByName(titles.get(item.getGroupId()));
//                groceryListDatabase.groceryListDao().updateItemsInList(titles.get(item.getGroupId()));



//                titles.add(item.toString());
//                Log.d("renammedm wjcbjbcic3cvh", item.toString());

//                groceryListDatabase.groceryListDao().renameList(ls, titles.get(item.getGroupId()));
//                Log.d("Renamed list", "renamed list requested: succeeded " + titles.get(item.getGroupId())
//                        + "\n");

                return true;
            case DELETE_LIST:
                groceryListDatabase.groceryListDao().deleteListByName(titles.get(item.getGroupId()));
                // removes items from the deleted list without deleting the items themselves
                groceryListDatabase.groceryItemDao().removetemsInList(
                        titles.get(item.getGroupId()));
                recAdapter.remove(item.getGroupId());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }




    public void renameItem(final MenuItem item, final int pos) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter New Name");
        final int tempPos = pos;
        final EditText newName = new EditText(this);
        alert.setView(newName);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                recAdapter.renameItem(newName.getText().toString(), tempPos);
//                List<GroceryItemTable> groceryItemTables1 = groceryListDatabase.
////                        groceryItemDao().getItemsOfList(titles.get(pos));
////                for (GroceryItemTable groceryItemTable : groceryItemTables1){
////                    groceryItemTable
////                }
                String oldName = titles.get(pos);
                recAdapter.remove(pos);
                titles.add(newName.getText().toString());
                GroceryListTable groceryListTable = new GroceryListTable();
                groceryListTable.setName(newName.getText().toString());
                groceryListDatabase.groceryListDao().addList(groceryListTable);

                groceryListDatabase.groceryItemDao().setListName(newName.getText().toString(),
                        oldName);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }

}