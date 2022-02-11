package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zatsepicoffee_v1.R;
import com.example.zatsepicoffee_v1.databinding.ActivityPhoneVerifBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class PhoneVerifActivity extends AppCompatActivity  implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText edtPhone, edtOTP,adtName;
    private ImageButton verifyOTPBtn, generateOTPBtn,back;
    private String verificationId;
    private ActivityPhoneVerifBinding binding;
    private LinearLayout ph1,cd1;
    private FirebaseFirestore db;
    private String phoneNumbCheck,name;
    private FirebaseUser user;
    private Intent intent;
    private Collection<Object> usersInf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verif);
        FirebaseApp.initializeApp(/*context=*/ this);


        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        edtPhone = findViewById(R.id.phoneEt);
        edtOTP = findViewById(R.id.codeEt);
        verifyOTPBtn = findViewById(R.id.phoneSumbBtn);
        generateOTPBtn = findViewById(R.id.saveNameBtn);
        back = findViewById(R.id.backToPhone);
        ph1=findViewById(R.id.phoneL1);
        cd1=findViewById(R.id.codeL1);
        cd1.setVisibility(View.GONE);

        generateOTPBtn.setOnClickListener( this);
        verifyOTPBtn.setOnClickListener(this) ;
        back.setOnClickListener(this) ;
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           user = task.getResult().getUser();
//                            finish();
                            checkUsersInBase();

//

                        } else {
                            Toast.makeText(PhoneVerifActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void checkUsersInBase(){
        phoneNumbCheck= user.getPhoneNumber();
        db.collection("users")
        .document(phoneNumbCheck)
        .get()
        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData().values());
                        intent  = new Intent(PhoneVerifActivity.this,MainMenu.class  );
//        intent.putExtra("accountName","name" );
                        startActivity(intent);

                    } else {
                        Log.d("TAG", "No such document");
                        Log.d("TAG", phoneNumbCheck);
                        intent  = new Intent(PhoneVerifActivity.this,InputNameActivity.class  );
                        intent.putExtra("accountPhone",phoneNumbCheck);
                        startActivity(intent);

                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }




    private void sendVerificationCode(String number) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationId = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    final String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        edtOTP.setText(code);
                        verifyCode(code);
                    }else {
                        Toast.makeText(PhoneVerifActivity.this, "Please enter verify Code", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    // displaying error message with firebase exception.
                    Toast.makeText(PhoneVerifActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            };


    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveNameBtn:
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    Toast.makeText(PhoneVerifActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    ph1.setVisibility(View.GONE);
                    String phone = "+7" + edtPhone.getText().toString().trim();
                    sendVerificationCode(phone);
                    cd1.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.phoneSumbBtn:
                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    Toast.makeText(PhoneVerifActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(edtOTP.getText().toString());
                }

                break;

            case R.id.backToPhone:
                ph1.setVisibility(View.VISIBLE);
                cd1.setVisibility(View.GONE);
                break;
        }
    }
}



