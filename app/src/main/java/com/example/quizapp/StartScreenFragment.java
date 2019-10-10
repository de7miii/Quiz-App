package com.example.quizapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartScreenFragment extends Fragment {

    private NavController mNavController;

    @BindView(R.id.radio_general_trivia) RadioButton radioGeneralTrivia;
    @BindView(R.id.radio_sports_trivia) RadioButton radioSportsTrivia;
    @BindView(R.id.radio_film_trivia) RadioButton radioFilmTrivia;
    @BindView(R.id.radio_history_trivia) RadioButton radioHistoryTrivia;
    @BindView(R.id.btn_start) Button buttonStart;


    public StartScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);

        //only category available now is General Trivia
        radioHistoryTrivia.setEnabled(false);
        radioFilmTrivia.setEnabled(false);
        radioSportsTrivia.setEnabled(false);

        buttonStart.setOnClickListener(v -> {
            if (radioGeneralTrivia.isChecked()) {
                mNavController.navigate(R.id.action_startScreenFragment_to_generalTriviaFragment);
            } else if (!radioGeneralTrivia.isChecked()
                    && !radioSportsTrivia.isChecked()
                    && !radioFilmTrivia.isChecked()
                    && !radioHistoryTrivia.isChecked()) {
                Snackbar.make(view, "Please Slecet a Category", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
