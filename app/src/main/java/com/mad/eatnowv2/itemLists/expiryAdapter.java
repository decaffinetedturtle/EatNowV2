package com.mad.eatnowv2.itemLists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mad.eatnowv2.R;

import java.util.ArrayList;

public class expiryAdapter extends RecyclerView.Adapter<expiryAdapter.ViewHolder>{

    Context context;

    ArrayList<foodGetter> foodList;

    public expiryAdapter(Context context, ArrayList<foodGetter> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_expired_blueprint, parent, false);
        return new expiryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.foodName.setText(foodList.get(position).getFoodName());
    holder.foodDesc.setText(foodList.get(position).getFoodDesc());
    holder.expiryDate.setText(foodList.get(position).getExpiryDate());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView foodName, foodDesc, expiryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.foodName);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            expiryDate = itemView.findViewById(R.id.expiryDate);

        }
    }





}
