package com.example.mobileappfinal;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;
import java.util.Random;

public class GameFragment extends Fragment {

    private Button leftButton;
    private Button rightButton;
    private TextView wordBox;
    private TextView scoreBox;
    private TextView timerText;

    private CountDownTimer timer;

    private static final String KEY_SCORE = "score";
    private static final String KEY_WORD = "word";
    private static final String KEY_COLOR = "color";

    Map<String, Integer> colors = Map.of(
            "RED",    0xFFFF6B6B,
            "ORANGE", 0xFFFF8A3D,
            "YELLOW", 0xFFFFFF80,
            "GREEN",  0xFF4CAF50,
            "BLUE",   0xFF4DA3FF,
            "VIOLET", 0xFFB388FF
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

    String currentWord; //WRONG CHOICE
    String currentColorKey; // CORRECT CHOICE

    int score = 0;


    public GameFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState
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

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE);
            currentWord = savedInstanceState.getString(KEY_WORD);
            currentColorKey = savedInstanceState.getString(KEY_COLOR);

            restoreUI();
        } else {
            startRound();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SCORE, score);
        outState.putString(KEY_WORD, currentWord);
        outState.putString(KEY_COLOR, currentColorKey);
    }

    //it crashes when you flip the screen if you dont do this
    //learned this the hard way
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void restoreUI() {
        scoreBox.setText("Score: " + score);;

        wordBox.setText(currentWord);
        wordBox.setTextColor(colors.get(currentColorKey));


        if (rand.nextBoolean()) {
            leftButton.setText(currentColorKey);
            rightButton.setText(currentWord);
        } else {
            leftButton.setText(currentWord);
            rightButton.setText(currentColorKey);
        }

        startTimer();
    }

    public void startRound() {
        if (timer != null) {
            timer.cancel();
        }

        currentWord = words[rand.nextInt(words.length)];

        //a loop to make sure that the word is never same color as itself
        //i.e. "Blue" won't be colored blue
        do {
            currentColorKey = words[rand.nextInt(words.length)];
        } while (currentColorKey.equals(currentWord));

        wordBox.setText(currentWord);
        wordBox.setTextColor(colors.get(currentColorKey));

        //coin flip to choose a correct and wrong button
        if (rand.nextBoolean()) {
            leftButton.setText(currentColorKey);
            rightButton.setText(currentWord);
        } else {
            leftButton.setText(currentWord);
            rightButton.setText(currentColorKey);
        }

        scoreBox.setText("Score: " + score);;
        startTimer();
    }

    public void handleChoice(String chosen) {
        if (timer != null) {
            timer.cancel();
        }

        if (chosen.equals(currentColorKey)) {
            score++;
        } else {
            score--;
        }

        scoreBox.setText("Score: " + score);;
        startRound();
    }

    private void startTimer() {
        long timeForRound = getTimeForRound();

        timer = new CountDownTimer(timeForRound, 1000) {

            //isAdded checks to see if its attached to current fragment
            //its just another check to not crash when the screen is rotated
            @Override
            public void onTick(long millisUntilFinished) {
                if (!isAdded()) return;
                timerText.setText("Time: " + (millisUntilFinished / 1000));
            }

            //make GameOverFragment when timer runs out
            @Override
            public void onFinish() {
                if (!isAdded()) return;

                if (timer != null) {
                    timer.cancel();
                }

                Fragment gameOverFragment = new GameOverFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("final_score", score);
                gameOverFragment.setArguments(bundle);

                // the transition animations
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out
                        )
                        .replace(R.id.fragment_container, gameOverFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }.start();
    }

    //technically 6 secs but cause of time and stuff
    //the display starts at 4
    //(5.00 secs on start -> 4.99 secs) so you never see 5 and it feels weird
    private long getTimeForRound() {
        long baseTime = 6000;
        long decrease = score * 200;
        long minTime = 1000;

        return Math.max(baseTime - decrease, minTime);
    }
}