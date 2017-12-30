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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created by OMISTAJA on 24.11.2017.
 */

/*
* Example symbols are passed in ArrayList from MainActivity.
* Game has 4 buttons and 1 asked japanese symbol.
* The right symbol name is added to random of the 4 buttons and
* 3 other random (wrong) names are added to the other buttons.
* All buttons will have different names, so there aren't duplicates.
*
* The "game" shuffles the passed ArrayList and asks all of the symbols in it.
* This way no symbol is asked twice and the order of symbols are pseudorandom.
*
 */

public class GameTwoFragment extends Fragment{

    private static final String LOG = "GameTwoFragment:";
    private static View rootView;
    private ArrayList<Symbol> mGameSymbols;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    Random rng = new Random();
    private TextView mQuestionPic;
    private Symbol mQuestion;
    private int mScoreCount = 0;
    private int mQuestionCount = 1; //first question is 1.
    private int mMaxQuestionCount;
    private TextView mPointView;
    private TextView mQuestionView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.game_two_layout, container, false);

        //UI elements init
        mButton1 = rootView.findViewById(R.id.answerBtn1);
        mButton2 = rootView.findViewById(R.id.answerBtn2);
        mButton3 = rootView.findViewById(R.id.answerBtn3);
        mButton4 = rootView.findViewById(R.id.answerBtn4);
        mQuestionPic = rootView.findViewById(R.id.questionImage);
        mPointView = rootView.findViewById(R.id.points);
        mQuestionView = rootView.findViewById(R.id.questions);

        /*
        Check if there are passed arguments to the Activity.
         */
        Bundle args = getArguments();
        if (args != null) {
            mGameSymbols = args.getParcelableArrayList("gameSymbols");
            Collections.shuffle(mGameSymbols);
            mQuestion = mGameSymbols.get(mQuestionCount - 1);
            mMaxQuestionCount = mGameSymbols.size();
        }
        //set the asked picture and set button pictures
        mQuestionPic.setText(mQuestion.getPic());
        changeQuestions(mQuestion);
        mPointView.setText("Points: " + Integer.toString(mScoreCount));
        mQuestionView.setText("Question: " + Integer.toString(mQuestionCount));

        //String testList = randomSymbolTexts(mGameSymbols, randomSymbolText(mGameSymbols));

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

    /*
    Method to change the questions. Takes the asked symbol as an argument.
    the random button, where the right answer will be added is selected pseudorandomly
    with java.util.random.

    Maybe not optimal, but it works.
     */

    public void changeQuestions(Symbol symbol){
        String answerPic = symbol.getPic();
        String answerTavu = symbol.getName();
        int i = rng.nextInt(3);
        String [] apuLista = randomSymbolTexts(mGameSymbols, answerTavu);

        //Debug logger
        Log.d(LOG, "the right case is " + i);
        mQuestionPic.setText(answerPic.toLowerCase());

        switch(i){
            case 0:
                mButton1.setText(answerTavu);
                mButton2.setText(apuLista[1]);
                mButton3.setText(apuLista[2]);
                mButton4.setText(apuLista[3]);
                break;

            case 1:
                mButton2.setText(answerTavu);
                mButton1.setText(apuLista[1]);
                mButton3.setText(apuLista[2]);
                mButton4.setText(apuLista[3]);
                break;

            case 2:
                mButton3.setText(answerTavu);
                mButton2.setText(apuLista[1]);
                mButton1.setText(apuLista[2]);
                mButton4.setText(apuLista[3]);
                break;

            case 3:
                mButton4.setText(answerTavu);
                mButton2.setText(apuLista[1]);
                mButton3.setText(apuLista[2]);
                mButton1.setText(apuLista[3]);
                break;
        }
    }

    /*

    Returns 3 random "wrong answers", with 1 right answer.
    Point is that the 3 random wrong answers can't have duplicates and they can't
    be same as the answer.
    Takes Symbol ArrayList and the right answer String as args.
    Returns a String Array[4], where the [0] element is the right answer.

    Maybe not optimal, but it works.
     */
    public String [] randomSymbolTexts(ArrayList<Symbol> symbols, String answer){

            String[] apu = new String[4];
            apu[0] = answer;

            apu[1] = randomSymbolText(symbols);

            while (apu[1].equals(answer)) {
                apu[1] = randomSymbolText(symbols);
            }
            apu[2] = randomSymbolText(symbols);

            while (apu[2].equals(answer) || apu[2].equals(apu[1])) {
                apu[2] = randomSymbolText(symbols);
            }

            apu[3] = randomSymbolText(symbols);
            while (apu[3].equals(answer) || apu[3].equals(apu[1]) || apu[3].equals(apu[2])) {
                apu[3] = randomSymbolText(symbols);
            }
            //test loop
            for (int i = 0; i < 4; i++) {
                Log.d(LOG, apu[i]);
            }

            return apu;
    }

    /*
    Returns pseudorandomly a symbols name.
    Takes an ArrayList<Symbol> as arg.
     */
    public String randomSymbolText(ArrayList<Symbol> symbols){
        int i = rng.nextInt(symbols.size());
        Symbol symbol = symbols.get(i);

        return symbol.getName();
    }

    // Helper method
    public Symbol randomSymbolAnswer(ArrayList<Symbol> symbols){
        Symbol symbol = symbols.get(mQuestionCount - 1);
        return symbol;
    }

    /*
    Method to test if pressed button has the right answer.
    Pressed Button is passed as arg.

    After mMaxQuestionCount is done, moveToGameEnd() is called.
     */
    public void testRightButton(Button btn){
        if(btn.getText().toString().equals(mQuestion.getName())){
            if(mQuestionCount < mMaxQuestionCount){
                mScoreCount++;
                mQuestionCount++;
                mPointView.setText("Points: " + Integer.toString(mScoreCount));
                mQuestionView.setText("Question: " + Integer.toString(mQuestionCount));
                setmQuestion(randomSymbolAnswer(mGameSymbols));
                changeQuestions(getmQuestion());
            } else{
                mScoreCount++;
                moveToGameEnd();
            }
        }else{
            Snackbar wrongAnswer = Snackbar.make(getActivity().findViewById(R.id.baseGame), "Wrong answer.", Snackbar.LENGTH_LONG);
            wrongAnswer.show();
            if(mQuestionCount  < mMaxQuestionCount){
                mQuestionCount++;
                mPointView.setText("Points: "+ Integer.toString(mScoreCount));
                mQuestionView.setText("Question: " + Integer.toString(mQuestionCount));
                setmQuestion(randomSymbolAnswer(mGameSymbols));
                changeQuestions(getmQuestion());
            } else {
                moveToGameEnd();
            }
        }
    }

    /*
    Method to change to score screen/game end screen.
    Score count is passed.
     */
    public void moveToGameEnd(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("score", mScoreCount);
        GameEndFragment endFragment = new GameEndFragment();
        endFragment.setArguments(bundle);
        transaction.replace(R.id.gameFragmentContainer, endFragment, "endFragment");
        transaction.commit();
        ((GameActivity)getActivity()).setHeader("Score Screen");
        getFragmentManager().beginTransaction().remove(GameTwoFragment.this).commit();
    }

    public Symbol getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(Symbol mQuestion) {
        this.mQuestion = mQuestion;
    }
}
