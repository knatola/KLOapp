package com.knatola.kloapp.Game;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;

import java.util.ArrayList;

/**
 * Created by knatola on 24.11.2017.
 */

/*
Fragment for the first game/quiz.
mMaxQuestionCount is set from the passed symbolList
 */


public class GameOneFragment extends Fragment{

    private static final String LOG = "GameOneFragment:";
    private static View rootView;
    private ArrayList<Symbol> mGameSymbols;
    private Button mOkBtn;
    private EditText mInput;
    private int mQuestionCount = 1;
    private int mMaxQuestionCount;
    private int mPointCount = 0;
    private TextView mQuestionSymbol;
    private TextView mQuestionCountText;
    private TextView mScoreCountText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.game_one_layout, container, false);
        mOkBtn = rootView.findViewById(R.id.okBtn);
        mInput = rootView.findViewById(R.id.answerInput);
        mQuestionSymbol = rootView.findViewById(R.id.symbolImage);
        mQuestionCountText = rootView.findViewById(R.id.questions_text1);
        mScoreCountText = rootView.findViewById(R.id.points_text1);

        //check args
        Bundle args = getArguments();
        if (args != null) {
            mGameSymbols = args.getParcelableArrayList("gameSymbols");
            mMaxQuestionCount = mGameSymbols.size();
            Log.d(LOG, "questions: "+ mMaxQuestionCount);
        }

        mScoreCountText.setText("Points: " + Integer.toString(mPointCount));
        mQuestionCountText.setText("Question: " + Integer.toString(mQuestionCount));
        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());

        /*
        Ok button listener. Checks the given answer. Game doesn't move on until user gives some answer
        (input field isn't empty). On right answer user gets a point.
         */
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInput.clearFocus();
                hideKeyboard(getContext());

                if(mInput.getText().toString().equals("")){
                    Snackbar noInput = Snackbar.make(rootView.findViewById(R.id.gameOneBase), "Write your answer.", Snackbar.LENGTH_SHORT);
                    noInput.show();
                } else if(!mInput.getText().toString().equals(mGameSymbols.get(mQuestionCount).getName())) {
                    Snackbar wrongAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Wrong answer.", Snackbar.LENGTH_LONG);
                    wrongAnswer.show();
                    mInput.setText("");
                    mQuestionCount++;
                    mScoreCountText.setText("Points: " + Integer.toString(mPointCount));
                    mQuestionCountText.setText("Question: " + Integer.toString(mQuestionCount));

                    if(mQuestionCount  < mMaxQuestionCount){
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        Log.d(LOG,"tying to start endGameFragment");
                        moveToGameEnd();
                    }
                } else {
                    Snackbar rightAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Right answer!", Snackbar.LENGTH_LONG);
                    rightAnswer.show();
                    mQuestionCount++;
                    mPointCount++;
                    mScoreCountText.setText("Points: " + Integer.toString(mPointCount));
                    mQuestionCountText.setText("Question: " + Integer.toString(mQuestionCount));
                    if(mQuestionCount < mMaxQuestionCount ) {
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        moveToGameEnd();
                    }
                }
            }
        });

        return rootView;
    }

    //helper method to hide the keyboard
    public static void hideKeyboard(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) mContext).getWindow()
                .getCurrentFocus().getWindowToken(), 0);
    }

    //Helper method to move to the game end screen, points are passed in bundle.
    public void moveToGameEnd(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("score", mPointCount);
        GameEndFragment endFragment = new GameEndFragment();
        endFragment.setArguments(bundle);
        transaction.replace(R.id.gameFragmentContainer, endFragment, "endFragment");
        transaction.commit();
    }
}
