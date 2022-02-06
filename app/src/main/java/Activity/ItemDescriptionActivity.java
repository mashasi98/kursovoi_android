package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import BaseClases.ShopingCardClass;

public class ItemDescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore db;
    private String categoryId,path,phone;
    private String id,image,title,description,size,total;
    private FirebaseAuth mAuth;

    private int price,amount=1;
    private Boolean avaliable,popular;
    private DocumentReference document;
    private ImageView imageView;

    private ImageButton plusBtn, minusBtn,addToCard,back;
    private TextView textTitle,textSize,textDescript,textPrice,amountTxt;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.item_info);

        Bundle arguments = getIntent().getExtras();

        avaliable= Boolean.valueOf(arguments.get("itemAvaliable").toString());
        categoryId = arguments.get("categoryId").toString();
        description= arguments.get("itemDescription").toString();
        id= arguments.get("itemId").toString();
        image= arguments.get("itemImage").toString();
        popular= Boolean.valueOf(arguments.get("itemPop").toString());
        price = Integer.parseInt(arguments.get("itemPrice").toString());
        size = arguments.get("itemSize").toString();
        title = arguments.get("itemTitle").toString();

        imageView=findViewById(R.id.image_item_info);
        textTitle=findViewById(R.id.info_title_txt);
        textSize=findViewById(R.id.info_size_txt);
        textDescript=findViewById(R.id.info_descript_txt);
        textPrice=findViewById(R.id.info_price_txt);

        amountTxt=findViewById(R.id.amount_count);
        plusBtn =findViewById(R.id.plus_amount_btn);
        minusBtn=findViewById(R.id.minus_amount_btn);
        addToCard=findViewById(R.id.order_btn);
        back=findViewById(R.id.from_itemBack_BTN);
        Glide
                .with(this)
                .load(image)
                .centerCrop()
                .into(imageView);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        textTitle.setText(title);
        textSize.setText(size);
        textDescript.setText(description);
        textPrice.setText(Integer.toString(price)+"P");
        amountTxt.setText((Integer.toString(amount)));

//
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        addToCard.setOnClickListener(this);
        back.setOnClickListener(this);

//        Log.d("TAG in new activity cat", categoryId);


        mAuth = FirebaseAuth.getInstance();
        phone=  mAuth.getCurrentUser().getPhoneNumber();
        System.out.println(phone);
        path="users/"+phone+"/";
        System.out.println(path);
        document = db.document(path);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus_amount_btn:
                Log.d("TAG","onClik+");
                amount++;
                amountTxt.setText(Integer.toString(amount));
                total=Integer.toString( price*amount)+"P";
                textPrice.setText(total);
                break;
            case R.id.minus_amount_btn:
                Log.d("TAG","onClik-");
                if (amount!=1){
                    amount--;
                    amountTxt.setText(Integer.toString(amount));
                    total=Integer.toString( price*amount)+"P";
                    textPrice.setText(total);
                }
                break;
            case R.id.order_btn:
                AddToBaseCard();

                break;
            case R.id.from_itemBack_BTN:
                finish();
                break;
        }
    }

    private void AddToBaseCard(){
        ShopingCardClass shopingCardClass = new ShopingCardClass(id,price,size,image,title,description,categoryId,popular,amount);
        document.collection("shopingCard")
                .document(id)
                .set(shopingCardClass)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

        finish();
        Intent intent=new Intent(ItemDescriptionActivity.this,   MainMenu.class);
        startActivity(intent);
        Toast.makeText(this, title+" "+amount+"шт. добавлено в корзину ",Toast.LENGTH_LONG).show();
    }
}