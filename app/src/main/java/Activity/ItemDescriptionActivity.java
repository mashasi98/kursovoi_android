package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private Button plusBtn, minusBtn,addToCard;
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
        addToCard=findViewById(R.id.add_to_card_btn);
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


        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        addToCard.setOnClickListener(this);

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
                amount++;
                amountTxt.setText(Integer.toString(amount));
                 total=Integer.toString( price*amount)+"P";
                textPrice.setText(total);
                break;
            case R.id.minus_amount_btn:
                if (amount!=1){
                    amount--;
                }else
                amountTxt.setText(Integer.toString(amount));
                total=Integer.toString( price*amount)+"P";
                textPrice.setText(total);
                break;
            case R.id.add_to_card_btn:
                AddToBaseCard();

                break;
        }
    }

    private void AddToBaseCard(){
        ShopingCardClass shopingCardClass = new ShopingCardClass(id,price,size,image,title,description,categoryId,popular,amount);
        document.collection("shopingCard")
                .document(id)
                .set(shopingCardClass);

    }
}