package com.example.mobileappfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class TitleScreenFragment extends Fragment {

    private Button startBtn;

    public TitleScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.title_screen_fragment, container, false);

        startBtn = view.findViewById(R.id.StartButton);

        startBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, new GameFragment())
                    .commit();
        });

        return view;
    }
}