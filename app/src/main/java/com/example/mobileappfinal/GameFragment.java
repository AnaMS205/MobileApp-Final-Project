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
    private static final String KEY_CORRECT = "correct";

    Map<String, Integer> colors = Map.of(
            "RED", 0xFFFF0000,
            "ORANGE", 0xFFFFA500,
            "YELLOW", 0xFFFFFF00,
            "GREEN", 0xFF008000,
            "BLUE", 0xFF0000FF,
            "VIOLET", 0xFFEE82EE
    );

    String[] words = {
            "RED", "ORANGE", "YELLOW",
            "GREEN", "BLUE", "VIOLET"
    };

    Random rand = new Random();

    String currentWord;
    String currentColorKey;
    String correctAnswer;

    int score = 0;

    public GameFragment() {}

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

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE);
            currentWord = savedInstanceState.getString(KEY_WORD);
            currentColorKey = savedInstanceState.getString(KEY_COLOR);
            correctAnswer = savedInstanceState.getString(KEY_CORRECT);

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
        outState.putString(KEY_CORRECT, correctAnswer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void restoreUI() {
        updateScore();

        wordBox.setText(currentWord);
        wordBox.setTextColor(colors.get(currentColorKey));

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

        updateScore();
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
                if (!isAdded()) return;
                timerText.setText("Time: " + (millisUntilFinished / 1000));
            }

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

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,   // entering (GameOver fades in)
                                R.anim.fade_out   // exiting (Game fades out)
                        )
                        .replace(R.id.fragment_container, gameOverFragment)
                        .addToBackStack(null)
                        .commit();

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, gameOverFragment)
                        .addToBackStack(null)
                        .commit();
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