package com.example.grocerylistmanager;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity{
    public static GroceryListDatabase groceryListDatabase;
    Intent intent;
    Button returnBut;
    CheckBox check;
    String searchField;

    RecyclerView recycleSearch;
    RecycleAdapterSearch searchAdapter;
    TextView spefRes;
    ArrayList<String> brand, type;
    public static ArrayList<Boolean> selectItem = new ArrayList<>();
    public static ArrayList<Integer> itemss = new ArrayList<>();
    ArrayList<Float> quantities = new ArrayList<>();
    ArrayList<String> units = new ArrayList<>();
    ArrayList<Boolean> booleans = new ArrayList<>();
    String listClicked = "";
    Context context;
    Button noRes;
    Button addTL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        groceryListDatabase = Room.databaseBuilder(getApplicationContext(), GroceryListDatabase.class, "grocerylistdb")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        context = this;
        brand = new ArrayList<>();
        type = new ArrayList<>();

//        brand.add("Generic");
//        type.add("Banana");

        intent = getIntent();
        searchField = intent.getStringExtra("SearchName");

        setTitle("Search: " + searchField);

        returnBut = findViewById(R.id.returnBut);



//        for (boolean i : selectItem){
            Log.d("deez", String.valueOf(selectItem.size()));
//        }

        spefRes = findViewById(R.id.spefRes);
        spefRes.setVisibility(View.INVISIBLE);

        returnBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Log.d("title", listClicked);
//                GroceryItemTable groceryItemTable = new GroceryItemTable();
//                for(int i = 0; i < brand.size(); i++){
//                    //Toast.makeText(getApplicationContext(), brand.size()+"", Toast.LENGTH_SHORT).show();
//                    if(listClicked.equals(brand.get(i))){
//                        Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
//                        groceryItemTable.setItemName("addedededed");
//                        groceryItemTable.setItemType("asffaftypeeeee");
//                        groceryItemTable.setQuantity((float) 1.0);
//                        groceryItemTable.setUnits("gallons");
//                        groceryItemTable.setChecked(false);
//                        groceryListDatabase.groceryItemDao().addItem(groceryItemTable);
//                    }
//                }
                //Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        recycleSearch = findViewById(R.id.recycleViewSearchResults);
        recycleSearch.setLayoutManager(new LinearLayoutManager(this));

        searchAdapter = new RecycleAdapterSearch(this, brand, type, selectItem);
        String firstLetter = "";
        if (searchField.length() < 3){
            spefRes.setVisibility(View.VISIBLE);
            firstLetter = searchField.substring(0, 1) + '%';
        }
        else firstLetter = searchField.substring(0, 2) + '%';
        Log.d("tag", "onCreate: " + firstLetter);

        listClicked = intent.getStringExtra("list");
        Log.d("meh2",listClicked);

        final List<GroceryItemTable> searchResultItems = groceryListDatabase.groceryItemDao().getSearchResults(firstLetter);
        Log.d("meh", "onCreate: " + searchResultItems.size());
//        for (GroceryItemTable git : searchResultItems){
//            if (git.isChecked()){
//                selectItem.add(true);
//            }
//            else selectItem.add(false);
//        }
//        if (check.isChecked()) selectItem.add(true);


        // if no results found for item user wanted ask them to do something about it
        noRes = findViewById(R.id.noResultsButton);
        noRes.setVisibility(View.INVISIBLE);

        addTL = findViewById(R.id.addToListButton);


        if (searchResultItems.size() == 0){
            noRes.setVisibility(View.VISIBLE);
            addTL.setVisibility(View.INVISIBLE);
        }
        noRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }

        });


        for (GroceryItemTable git : searchResultItems) {

            brand.add(git.getItemName());
            type.add(git.getItemType());
//            if (git.isChecked())
//                check.setChecked(true);
        }
        addTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (GroceryItemTable git : searchResultItems) {
                    GroceryItemTable git2 = new GroceryItemTable();
                    git2.setItemName(git.getItemName());
                    git2.setList_name(listClicked);
                    git2.setItemType(git.getItemType());
                    git2.setUnits(git.getUnits());
                    groceryListDatabase.groceryItemDao().addItem(git2);

                }

                searchAdapter.notifyDataSetChanged();


            }
        });


// */
        recycleSearch.setAdapter(searchAdapter);

    }
    public void  showAlert(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Item You Were Looking For");
        final EditText searchItem = new EditText(this);
        alert.setView(searchItem);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

//                Intent intent = new Intent(context, SearchActivity.class);
//                intent.putExtra("SearchItem", searchItem.getText().toString());
//                startActivityForResult(intent, 1);
                intent = getIntent();
                intent.putExtra("itemName", searchItem.getText().toString());
                    show2Alert();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }
    public void  show2Alert(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Suitable Item Type eg. 'dairy'");
        final EditText searchItem = new EditText(this);
        alert.setView(searchItem);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

//                Intent intent = new Intent(context, SearchActivity.class);
//                intent.putExtra("SearchItem", searchItem.getText().toString());
//                startActivityForResult(intent, 1);
                intent = getIntent();
                intent.putExtra("itemType", searchItem.getText().toString());

                GroceryItemTable groceryItemTable = new GroceryItemTable();
                groceryItemTable.setItemName(intent.getStringExtra("itemName"));
                groceryItemTable.setItemType(intent.getStringExtra("itemType"));
                groceryListDatabase.groceryItemDao().addItem(groceryItemTable);

                finish();
                startActivity(getIntent());
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
//        groceryListDatabase.groceryItemDao().setItemTypeOfItem(intent.getStringExtra("itemName"));


    }





}

