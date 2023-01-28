package com.mad.eatnowv2.itemLists.expiredStuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.eatnowv2.itemLists.expiryAdapter;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.itemLists.foodGetter;

import java.util.ArrayList;

public class expiredMain extends AppCompatActivity {

    RecyclerView expiredRecyclerView;
    ArrayList<foodGetter> foodList;
    expiryAdapter expiryAdapter;

    FirebaseFirestore fStore;

    LinearLayoutManager linearLayoutManager;

    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expired_main);


        expiredRecyclerView = findViewById(R.id.expiredRecyclerView);
        //expiredRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, expiredRecyclerView.VERTICAL, false);

        expiredRecyclerView.setLayoutManager(linearLayoutManager);


        fStore = FirebaseFirestore.getInstance();

        Query query = fStore.collection("expiredFood");

        FirestoreRecyclerOptions<foodGetter> options = new FirestoreRecyclerOptions.Builder<foodGetter>()
                .setQuery(query, foodGetter.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<foodGetter, ViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull foodGetter model) {

                holder.bind(model);

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_expired_blueprint, group, false);

                return new ViewHolder(view);
            }
        };

        expiredRecyclerView.setAdapter(adapter);
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

        public void bind(foodGetter foodGetter){

                foodName.setText(foodGetter.getFoodName());
                foodDesc.setText(foodGetter.getFoodDesc());
                expiryDate.setText(foodGetter.getExpiryDate());
        }
    }

}