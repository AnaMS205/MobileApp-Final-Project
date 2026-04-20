package com.example.mobileappfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.CountDownTimer;

import java.util.Map;
import java.util.Random;

public class GameFragment extends Fragment {

    private Button leftButton;
    private Button rightButton;
    private TextView wordBox;
    private TextView scoreBox;

    private CountDownTimer timer;
    private TextView timerText;
    private static final long TIME_PER_ROUND = 5000;
//I'm striking Indigo because its just too similar to Violet
//that also might just be my color blindness
    Map<String, Integer> colors = Map.of(
            "RED", 0xFFFF0000,
            "ORANGE", 0xFFFFA500,
            "YELLOW", 0xFFFFFF00,
            "GREEN", 0xFF008000,
            "BLUE", 0xFF0000FF,
            "VIOLET", 0xFFEE82EE
    );

    String[] words = {
            "RED",
            "ORANGE",
            "YELLOW",
            "GREEN",
            "BLUE",
            "VIOLET"
    };

    Random rand = new Random();

    String currentWord;
    String currentColorKey;
    String correctAnswer;

    int score = 0;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        leftButton = view.findViewById(R.id.leftButton);
        rightButton = view.findViewById(R.id.rightButton);
        wordBox = view.findViewById(R.id.wordContainer);
        scoreBox = view.findViewById(R.id.scoreText);
        timerText = view.findViewById(R.id.timerText);

        leftButton.setOnClickListener(v ->
                handleChoice(leftButton.getText().toString()));

        rightButton.setOnClickListener(v ->
                handleChoice(rightButton.getText().toString()));

        startRound();
    }

    public void startRound() {

        if (timer != null) {
            timer.cancel();
        }

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
        } else {
            leftButton.setText(wrongAnswer);
            rightButton.setText(correctAnswer);
        }

        startTimer();
    }

    public void handleChoice(String chosen) {
        if (timer != null) {
            timer.cancel();
        }
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
    private void startTimer() {

        long timeForRound = getTimeForRound();

        timer = new CountDownTimer(timeForRound, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                score--;
                updateScore();
                startRound();
            }
        }.start();
    }
    private long getTimeForRound() {
        long baseTime = 5000;
        long decrease = score * 200;

        long minTime = 1000;

        return Math.max(baseTime - decrease, minTime);
    }
}