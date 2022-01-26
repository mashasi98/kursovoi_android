package Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.zatsepicoffee_v1.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;


import Adapter.Adapt;
import Adapter.CaffeAdapter;
import Adapter.NewsAdapter;
import Adapter.PopularAdapter;
import BaseClases.CaffeClass;
import BaseClases.ItemsClass;
import BaseClases.MainModels;
import BaseClases.NewsClass;
import Interfaces.ICaffeLoadLissener;
//import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private RecyclerView recyclerViewPopular, recyclerViewNews, recyclerViewCafe;
    private ArrayList<MainModels> popular_list, news_list, cafe_list;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton menuActivity;
    private ImageButton btnFb, btnInst, btnTA;
    //    private ActivityMainMenuBinding binding;
    private ICaffeLoadLissener iCaffeLoadLissener;
    //    private DatabaseReference database;
    private FirebaseFirestore db;
    private CaffeAdapter caffeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        setContentView(R.layout.activity_main_menu);

        db = FirebaseFirestore.getInstance();
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        //Скроллы
        recyclerViewPopular = findViewById(R.id.recycle_view_popular);
        recyclerViewNews = findViewById(R.id.recycle_view_news);
        recyclerViewCafe = findViewById(R.id.recycle_view_caffe);
        //Кнопка меню
        menuActivity = findViewById(R.id.fab);
        menuActivity.setOnClickListener(this);

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

//        init(horiz_scrl_animation,iCaffeLoadLissener);
//        loadCafeFromFarebase();
//        mAuth = FirebaseAuth.getInstance();
//        checkUserStatus();
//
//        binding= ActivityMainMenuBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());


        //массивы картинок и описаний(заменить на считыванеи с бд)
//        Integer[] ListImagePopular = {R.drawable.americano, R.drawable.cappuchino, R.drawable.latte, R.drawable.espresso, R.drawable.falt_white, R.drawable.raf};
//        Integer[] ListImageNews = {R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news5, R.drawable.news7};
//        Integer[] ListImageCafe = {R.drawable.caff_chap, R.drawable.caff_kras, R.drawable.caff_kras_small, R.drawable.caff_sev, R.drawable.caff_sedina11, R.drawable.caff_neftyan, R.drawable.caff_stadion, R.drawable.caff_stavr131, R.drawable.caff_stavr254};
//
//        //массив текстов названий
//        String[] cofeeDescription = {"Облепиховый чай", "Имбирный чай", "Какао", "Пряный какао"};
        //Прокрутки


//        recyclerViewMainMenu(ListImagePopular, horiz_scrl_animation, recyclerViewPopular, popular_list);
//        recyclerViewNews(ListImageNews, horiz_scrl_animation, recyclerViewNews, news_list);
//            recyclerViewCafe(ListImageCafe, horiz_scrl_animation, recyclerViewCafe);
//        recyclerViewMainMenu(ListImageNews,horiz_scrl_animation,recyclerViewNews,news_list,R.layout.row_item_news);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.fab || v.getId() == R.id.viewAllMenuBtn || v.getId() == R.id.layout1) {
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
        }
    }


    private void loadCafeFromFarebase(RecyclerView recyclerViewMain, RecyclerView.ItemAnimator itemAnimator) {

        List<CaffeClass> caffeClassList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);

        Log.d(TAG, String.valueOf(caffeClassList.size()));
        Log.d(TAG, String.valueOf(caffeClassList.size()) + " at the start");

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
                            Log.d(TAG, "onSuccess: " + caffeClassList);
                        }
//                      Log.d(TAG, String.valueOf(caffeClassList.size()));
//                      Log.d(TAG, String.valueOf(caffeClassList.size()) + " at the end");
                        recyclerViewMain.setLayoutManager(linearLayoutManager);
                        recyclerViewMain.setItemAnimator(itemAnimator);
                        recyclerViewMain.setAdapter(
                                new CaffeAdapter(caffeClassList, MainMenu.this));
                        Log.d(TAG, String.valueOf(caffeClassList.size()) + "  AFTER ADAPTER IS GO ");
//                                              for (CaffeClass cc:caffeClassList) {
//                                                  Log.d(TAG, cc.getId());
//                                                  Log.d(TAG, cc.getAdress());
//                                                  Log.d(TAG, cc.getTime());
//                                                  Log.d(TAG, cc.getImagePath());
//                                                  Log.d(TAG, cc.getContacts());
//                                                  Log.d(TAG,"___________________________");
//                                              }
                    }
                });
    }

    private void loadNewsFromFarebase(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {

        List<NewsClass> newsClassList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
//
//        Log.d(TAG, String.valueOf(newsClassList.size()));
//        Log.d(TAG, String.valueOf(newsClassList.size()) + " at the start");
        db.collection("news").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, String.valueOf(newsClassList.size()) + "  news at the start");
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<NewsClass> types = queryDocumentSnapshots.toObjects(NewsClass.class);
                            newsClassList.addAll(types);
                            Log.d(TAG, "onSuccess: " + newsClassList);
                        }
//                      Log.d(TAG, String.valueOf(caffeClassList.size()));
//                      Log.d(TAG, String.valueOf(caffeClassList.size()) + " at the end");
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        recyclerView.setAdapter(new NewsAdapter(newsClassList, MainMenu.this));
                        Log.d(TAG, String.valueOf(newsClassList.size()) + "  AFTER ADAPTER IS GO");
//                                              for (NewsClass cc:newsClassList) {
//                                                  Log.d(TAG, cc.getId());
//
//                                                  Log.d(TAG, cc.getTitle());
//                                                  Log.d(TAG, cc.getImagePath());
//                                                  Log.d(TAG, cc.getDiscription());
//                                                  Log.d(TAG,"___________________________");
//                                              }
                    }
                });
    }

    private void loadPopularFromFarebase(RecyclerView recyclerViewMain, RecyclerView.ItemAnimator itemAnimator) {

        List<ItemsClass> itemsClasses = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);

        Log.d(TAG, String.valueOf(itemsClasses.size()));
        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the start");

        db.collection("items").whereEqualTo("popular",true).whereEqualTo("avaliable",true)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                            Log.d(TAG, q + " queryDocumentSnapshots is here_________________________________________");
                        }
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<ItemsClass> types = queryDocumentSnapshots.toObjects(ItemsClass.class);
                            Log.d(TAG, "onSuccess: types "+types);
                            itemsClasses.addAll(types);
                            Log.d(TAG, "onSuccess: " + itemsClasses);
                        }

                        Log.d(TAG, String.valueOf(itemsClasses.size()) + " at the end");
                        recyclerViewMain.setLayoutManager(linearLayoutManager);
                        recyclerViewMain.setItemAnimator(itemAnimator);
                        recyclerViewMain.setAdapter(new PopularAdapter(itemsClasses, MainMenu.this));
                        Log.d(TAG, String.valueOf(itemsClasses.size()) + "  AFTER ADAPTER IS GO ");
                                              for (ItemsClass cc:itemsClasses) {
                                                  Log.d(TAG, cc.getId());
                                                  Log.d(TAG, cc.getTitle());
//                                                  Log.d(TAG, cc.getSize());
                                                  Log.d(TAG, cc.getImagePath());

                                                  Log.d(TAG,"___________________________");
                                              }
                    }
                });
    }


//    public void recyclerViewCafe(Integer[] listImage, RecyclerView.ItemAnimator itemAnimator, RecyclerView recyclerViewMain) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
//        List<CaffeClass> caffeClasses = new ArrayList<>();
//        for (Integer integer : listImage) {
//            caffeClasses.add(new CaffeClass("String id", "String imagePath", "String adress", " String time", " String contacts"));
//        }
//        recyclerViewMain.setLayoutManager(linearLayoutManager);
//        recyclerViewMain.setItemAnimator(itemAnimator);
//        recyclerViewMain.setAdapter(new CaffeAdapter(caffeClasses, MainMenu.this));}
//
//    public void recyclerViewMainMenu(Integer[] listImage, RecyclerView.ItemAnimator itemAnimator, RecyclerView recyclerViewMain, ArrayList<MainModels> arrayList) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);

//        arrayList = new ArrayList<>();
//        for (Integer integer : listImage) {
//            arrayList.add(new MainModels(integer));
//        }
//        recyclerViewMain.setLayoutManager(linearLayoutManager);
//        recyclerViewMain.setItemAnimator(itemAnimator);
//        recyclerViewMain.setAdapter(new PopularAdapter(arrayList, MainMenu.this, R.layout.row_item_popular));
//    }
//
//    public void recyclerViewNews(Integer[] listImage, RecyclerView.ItemAnimator itemAnimator, RecyclerView recyclerViewMain, ArrayList<MainModels> arrayList) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
//
//        arrayList = new ArrayList<>();
//        for (Integer integer : listImage) {
//            arrayList.add(new MainModels(integer));
//        }
//        recyclerViewMain.setLayoutManager(linearLayoutManager);
//        recyclerViewMain.setItemAnimator(itemAnimator);
//        recyclerViewMain.setAdapter(new NewsAdapter(arrayList, MainMenu.this, R.layout.row_item_news));
//    }


}

