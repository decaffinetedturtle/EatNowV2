package com.mad.eatnowv2.itemLists.unExpiredStuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.itemLists.foodGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class unExpiredMain extends AppCompatActivity {

    FirebaseAuth mAuth;

    RecyclerView unExpiredRecyclerView;
    ArrayList<foodGetter> foodList;

    FirebaseFirestore fstore;

    LinearLayoutManager linearLayoutManager;

    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_expired_main);

        unExpiredRecyclerView = findViewById(R.id.unExpiredRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this, unExpiredRecyclerView.VERTICAL, false);

        unExpiredRecyclerView.setLayoutManager(linearLayoutManager);

        fstore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        Query query = fstore.collection("UserData");

        FirestoreRecyclerOptions<foodGetter> options = new FirestoreRecyclerOptions.Builder<foodGetter>()
                .setQuery(query, foodGetter.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<foodGetter, ViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull foodGetter model) {
                holder.bind(model);
            }

            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_un_expired_blueprint, group, false);

                return new ViewHolder(view);
            }
        };

        unExpiredRecyclerView.setAdapter(adapter);


        //handles swiping
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
                //used to drag and drop
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //adapter.deleteItem(viewHolder.getAdapterPosition());

                String foodName = foodList.get(viewHolder.getBindingAdapterPosition()).getFoodName();
                String foodDesc = foodList.get(viewHolder.getBindingAdapterPosition()).getFoodDesc();
                String expiryDate = foodList.get(viewHolder.getBindingAdapterPosition()).getExpiryDate();

                int position = viewHolder.getBindingAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        //TODO: add to expired list
                        DocumentReference documentReference =  fstore.collection("expiredFood").document(mAuth.getCurrentUser().getUid());
                        Map<String, Object> food = new HashMap<>();
                        food.put("foodName", foodName);
                        food.put("foodDesc", foodDesc);
                        food.put("expiryDate", expiryDate);
                        adapter.notifyItemRemoved(position);
                        break;


                    case ItemTouchHelper.RIGHT:

                        //TODO: add edit fragment

                        startActivity(new Intent(getApplicationContext(), updateUnExpired.class));



                        adapter.notifyItemChanged(position);
                        break;
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(unExpiredRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodName, foodDesc, expiryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.foodName);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            expiryDate = itemView.findViewById(R.id.expiryDate);
        }

        public void bind(foodGetter foodGetter) {
            foodName.setText(foodGetter.getFoodName());
            foodDesc.setText(foodGetter.getFoodDesc());
            expiryDate.setText(foodGetter.getExpiryDate());
        }
    }
}
