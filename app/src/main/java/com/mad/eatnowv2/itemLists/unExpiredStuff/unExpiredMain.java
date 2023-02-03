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
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.itemLists.expiryAdapter;
import com.mad.eatnowv2.itemLists.foodGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class unExpiredMain extends AppCompatActivity {

    expiryAdapter adapter;

    FirebaseAuth mAuth;

    RecyclerView unExpiredRecyclerView;
    ArrayList<foodGetter> foodList;

    FirebaseFirestore fstore;

    LinearLayoutManager linearLayoutManager;

    //FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_expired_main);

        fstore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        Query query = fstore.collection("UserData");

        unExpiredRecyclerView = findViewById(R.id.unExpiredRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this, unExpiredRecyclerView.VERTICAL, false);

        foodList = new ArrayList<>();
        adapter = new expiryAdapter(this, foodList);

        unExpiredRecyclerView.setLayoutManager(linearLayoutManager);
        unExpiredRecyclerView.setAdapter(adapter);

        fstore.collection("UserData").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list){

                                foodGetter f = d.toObject(foodGetter.class);
                                assert f != null;
                                f.setFoodId(d.getId());
                                foodList.add(f);
                            }
                            adapter.notifyItemChanged(foodList.indexOf(foodList), foodList.size());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }

}
