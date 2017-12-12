package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        }
        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInput.getText().toString().equals("")){
                    Snackbar noInput = Snackbar.make(rootView.findViewById(R.id.gameOneBase), "Write your answer.", Snackbar.LENGTH_SHORT);
                    noInput.show();
                }

                else if(!mInput.getText().toString().equals(mGameSymbols.get(mQuestionCount).getName())) {
                    Snackbar wrongAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Wrong answer.", Snackbar.LENGTH_LONG);
                    wrongAnswer.show();
                    if(mQuestionCount + 1 <= mMaxQuestionCount){
                        mQuestionCount++;
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    }

                }
                else {
                    Snackbar rightAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Right answer!", Snackbar.LENGTH_LONG);
                    rightAnswer.show();
                    if(mQuestionCount + 1 <= mMaxQuestionCount ) {
                        mQuestionCount++;
                        mPointCount++;
                        mQuestionSymbol.setText(mGameSymbols.get(mQuestionCount).getPic());
                    }else{
                        //End game screen here with bundle point/questions.
                    }

                }
            }
        });

        return rootView;
    }
}
