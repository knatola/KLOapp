package com.knatola.kloapp.Game;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knatola.kloapp.R;

/**
 * Created by OMISTAJA on 24.11.2017.
 */

public class GameEndFragment extends android.support.v4.app.Fragment {

    private static View rootView;
    private static int mScore = 0;
    private static int mQuestionCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.hirasymbol_layout, container, false);

        Bundle args = getArguments();

        if (args != null) {
            mScore = args.getInt("score");
            mQuestionCount = args.getInt("questionCount");
        }




        return rootView;
    }
}
