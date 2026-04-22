package com.example.mobileappfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class GameOverFragment extends Fragment {

    public GameOverFragment() {}

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.game_over_fragment, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        TextView finalScoreText = view.findViewById(R.id.finalScoreText);
        TextView score1 = view.findViewById(R.id.score1);
        TextView score2 = view.findViewById(R.id.score2);
        TextView score3 = view.findViewById(R.id.score3);

        Button retryButton = view.findViewById(R.id.retryButton);

        retryButton.setOnClickListener(v -> {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, new GameFragment())
                    .commit();
        });

        int finalScore = getArguments() != null
                ? getArguments().getInt("final_score")
                : 0;

        finalScoreText.setText("Score: " + finalScore);

        HighScores scores = loadScores();

        if (finalScore > scores.score3) {
            promptForInitials(finalScore, scores, score1, score2, score3);
        } else {
            updateUI(scores, score1, score2, score3);
        }
    }

    private HighScores loadScores() {
        var prefs = requireActivity().getSharedPreferences("scores", 0);

        return new HighScores(
                prefs.getInt("score1", 0),
                prefs.getInt("score2", 0),
                prefs.getInt("score3", 0),
                prefs.getString("name1", "AAA"),
                prefs.getString("name2", "BBB"),
                prefs.getString("name3", "CCC")
        );
    }

    private void promptForInitials(
            int finalScore,
            HighScores scores,
            TextView score1,
            TextView score2,
            TextView score3
    ) {

        EditText input = new EditText(requireContext());
        input.setHint("Enter 3 initials");
        input.setFilters(new android.text.InputFilter[]{
                new android.text.InputFilter.LengthFilter(3)
        });

        new AlertDialog.Builder(requireContext())
                .setTitle("New High Score!")
                .setMessage("Enter your initials:")
                .setView(input)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {

                    String initials = input.getText().toString().toUpperCase();

                    if (initials.length() < 3) {
                        initials = (initials + "AAA").substring(0, 3);
                    }

                    HighScores updated = insertScore(finalScore, initials, scores);

                    saveScores(updated);
                    updateUI(updated, score1, score2, score3);
                })
                .show();
    }

    private HighScores insertScore(int score, String name, HighScores highscore) {

        if (score > highscore.score1) {
            return new HighScores(
                    score, highscore.score1, highscore.score2,
                    name, highscore.name1, highscore.name2
            );
        } else if (score > highscore.score2) {
            return new HighScores(
                    highscore.score1, score, highscore.score2,
                    highscore.name1, name, highscore.name2
            );
        } else {
            return new HighScores(
                    highscore.score1, highscore.score2, score,
                    highscore.name1, highscore.name2, name
            );
        }
    }

    private void saveScores(HighScores highscore) {
        var prefs = requireActivity().getSharedPreferences("scores", 0);

        prefs.edit()
                .putInt("score1", highscore.score1).putString("name1", highscore.name1)
                .putInt("score2", highscore.score2).putString("name2", highscore.name2)
                .putInt("score3", highscore.score3).putString("name3", highscore.name3)
                .apply();
    }

    private void updateUI(
            HighScores highscore,
            TextView score1,
            TextView score2,
            TextView score3
    ) {
        score1.setText(highscore.name1 + " - " + highscore.score1);
        score2.setText(highscore.name2 + " - " + highscore.score2);
        score3.setText(highscore.name3 + " - " + highscore.score3);
    }

    public class HighScores {

        public int score1, score2, score3;
        public String name1, name2, name3;

        public HighScores(int score1, int score2, int score3,
                          String name1, String name2, String name3) {
            this.score1 = score1;
            this.score2 = score2;
            this.score3 = score3;
            this.name1 = name1;
            this.name2 = name2;
            this.name3 = name3;
        }
    }
}