package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import Adapter.CaffeAdapter;
import Adapter.NewsAdapter;
import Adapter.PopularAdapter;
import BaseClases.CaffeClass;
import BaseClases.ItemsClass;
import BaseClases.MainModels;
import BaseClases.NewsClass;


public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private RecyclerView recyclerViewPopular, recyclerViewNews, recyclerViewCafe;
    private ArrayList<MainModels> popular_list, news_list, cafe_list;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton menuActivity;
    private ImageButton btnFb, btnInst, btnTA,btnCard;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CaffeAdapter caffeAdapter;
    private DocumentReference document;
    private String phone,name;
    private TextView helloText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        setContentView(R.layout.activity_main_menu);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        //Скроллы
        recyclerViewPopular = findViewById(R.id.recycle_view_popular);
        recyclerViewNews = findViewById(R.id.recycle_view_news);
        recyclerViewCafe = findViewById(R.id.recycle_view_caffe);
        helloText=findViewById(R.id.helloTxt);
        //Кнопка меню
        menuActivity = findViewById(R.id.fab);
        menuActivity.setOnClickListener(this);
        btnCard=findViewById(R.id.appBarShopcardBtn);
        btnCard.setOnClickListener(this);
//      Кнопки соц сетей
        btnInst = findViewById(R.id.btnInst);
        btnInst.setOnClickListener(this);
        btnFb = findViewById(R.id.btnFb);
        btnFb.setOnClickListener(this);

        btnTA = findViewById(R.id.btnTA);
        btnTA.setOnClickListener(this);

        loadCafeFromFarebase(recyclerViewCafe, horiz_scrl_animation);
        loadNewsFromFarebase(recyclerViewNews, horiz_scrl_animation);
        loadPopularFromFarebase(recyclerViewPopular, horiz_scrl_animation);
        mAuth = FirebaseAuth.getInstance();
        phone=mAuth.getCurrentUser().getPhoneNumber();
        loadNameFromFarebase();

    }

    public void onClick(View v) {
        if (v.getId() == R.id.fab || v.getId() == R.id.viewAllMenuBtn) {
            Intent intent = new Intent(MainMenu.this, MenuActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnFb) {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/zatsepicoffee/"));
            startActivity(browserIntent);
        } else if (v.getId() == R.id.btnInst) {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/zatsepi_coffee/"));
            startActivity(browserIntent);
        } else if (v.getId() == R.id.btnTA) {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.ru/Restaurant_Review-g298532-d14075463-Reviews-Zatsepi_Coffee-Krasnodar_Krasnodar_Krai_Southern_District.html"));
            startActivity(browserIntent);
        }else if (v.getId() == R.id.appBarShopcardBtn) {
            Intent intent = new Intent(MainMenu.this, ShopingCardActivity.class);
            startActivity(intent);
        }
    }
    private void loadNameFromFarebase() {

        db.collection("users").document(phone).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name= String.valueOf(document.getData().get("name")).toUpperCase();
                                helloText.setText("ПРИВЕТ,"+name+"!");

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    private void loadCafeFromFarebase(RecyclerView recyclerViewMain, RecyclerView.ItemAnimator itemAnimator) {

        List<CaffeClass> caffeClassList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
        
        db.collection("caffe").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<CaffeClass> types = queryDocumentSnapshots.toObjects(CaffeClass.class);
                            caffeClassList.addAll(types);

                        }
                        recyclerViewMain.setLayoutManager(linearLayoutManager);
                        recyclerViewMain.setItemAnimator(itemAnimator);
                        recyclerViewMain.setAdapter(new CaffeAdapter(caffeClassList, MainMenu.this));

                    }
                });
    }

    private void loadNewsFromFarebase(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {

        List<NewsClass> newsClassList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);

        db.collection("news").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d(TAG, String.valueOf(newsClassList.size()) + "  news at the start");
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<NewsClass> types = queryDocumentSnapshots.toObjects(NewsClass.class);
                            newsClassList.addAll(types);

                        }
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        recyclerView.setAdapter(new NewsAdapter(newsClassList, MainMenu.this));

                    }
                });
    }

    private void loadPopularFromFarebase(RecyclerView recyclerViewMain, RecyclerView.ItemAnimator itemAnimator) {

        List<ItemsClass> itemsClasses = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);

        Log.d(TAG, String.valueOf(itemsClasses.size()));
        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the start");
        
        ArrayList<String> itemPath = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            itemPath.add("category/categoryId"+String.valueOf(i)+"/");
//            Log.d("GGGGGGGG","category/categoryId"+String.valueOf(i)+"/");
        }

        for (int j = 0; j < itemPath.size(); j++) {
            document = db.document(itemPath.get(j));
//            Log.d("YYYYYYYYYYYYYYYYYYYYYt", String.valueOf(document));
            document.collection("items")
                    .whereEqualTo("popular",true).whereEqualTo("avaliable",true)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                    ItemsClass types =  document.toObject(ItemsClass.class);
                                    itemsClasses.addAll(Collections.singleton(types));
                                    Log.d(TAG, "onSuccess: " + itemsClasses);
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }


//                            Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the end");

                                recyclerViewMain.setLayoutManager(linearLayoutManager);
                                recyclerViewMain.setItemAnimator(itemAnimator);
                                recyclerViewMain.setAdapter(new PopularAdapter(itemsClasses, MainMenu.this));
//                                Log.d(TAG, String.valueOf(itemsClasses.size()) + "  AFTER ADAPTER IS GO ");
//                                for (ItemsClass cc : itemsClasses) {
//                                    Log.d(TAG, cc.getId());
//                                    Log.d(TAG, cc.getTitle());
//                                    Log.d(TAG, cc.getSize());
//                                    Log.d(TAG, cc.getImagePath());
//                                    Log.d(TAG, "___________________________");
//                                }


                        }
                    });
        }

    }

}

