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

public class MainActivity extends AppCompatActivity {

    private Button leftButton;
    private Button rightButton;
    private TextView wordBox;
    private TextView scoreBox;

    Map<String, Integer> colors = Map.of(
            "RED", 0xFFFF0000,
            "ORANGE", 0xFFFFA500,
            "YELLOW", 0xFFFFFF00,
            "GREEN", 0xFF008000,
            "BLUE", 0xFF0000FF,
            "INDIGO", 0xFF4B0082,
            "VIOLET", 0xFFEE82EE
    );

    String[] words = {
            "RED",
            "ORANGE",
            "YELLOW",
            "GREEN",
            "BLUE",
            "INDIGO",
            "VIOLET"
    };

    Random rand = new Random();

    String currentWord;
    String currentColorKey;
    String correctAnswer;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        wordBox = findViewById(R.id.wordContainer);
        scoreBox = findViewById(R.id.scoreText);

        leftButton.setOnClickListener(v -> handleChoice(leftButton.getText().toString()));
        rightButton.setOnClickListener(v -> handleChoice(rightButton.getText().toString()));

        startRound();
    }

    public void startRound() {

        currentWord = words[rand.nextInt(words.length)];

        do {
            currentColorKey = words[rand.nextInt(words.length)];
        } while (currentColorKey.equals(currentWord));

        wordBox.setText(currentWord);
        wordBox.setTextColor(colors.get(currentColorKey));

        correctAnswer = currentColorKey;

        String wrongAnswer = currentWord;

        if (rand.nextBoolean()) {
            leftButton.setText(correctAnswer);
            rightButton.setText(wrongAnswer);
        }
        else {
            leftButton.setText(wrongAnswer);
            rightButton.setText(correctAnswer);
        }
    }

    public void handleChoice(String chosen) {
        if (chosen.equals(correctAnswer)) {
            score++;
        } else {
            score--;
        }
        updateScore();
        startRound();
    }

    public void updateScore() {
        scoreBox.setText("Score: " + score);
    }
}