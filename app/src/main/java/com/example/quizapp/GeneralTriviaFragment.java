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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralTriviaFragment extends Fragment {

    private GeneralTrivia mGeneralTrivia;
    @BindView(R.id.question_1_group)
    RadioGroup question1Group;
    @BindView(R.id.question_3_group)
    RadioGroup question3Group;
    @BindView(R.id.question_4_group)
    RadioGroup question4Group;
    @BindView(R.id.question_6_group)
    RadioGroup question6Group;
    @BindView(R.id.editText_question_2)
    EditText editTextQuestion2;
    @BindView(R.id.editText_question_5)
    EditText editTextQuestion5;
    @BindView(R.id.checkbox_sudan)
    CheckBox checkboxSudan;
    @BindView(R.id.checkbox_uae)
    CheckBox checkboxUae;
    @BindView(R.id.checkbox_south_africa)
    CheckBox checkboxSouthAfrica;
    @BindView(R.id.checkbox_singapore)
    CheckBox checkboxSingapore;
    @BindView(R.id.btn_submit)
    Button submitButton;

    private int mScore = 0;

    private NavController mNavController;

    public GeneralTriviaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_trivia, container, false);
        ButterKnife.bind(this, view);
        editTextQuestion2.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editTextQuestion5.setImeOptions(EditorInfo.IME_ACTION_DONE);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGeneralTrivia = GeneralTrivia.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        question1Group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_graham_bell) {
                mScore += 1;
            }
        });
        question3Group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_1939) {
                mScore += 1;
            }
        });
        question4Group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_ear) {
                mScore += 1;
            }
        });
        question6Group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_vincent_van_gogh) {
                mScore += 1;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    @OnClick(R.id.btn_submit)
    void submit() {
        checkQuestion2();
        checkQuestion5();
        checkQuestion7();
        GeneralTriviaFragmentDirections.ActionResult action
                = GeneralTriviaFragmentDirections.actionResult();
        action.setScore(mScore);
        mNavController.navigate(action);
        reset();
    }

    private void checkQuestion2() {
        StringBuilder question2Answer = new StringBuilder();
        question2Answer.append(editTextQuestion2.getText().toString().toLowerCase().trim());
        if (question2Answer.length() != 0) {
            if (mGeneralTrivia.getQuestionAnswer(2).toLowerCase().trim().contains(question2Answer)) {
                mScore += 1;
            }
        }
    }

    private void checkQuestion5() {
        StringBuilder question5Answer = new StringBuilder();
        question5Answer.append(editTextQuestion5.getText().toString().toLowerCase().trim());
        if (question5Answer.length() != 0) {
            if (mGeneralTrivia.getQuestionAnswer(5).toLowerCase().trim().equals(question5Answer.toString())) {
                mScore += 1;
            }
        }
    }

    private void checkQuestion7() {
        if (checkboxUae.isChecked() && checkboxSudan.isChecked()) {
            mScore += 1;
        }
    }

    private void reset() {
        question1Group.clearCheck();
        question3Group.clearCheck();
        question4Group.clearCheck();
        question6Group.clearCheck();
        editTextQuestion2.getText().clear();
        editTextQuestion5.getText().clear();
        if (checkboxSudan.isChecked()) {
            checkboxSudan.setChecked(false);
        }
        if (checkboxUae.isChecked()) {
            checkboxUae.setChecked(false);
        }
        if (checkboxSingapore.isChecked()) {
            checkboxSingapore.setChecked(false);
        }
        if (checkboxSouthAfrica.isChecked()) {
            checkboxSouthAfrica.setChecked(false);
        }
        if (mScore > 0) {
            mScore = 0;
        }
    }
}
