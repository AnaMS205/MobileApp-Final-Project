package com.example.mobileappfinal;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
                    .replace(R.id.fragment_container, new TitleScreenFragment())//<-- swap out new GameFragment for MenuFragment and thats what it will display on start
                    .commit();
        }
    }
}