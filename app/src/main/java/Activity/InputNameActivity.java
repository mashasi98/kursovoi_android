package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import BaseClases.UsersClass;

public class InputNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton save;
    private EditText nameTxt;
    private String phone,name;
    private UsersClass user;
    private FirebaseFirestore db;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
        setContentView(R.layout.activity_input_name);
        db = FirebaseFirestore.getInstance();
        Bundle arguments = getIntent().getExtras();
        phone = arguments.get("accountPhone").toString();
        save=findViewById(R.id.saveNameBtn);
        nameTxt=findViewById(R.id.nameEt);

        save.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        name=nameTxt.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(InputNameActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
        } else {
            user =new UsersClass(phone,name);
            db.collection("users")
                    .document(phone)
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "DocumentSnapshot successfully written!");
                            intent  = new Intent(InputNameActivity.this,MainMenu.class  );
//        intent.putExtra("accountName","name" );
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error writing document", e);
                        }
                    });



        }
    }
}