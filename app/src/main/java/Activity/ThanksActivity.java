package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.zatsepicoffee_v1.R;

public class ThanksActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton toMainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        toMainBtn=findViewById(R.id.order_toMainBtn);
        toMainBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        finish();
        Intent intent=new Intent(ThanksActivity.this,   MainMenu.class);
        startActivity(intent);
    }
}