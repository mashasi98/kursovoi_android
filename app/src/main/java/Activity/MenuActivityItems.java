package Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Adapter.ItemAdapter;
import BaseClases.ItemsClass;

public class MenuActivityItems extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewItems;
    private FirebaseFirestore db;
    private TextView category_title;
    private String catTitle,catyId,path;
    private DocumentReference document;

    private static final String TAG ="TAG in new activity categoryTitle: ";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_menu_items);
        //передача данных из прошлой активити
        Bundle arguments = getIntent().getExtras();

        catyId = arguments.get("categoryId").toString();
        catTitle = arguments.get("categoryTitle").toString();
        Log.d("TAG in new activity id", catyId);
        Log.d(TAG, catTitle);

        path="category/"+catyId+"/";
        System.out.println(path);
        document = db.document(path);


        //нопки
        category_title=findViewById(R.id.category_txt_item);
        category_title.setText(catTitle);
        //
        recyclerViewItems = findViewById(R.id.recycle_view_items);
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        loadItemByCategoryFromFarebase(recyclerViewItems,horiz_scrl_animation,document);


    }

    public void onClick(View v) {
        switch(v.getId()){

        }

    }
    @SuppressLint("LongLogTag")
    private void loadItemByCategoryFromFarebase(RecyclerView recyclerViewMain, RecyclerView.ItemAnimator itemAnimator, DocumentReference document) {

        List<ItemsClass> itemsClasses = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        Log.d(TAG, String.valueOf(itemsClasses.size()));
        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the start");

//        db.collection("items").whereEqualTo("popular",true).whereEqualTo("avaliable",true)
//                       .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
//                            Log.d(TAG,   q + " queryDocumentSnapshots is here_________________________________________");
//
//                        }
//                        if (queryDocumentSnapshots.isEmpty()) {
//                            Log.d(TAG, "onSuccess: LIST EMPTY");
//                            return;
//                        } else {

//                            List<ItemsClass> types = queryDocumentSnapshots.toObjects(ItemsClass.class);
//                            Log.d(TAG, "onSuccess: types "+types);
//                            itemsClasses.addAll(types);
//                            Log.d(TAG, "onSuccess: " + itemsClasses);
//                    }

//                        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the end");
//                        recyclerViewMain.setLayoutManager(linearLayoutManager);
//                        recyclerViewMain.setItemAnimator(itemAnimator);
//                        recyclerViewMain.setAdapter(new PopularAdapter(itemsClasses, MainMenu.this));
//                        Log.d(TAG, String.valueOf(itemsClasses.size()) + "  AFTER ADAPTER IS GO ");
//                                              for (ItemsClass cc:itemsClasses) {
//                                                  Log.d(TAG, cc.getId());
//                                                  Log.d(TAG, cc.getTitle());
////                                                  Log.d(TAG, cc.getSize());
//                                                  Log.d(TAG, cc.getImagePath());
//
//                                                  Log.d(TAG,"___________________________");
//                                              }
//                    }
//                });
        document.collection("items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onSuccess in item 11: " + itemsClasses);
                            Log.d(TAG, "onSuccess in item 11: " + task.getResult().size());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                ItemsClass types =  document.toObject(ItemsClass.class);
                                itemsClasses.addAll(Collections.singleton(types));
                                Log.d(TAG, "onSuccess in item: " + itemsClasses);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the end");
                        recyclerViewMain.setLayoutManager(staggeredGridLayoutManager);
                        recyclerViewMain.setItemAnimator(itemAnimator);
                        recyclerViewMain.setAdapter(new ItemAdapter(itemsClasses, MenuActivityItems.this));
                        Log.d(TAG, String.valueOf(itemsClasses.size()) + "  AFTER ADAPTER IS GO ");
                        for (ItemsClass cc : itemsClasses) {
                            Log.d(TAG, cc.getId());
                            Log.d(TAG, cc.getTitle());
                            Log.d(TAG, cc.getSize());
                            Log.d(TAG, cc.getImagePath());
                            Log.d(TAG, "___________________________");
                        }
                    }
                });
    }

}