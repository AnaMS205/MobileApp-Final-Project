package com.example.mobileappfinal;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Map;
import java.util.Random;
public class TitleScreenActivity extends AppCompatActivity {

    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlescreen);

        startBtn = findViewById(R.id.StartButton);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(TitleScreenActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 5
                );

            }
        });

    }
}
