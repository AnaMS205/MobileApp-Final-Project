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

        if (finalScore > scores.s3) {
            promptForInitials(finalScore, scores, score1, score2, score3);
        } else {
            updateUI(scores, score1, score2, score3);
        }
    }

    private HighScores loadScores() {
        var prefs = requireActivity().getSharedPreferences("scores", 0);

        return new HighScores(
                prefs.getInt("s1", 0),
                prefs.getInt("s2", 0),
                prefs.getInt("s3", 0),
                prefs.getString("n1", "AAA"),
                prefs.getString("n2", "BBB"),
                prefs.getString("n3", "CCC")
        );
    }

    // ---------------------------
    // Prompt for initials
    // ---------------------------
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

    private HighScores insertScore(int score, String name, HighScores s) {

        if (score > s.s1) {
            return new HighScores(
                    score, s.s1, s.s2,
                    name, s.n1, s.n2
            );
        } else if (score > s.s2) {
            return new HighScores(
                    s.s1, score, s.s2,
                    s.n1, name, s.n2
            );
        } else {
            return new HighScores(
                    s.s1, s.s2, score,
                    s.n1, s.n2, name
            );
        }
    }

    private void saveScores(HighScores s) {
        var prefs = requireActivity().getSharedPreferences("scores", 0);

        prefs.edit()
                .putInt("s1", s.s1).putString("n1", s.n1)
                .putInt("s2", s.s2).putString("n2", s.n2)
                .putInt("s3", s.s3).putString("n3", s.n3)
                .apply();
    }

    private void updateUI(
            HighScores s,
            TextView score1,
            TextView score2,
            TextView score3
    ) {
        score1.setText(s.n1 + " - " + s.s1);
        score2.setText(s.n2 + " - " + s.s2);
        score3.setText(s.n3 + " - " + s.s3);
    }

    public class HighScores {

        public int s1, s2, s3;
        public String n1, n2, n3;

        public HighScores(int s1, int s2, int s3,
                          String n1, String n2, String n3) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.n1 = n1;
            this.n2 = n2;
            this.n3 = n3;
        }
    }
}