package com.example.mobileappfinal;



import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Map;
import java.util.Random;

//right now it just immediately loads the GameFragment
//maybe if time permits we can save highscores across runs but eh
//Ill leave navigation stuff to you in the meantime
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TitleScreenActivity())//<-- swap out new GameFragment for MenuFragment and thats what it will display on start
                    .commit();
        }
    }
}