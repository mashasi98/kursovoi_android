package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapter.ShopingCardAdapter;
import BaseClases.OrderClass;
import BaseClases.ShopingCardClass;

public class ShopingCardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TAG";
    private RecyclerView recyclerViewShopingCard;
    private List<ShopingCardClass> shopingCardClasses;
    private TextView allPrice, totalPrise;
    private ImageButton back, order, clearCardbtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String phone, path, dateText, timeText;
    private DocumentReference document;
    private Toast toast;
    private int totalAmount = 0;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_card);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        phone = mAuth.getCurrentUser().getPhoneNumber();
        Log.d("PHONE", phone);
        path = "users/" + phone + "/";
        document = db.document(path);

        allPrice = findViewById(R.id.all_price_txt);
        totalPrise = findViewById(R.id.total_price_txt);
        back = findViewById(R.id.from_cardTOMAIN_BTN);
        order = findViewById(R.id.order_btn);
        clearCardbtn = findViewById(R.id.clear_card_btn);

        back.setOnClickListener(this);
        order.setOnClickListener(this);
        clearCardbtn.setOnClickListener(this);

        recyclerViewShopingCard = findViewById(R.id.recyclerViewShopingCard);
        loadCardFromBase(recyclerViewShopingCard);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_cardTOMAIN_BTN:
                finish();
                break;
            case R.id.order_btn:
                addOrder();

                break;
            case R.id.clear_card_btn:
                clearCard();
                Toast.makeText(this, "Корзина очищена", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void loadCardFromBase(RecyclerView recyclerViewShopingCard) {
        shopingCardClasses = new ArrayList<>();
        RecyclerView.ItemAnimator vert_scrl_animation = new DefaultItemAnimator();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopingCardActivity.this, LinearLayoutManager.VERTICAL, false);
        document.collection("shopingCard")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            Log.d(TAG, "onSuccess: IN CARD " + queryDocumentSnapshots.getDocuments());
                            List<ShopingCardClass> types = queryDocumentSnapshots.toObjects(ShopingCardClass.class);
                            shopingCardClasses.addAll(types);
                        }
                        recyclerViewShopingCard.setLayoutManager(linearLayoutManager);
                        recyclerViewShopingCard.setItemAnimator(vert_scrl_animation);
                        recyclerViewShopingCard.setAdapter(new ShopingCardAdapter(shopingCardClasses, ShopingCardActivity.this));
                        for (ShopingCardClass s : shopingCardClasses) {
                            totalAmount += s.getAmount() * s.getPrice();
                            Log.d("totalAmount ", String.valueOf(totalAmount));
                        }
                        totalPrise.setText(String.valueOf(totalAmount));
                        allPrice.setText(String.valueOf(totalAmount));



                    }
                });


    }

    public void clearCard() {
        if (shopingCardClasses.size() != 0) {
            for (ShopingCardClass s : shopingCardClasses) {
                document.collection("shopingCard")
                        .document(s.getId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot" + s.getId() + " successfully deleted!");
                                shopingCardClasses.clear();
                                Log.d(TAG, String.valueOf(shopingCardClasses.size()));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });
            }

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

    }

    public void addOrder() {
        if (shopingCardClasses.size() != 0) {
            Date currentDate = new Date();

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            timeText = timeFormat.format(currentDate);
            totalAmount = Integer.parseInt(String.valueOf(totalPrise.getText()));
            OrderClass orderClass = new OrderClass(totalAmount, dateText, timeText, shopingCardClasses);
            document.collection("orders")
                    .document()
                    .set(orderClass)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            clearCard();
                            finish();
                            intent=new Intent(ShopingCardActivity.this,ThanksActivity.class);
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });


        }else {
            Toast.makeText(this, "Ваша корзина пуста", Toast.LENGTH_LONG).show();
        }
    }
}