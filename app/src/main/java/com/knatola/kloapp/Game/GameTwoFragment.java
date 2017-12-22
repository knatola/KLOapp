package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by OMISTAJA on 24.11.2017.
 */

public class GameTwoFragment extends Fragment{

    private static final String LOG = "GameTwoFragment:";
    private GameTwoLogic mGameTwoLogic;
    private static View rootView;
    private ArrayList<Symbol> mGameSymbols;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    Random rng = new Random();
    private TextView mQuestionPic;
    private String mQuestion;
    private int mScoreCount = 0;
    private int mQuestionCount;
    private int mMaxQuestionCount;
    private TextView mPointView;
    private TextView mQuestionView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.game_two_layout, container, false);
        mButton1 = rootView.findViewById(R.id.answerBtn1);
        mButton2 = rootView.findViewById(R.id.answerBtn2);
        mButton3 = rootView.findViewById(R.id.answerBtn3);
        mButton4 = rootView.findViewById(R.id.answerBtn4);
        mQuestionPic = rootView.findViewById(R.id.questionImage);
        mPointView = rootView.findViewById(R.id.points);
        mQuestionView = rootView.findViewById(R.id.questions);

        mGameTwoLogic = GameTwoLogic.getInstance();
        Bundle args = getArguments();

        if (args != null) {
            mGameSymbols = args.getParcelableArrayList("gameSymbols");
            setmQuestion(randomSymbolText(mGameSymbols));
            mMaxQuestionCount = mGameSymbols.size();
        }
        //set the asked picture and set button pictures
        mQuestionPic.setText(mQuestion);
        changeQuestions(mQuestion);
        mPointView.setText("Points: " + Integer.toString(mScoreCount));
        mQuestionView.setText("Questions: " + Integer.toString(mQuestionCount));

        Set<Symbol> testList = randomSymbolTexts(mGameSymbols);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRightButton(mButton1);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRightButton(mButton2);
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRightButton(mButton3);
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRightButton(mButton4);
            }
        });

        return rootView;
    }

    //method to change the questions, maybe this could be made more efficiently (?)
    public void changeQuestions(String answer){
        int i = rng.nextInt(3);
        Log.d(LOG, "the right case is " + i);
        mQuestionPic.setText(answer.toLowerCase());
        switch(i){
            case 0:
                mButton1.setText(answer);
                mButton2.setText(randomSymbolText(mGameSymbols));
                mButton3.setText(randomSymbolText(mGameSymbols));
                mButton4.setText(randomSymbolText(mGameSymbols));
                break;

            case 1:
                mButton2.setText(answer);
                mButton1.setText(randomSymbolText(mGameSymbols));
                mButton3.setText(randomSymbolText(mGameSymbols));
                mButton4.setText(randomSymbolText(mGameSymbols));
                break;

            case 2:
                mButton3.setText(answer);
                mButton2.setText(randomSymbolText(mGameSymbols));
                mButton1.setText(randomSymbolText(mGameSymbols));
                mButton4.setText(randomSymbolText(mGameSymbols));
                break;

            case 3:
                mButton4.setText(answer);
                mButton2.setText(randomSymbolText(mGameSymbols));
                mButton3.setText(randomSymbolText(mGameSymbols));
                mButton1.setText(randomSymbolText(mGameSymbols));
                break;
        }
    }

    public Set<Symbol> randomSymbolTexts(ArrayList<Symbol> symbols){

        int i = rng.nextInt(symbols.size());
        Set<Symbol> apuLista = new HashSet<>();
        for(int j  = 0 ; j < 4; j++) {
            Symbol symbol = symbols.get(i);
            if (apuLista.contains(symbol)) {
                if(i == symbols.size()){
                    Symbol symbol1 = symbols.get(i-1);
                    apuLista.add(symbol1);
                }else if( i < symbols.size()){
                    Symbol symbol2 = symbols.get(i+1);
                    apuLista.add(symbol2);
                }
            } else {
                apuLista.add(symbol);
            }
            for(Symbol r: apuLista){
                Log.d(LOG, r.getPic());
            }
        }
        return apuLista;
    }
    public String randomSymbolText(ArrayList<Symbol> symbols){
        int i = rng.nextInt(symbols.size());
        Symbol symbol = symbols.get(i);

        return symbol.getPic();
    }

    public void testRightButton(Button btn){
        if(btn.getText().toString().equals(mQuestion)){
            if(mQuestionCount + 1 < mMaxQuestionCount){
                mScoreCount++;
                mQuestionCount++;
                mPointView.setText("Points: " + Integer.toString(mScoreCount));
                mQuestionView.setText("Question: " + Integer.toString(mQuestionCount));
                setmQuestion(randomSymbolText(mGameSymbols));
                changeQuestions(getmQuestion());
            } else{
                mScoreCount++;
                moveToGameEnd();
            }
        }else{
            Snackbar wrongAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Wrong answer.", Snackbar.LENGTH_LONG);
            wrongAnswer.show();
            if(mQuestionCount + 1 < mMaxQuestionCount){
                mQuestionCount++;
                mPointView.setText("Points: "+ Integer.toString(mScoreCount));
                mQuestionView.setText("Question: " + Integer.toString(mQuestionCount));
                setmQuestion(randomSymbolText(mGameSymbols));
                changeQuestions(getmQuestion());
            } else {
                moveToGameEnd();
            }
        }
    }

    public void moveToGameEnd(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("score", mScoreCount);
        GameEndFragment endFragment = new GameEndFragment();
        endFragment.setArguments(bundle);
        transaction.replace(R.id.gameFragmentContainer, endFragment, "endFragment");
        transaction.commit();
        getFragmentManager().beginTransaction().remove(GameTwoFragment.this).commit();
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }
}
