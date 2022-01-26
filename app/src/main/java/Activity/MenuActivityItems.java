package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoryAdapter;
import BaseClases.CategoryClass;
import BaseClases.ItemsClass;

public class MenuActivityItems extends AppCompatActivity implements View.OnClickListener {
    ImageButton backToMenuBtb;
    RecyclerView recyclerViewItems;
    private FirebaseFirestore db;
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

        db = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_menu_items);
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        //кнопки корзины и возврата

        backToMenuBtb=findViewById(R.id.back_toMenu_btn);
        backToMenuBtb.setOnClickListener(this);
        recyclerViewItems=findViewById(R.id.recycle_view_items);

//        //recycleView
//        Integer[] ListImageCategory= {R.drawable.category_classic, R.drawable.category_aliter, R.drawable.category_author, R.drawable.category_iced, R.drawable.category_warming, R.drawable.category_fresh, R.drawable.category_meal};
//        String[] categoryName = {"Классика", "Альтернатива", "Авторское", "Охлаждает","Согревает","Освежает","Перекусить"};
//        recyclerViewCategory=findViewById(R.id.recycle_view_category);
//        recyclerViewCategory(ListImageCategory,categoryName,horiz_scrl_animation,recyclerViewCategory);
//        loadItemsByCatFromFarebase(recyclerViewItems,horiz_scrl_animation);
    }
    public void onClick(View v) {
        if ( v.getId() == R.id.back_toMain_btn ) {
            Intent intent = new Intent(MenuActivityItems.this, MenuActivity.class);
            startActivity(intent);
        }
    }
//    private void loadItemsByCatFromFarebase(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {
//
//        List<ItemsClass> categoryClassList = new ArrayList<>();
//
//        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MenuActivity.this, LinearLayoutManager.HORIZONTAL, false);
////
////        Log.d(TAG, String.valueOf(newsClassList.size()));
////        Log.d(TAG, String.valueOf(newsClassList.size()) + " at the start");
//        db.collection("category").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d(TAG, String.valueOf(categoryClassList.size()) + "  news at the start");
//                        if (queryDocumentSnapshots.isEmpty()) {
//                            Log.d(TAG, "onSuccess: LIST EMPTY");
//                            return;
//                        } else {
//                            List<CategoryClass> types = queryDocumentSnapshots.toObjects(CategoryClass.class);
//                            categoryClassList.addAll(types);
//                            Log.d(TAG, "onSuccess: " + categoryClassList);
//                        }
////                      Log.d(TAG, String.valueOf(caffeClassList.size()));
////                      Log.d(TAG, String.valueOf(caffeClassList.size()) + " at the end");
//                        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//                        recyclerView.setItemAnimator(itemAnimator);
//                        recyclerView.setAdapter(new CategoryAdapter(categoryClassList, MenuActivityItems.this));
//                        Log.d(TAG, String.valueOf(categoryClassList.size()) + "  AFTER ADAPTER IS GO");
////                                              for (NewsClass cc:newsClassList) {
////                                                  Log.d(TAG, cc.getId());
////
////                                                  Log.d(TAG, cc.getTitle());
////                                                  Log.d(TAG, cc.getImagePath());
////                                                  Log.d(TAG, cc.getDiscription());
////                                                  Log.d(TAG,"___________________________");
////                                              }
//                    }
//                });
//    }


}

