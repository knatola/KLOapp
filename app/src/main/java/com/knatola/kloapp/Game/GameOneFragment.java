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
 * Created by OMISTAJA on 24.11.2017.
 */

public class GameOneFragment extends Fragment{

    private static final String LOG = "GameOneFragment:";
    private static View rootView;
    private ArrayList<Symbol> mGameSymbols;
    private Button mOkBtn;
    private EditText mInput;
    private int mQuestionCount = 0;
    private int mMaxQuestionCount;
    private int mPointCount = 0;
    private TextView mQuestionSymbol;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.game_one_layout, container, false);
        mOkBtn = rootView.findViewById(R.id.okBtn);
        mInput = rootView.findViewById(R.id.answerInput);
        mQuestionSymbol = rootView.findViewById(R.id.symbolImage);
        Bundle args = getArguments();

        if (args != null) {
            mGameSymbols = args.getParcelableArrayList("gameSymbols");
            mMaxQuestionCount = mGameSymbols.size();
            Log.d(LOG, "questions: "+ mMaxQuestionCount);
        }

        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
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

                    if(mQuestionCount + 1 < mMaxQuestionCount){
                        mQuestionCount++;
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        Log.d(LOG,"tying to start endGameFragment");
                        moveToGameEnd();
                    }
                } else {
                    Snackbar rightAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Right answer!", Snackbar.LENGTH_LONG);
                    rightAnswer.show();
                    if(mQuestionCount + 1 < mMaxQuestionCount ) {
                        mQuestionCount++;
                        mPointCount++;
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        moveToGameEnd();
                    }
                }



            }
        });

        return rootView;
    }

    public static void hideKeyboard(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) mContext).getWindow()
                .getCurrentFocus().getWindowToken(), 0);
    }

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
