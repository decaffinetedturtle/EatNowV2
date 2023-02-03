package com.mad.eatnowv2.itemLists;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.itemLists.unExpiredStuff.updateUnExpired;
import com.mad.eatnowv2.userDashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class expiryAdapter extends RecyclerView.Adapter<expiryAdapter.ViewHolder>{

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
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

    /*holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DocumentReference documentReference =  fstore.collection("expiredFood").document(mAuth.getCurrentUser().getUid());
            Map<String, Object> food = new HashMap<>();
            food.put("foodName", foodList.get(holder.getAbsoluteAdapterPosition()).getFoodName());
            food.put("foodDesc", foodList.get(holder.getAbsoluteAdapterPosition()).getFoodDesc());
            food.put("expiryDate", foodList.get(holder.getAbsoluteAdapterPosition()).getExpiryDate());
            documentReference.update(food);
        }
    });*/



/*       editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity
                startActivity(context, new Intent(context, updateUnExpired.class), null);

            }
        });*/



       /* holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateUnExpired.class);
                foodGetter food = foodList.get(holder.getAbsoluteAdapterPosition());
                intent.putExtra("food", food);
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView foodName, foodDesc, expiryDate;

        ImageButton editBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.foodName);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            expiryDate = itemView.findViewById(R.id.expiryDate);


            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            foodGetter food = foodList.get(getAbsoluteAdapterPosition());
            Intent intent = new Intent(context, updateUnExpired.class);
            intent.putExtra("food", food);
            context.startActivity(intent);
            //expiryAdapter.this.notifyDataSetChanged();
        }
    }





}
