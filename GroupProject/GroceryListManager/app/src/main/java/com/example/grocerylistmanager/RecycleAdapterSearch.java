package com.example.grocerylistmanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class RecycleAdapterSearch extends RecyclerView.Adapter<RecycleAdapterSearch.MyViewHolderSearch> {

    ArrayList<String> brand, type;
    ArrayList<Boolean> selectItem;
    Context context;

    public RecycleAdapterSearch(Context context, ArrayList<String> brand, ArrayList<String> type, ArrayList<Boolean> selectItem){
        this.brand = brand;
        this.type = type;
        this.selectItem = selectItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolderSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_results_card, parent, false);
        return new MyViewHolderSearch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSearch holder, int position) {

        holder.brand.setText(brand.get(position));
        holder.type.setText(type.get(position));
//        holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return brand.size();
    }

    public class MyViewHolderSearch extends RecyclerView.ViewHolder {

        TextView brand, type;
//        CheckBox checkBox;
        CardView cardView;

        public MyViewHolderSearch(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.itemBrandStringSearch);
            type = itemView.findViewById(R.id.itemTypeStringSearch);

            cardView = itemView.findViewById(R.id.searchCardLayout);
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    final ArrayList <Boolean> checks = new ArrayList<>();
//                    if (isChecked){
//                        Log.d("noice", "onCheckedChanged: ");
////                        checks.add(true);
//
//                        SearchActivity.selectItem.add(true);
////                        for(int i = 0; i < selectItem.size(); i++){
////                            SearchActivity.itemss.add(i);
//
//                        }
////                        Log.d("SELECTITEM VAL", String.valueOf(selectItem.get(0)));
////                        SearchActivity.groceryListDatabase.groceryItemDao().setListName();
//
//                    else {
//                        SearchActivity.selectItem.add(false);
////                        Log.d("SELECTITEM VAL", String.valueOf(selectItem.get(1)));
//                    }
//
//                }
//            });
        }

    }


}
