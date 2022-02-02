package Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zatsepicoffee_v1.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends  AppCompatActivity{

    private VideoView videoView;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
//        FirebaseAuth.getInstance().signOut();
        setContentView(R.layout.activity_main);

//         adding video on welcome page
        videoView= findViewById(R.id.videoView);
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.v1_1;
//        Uri creating immutable reference
        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user = FirebaseAuth.getInstance().getCurrentUser();
                System.out.println("USER________________"+user);

                if (user!=null){
                    System.out.println("USERPhone________________"+user.getPhoneNumber());
                    finish();
                    startActivity(new Intent(MainActivity.this,MainMenu.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }else {
                    finish();
                    Intent intent= new Intent(MainActivity.this,PhoneVerifActivity.class);
                    startActivity(intent);
                }

            }
        },5000);

    }
    @Override
    public void onStart() {

        super.onStart();

    }
}