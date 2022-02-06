package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


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

public class MenuActivity extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton back,card;

    private RecyclerView recyclerViewCategory;
    private FirebaseFirestore db;
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_menu);


        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        //кнопки корзины и возврата
        back=findViewById(R.id.from_menuToMain_BTN);
        card=findViewById(R.id.from_menuToCard_BTN);

        back.setOnClickListener(this);
        card.setOnClickListener(this);

        //recyclerView
        recyclerViewCategory=findViewById(R.id.recycle_view_category);
        loadCategoryFromFarebase(recyclerViewCategory,horiz_scrl_animation);
    }
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.from_menuToMain_BTN:
                finish();
                break;
        case R.id.from_menuToCard_BTN:
            Intent intent =new Intent(MenuActivity.this,ShopingCardActivity.class);
            startActivity(intent);
            break;
        }


    }
    private void loadCategoryFromFarebase(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {

        List<CategoryClass> categoryClassList = new ArrayList<>();

        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        db.collection("category").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, String.valueOf(categoryClassList.size()) + "  news at the start");
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<CategoryClass> types = queryDocumentSnapshots.toObjects(CategoryClass.class);
                            categoryClassList.addAll(types);
                            Log.d(TAG, "onSuccess: " + categoryClassList);
                        }
                        recyclerView.setLayoutManager(staggeredGridLayoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        CategoryAdapter categoryAdapter=new CategoryAdapter(categoryClassList, MenuActivity.this);
                        recyclerView.setAdapter(categoryAdapter);

                    }
                });
    }


}

