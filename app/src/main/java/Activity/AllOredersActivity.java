package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.autofill.Validator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.AllOrdersAdapter;
import Adapter.CaffeAdapter;
import BaseClases.CaffeClass;
import BaseClases.OrderClass;

public class AllOredersActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ImageButton back;
    private FirebaseFirestore db;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());
        setContentView(R.layout.activity_all_oreders);

//        mAuth = FirebaseAuth.getInstance();
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        db = FirebaseFirestore.getInstance();
        Bundle arguments = getIntent().getExtras();

        phone= arguments.get("accountPhone").toString();
        back =findViewById(R.id.backToAcc_BTN);
        back.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycle_view_allOrders);
        loadAllOrdersFromFarebase(recyclerView,horiz_scrl_animation);


    }
    private void loadAllOrdersFromFarebase( RecyclerView recyclerView, RecyclerView.ItemAnimator horiz_scrl_animation ) {
        List<OrderClass> orderClasses = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllOredersActivity.this, LinearLayoutManager.VERTICAL, false);

        db.collection("users")
                .document(phone)
                .collection("orders")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d("TAG", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<OrderClass>  types = queryDocumentSnapshots.toObjects(OrderClass.class);
                            orderClasses.addAll(types);
                        }
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setItemAnimator(horiz_scrl_animation);
                        recyclerView.setAdapter(new AllOrdersAdapter(orderClasses, AllOredersActivity.this));
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToAcc_BTN:
                finish();
                break;
        }

    }
}