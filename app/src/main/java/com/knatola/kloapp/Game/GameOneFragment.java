package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knatola.kloapp.R;

/**
 * Created by OMISTAJA on 24.11.2017.
 */

public class GameOneFragment extends Fragment {

    private static View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.game_one_layout, container, false);

        Bundle args = getArguments();

        if (args != null) {

        }


        return rootView;
    }
}
