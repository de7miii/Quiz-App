package com.example.quizapp;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private int mScore = 0;

    @BindView(R.id.header_text)
    TextView headerText;
    @BindView(R.id.score_text)
    TextView scoreText;
    @BindView(R.id.background_image)
    ImageView backgroundImage;

    private NavController mNavController;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mScore = ResultFragmentArgs.fromBundle(getArguments()).getScore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        StringBuilder score = new StringBuilder();
        score.append("You Scored:\n");
        score.append(mScore);
        score.append(" out of 7");
        scoreText.setText(score);
        headerText.setText(R.string.header_congrats);
        Toast.makeText(getContext(), score, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    @OnClick(R.id.btn_play_again)
    void playAgain() {
        mNavController.navigate(R.id.actionResultToGame);
    }

    @OnClick(R.id.btn_home)
    void home() {
        mNavController.popBackStack(R.id.startScreenFragment, false);
    }
}
