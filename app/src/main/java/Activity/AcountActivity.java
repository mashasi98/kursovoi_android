package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zatsepicoffee_v1.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AcountActivity extends AppCompatActivity implements View.OnClickListener {
    private String name,phone;
    private FirebaseFirestore db;
    private TextView nameTxt,phoneTxt;
    private FirebaseAuth mAuth;
    private Intent intent;

    private ImageButton orders,change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

        db = FirebaseFirestore.getInstance();
        Bundle arguments = getIntent().getExtras();
        name= arguments.get("accountName").toString();
        phone= arguments.get("accountPhone").toString();
        mAuth = FirebaseAuth.getInstance();


        nameTxt= findViewById(R.id.aacountName_txt);
        phoneTxt= findViewById(R.id.aacountPhone_txt);
        orders=findViewById(R.id.account_order_Btn);
        change=findViewById(R.id.account_change_Btn);


        orders.setOnClickListener(this);
        change.setOnClickListener(this);



        nameTxt.setText("ПРИВЕТ,"+name);
        phoneTxt.setText(phone);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.account_order_Btn:
                intent=new Intent(AcountActivity.this,AllOredersActivity.class);
                intent.putExtra("accountPhone",phone );
                startActivity(intent);


                break;
            case R.id.account_change_Btn:
                    mAuth.signOut();
                    intent=new Intent(AcountActivity.this,PhoneVerifActivity.class);
                    intent.putExtra("accountPhone",phone );
                    startActivity(intent);
                    finish();

                break;

        }
    }
}