package com.example.grocerylistmanager;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class listItem extends AppCompatActivity {
        public static GroceryListDatabase groceryListDatabase;

    Intent intent;

    RecyclerView itemRecyclerView;
    RecycleAdapterList recycleAdapterList;
    ConstraintLayout bottomLayout;

    Button removeChecks, searchButton;

    Context context;

    ArrayList<String> brands = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    ArrayList<Float> quantities = new ArrayList<>();
    ArrayList<String> units = new ArrayList<>();
    ArrayList<Boolean> booleans = new ArrayList<>();

    String listClicked = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity);

        groceryListDatabase = Room.databaseBuilder(getApplicationContext(), GroceryListDatabase.class, "grocerylistdb")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();

        intent = getIntent();

        context = this;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            listClicked = extras.getString("Title");
        }

        Log.d("title", listClicked);
        setTitle(intent.getStringExtra("Title"));
        DisplayMetrics dm =  this.getResources().getDisplayMetrics();

        itemRecyclerView = findViewById(R.id.itemRecyclerVewList);
        bottomLayout = findViewById(R.id.item_bottom_layout);

        recycleAdapterList = new RecycleAdapterList(this, brands, types, quantities, units, booleans);



        ArrayList<String> items = new ArrayList<>();
        List<GroceryItemTable> groceryItemTables = groceryListDatabase.groceryItemDao().getItemsOfList(listClicked);
        Log.d("num items", String.valueOf(groceryItemTables.size()));

            for (GroceryItemTable git : groceryItemTables) {

                brands.add(git.getItemName());
                types.add(git.getItemType());
                quantities.add(git.getQuantity());
                units.add(git.getUnits());
                if (git.isChecked()) {
                    booleans.add(true);
                }
                else booleans.add(false);
            }


//*/





        //------------------------------------------------------------------

        itemRecyclerView.setMinimumHeight(dm.heightPixels-bottomLayout.getHeight());

        removeChecks = findViewById(R.id.removeCheckBut);
        searchButton = findViewById(R.id.itemSearchBut);


        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemRecyclerView.setAdapter(recycleAdapterList);

        removeChecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeChecks(listClicked);
                recycleAdapterList.notifyDataSetChanged();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }
    //reset all booleans
    public void removeChecks(String listClicked){
        for(Boolean i : booleans)
            i = false;
        List<GroceryItemTable> groceryItemTables = groceryListDatabase.groceryItemDao().getItemsOfList(listClicked);

        for (GroceryItemTable git : groceryItemTables) {
            git.setChecked(false);
        }

    }

    public void addItem(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Item To Search");
        final EditText searchName = new EditText(this);
        alert.setView(searchName);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("SearchName", searchName.getText().toString());
                intent.putExtra("list", listClicked);

                startActivityForResult(intent, 1);

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int CHANGE_QUANTITY = 0;
        final int DELETE_ITEM = 1;

        switch (item.getItemId()) {
            case CHANGE_QUANTITY:
//                brands.add(item.getGroupId(), brands.get(item.getGroupId()));
//                types.add(item.getGroupId(), types.get(item.getGroupId()));
//                quantities.add(item.getGroupId(), quantities.get(item.getGroupId()));
//                units.add(item.getGroupId(), units.get(item.getGroupId()));
//                booleans.add(item.getGroupId(), booleans.get(item.getGroupId()));
//                changeQuantity(item, item.getGroupId());
                return true;



            case DELETE_ITEM:
                recycleAdapterList.remove(item.getGroupId());

                groceryListDatabase.groceryItemDao().removetemsInList(listClicked);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void changeQuantity(MenuItem item, final int pos) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter the amount");
        final int tempPos = pos;
        final EditText newName = new EditText(this);
        alert.setView(newName);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                recycleAdapterList.newQuantity(newName.getText().toString(), tempPos);
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
