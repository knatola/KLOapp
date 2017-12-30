package com.knatola.kloapp.Game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.knatola.kloapp.MainActivity;
import com.knatola.kloapp.R;

/**
 * Created by OMISTAJA on 24.11.2017.
 */

/*
Reusable Fragment for the game end screen. Points are passed from a game.
 */
public class GameEndFragment extends android.support.v4.app.Fragment {

    private static View rootView;
    private static int mScore = 0;
    private static int mQuestionCount;
    private Button mGameMenuBtn;
    private Button mMainMenuBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.game_end_layout, container, false);

        Bundle args = getArguments();
        if (args != null) {
            mScore = args.getInt("score");
            mQuestionCount = args.getInt("questionCount");
        }
        TextView mScoreView = rootView.findViewById(R.id.score_count);
        mScoreView.setText(Integer.toString(mScore));
        mGameMenuBtn = rootView.findViewById(R.id.toGameMenuBtn);
        mMainMenuBtn = rootView.findViewById(R.id.toMainMenuBtn);

        mGameMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(GameEndFragment.this).commit();
                ((GameActivity)getActivity()).setBtnLayout(1);
            }
        });

        mMainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(menuIntent);
            }
        });

        return rootView;
    }
}
