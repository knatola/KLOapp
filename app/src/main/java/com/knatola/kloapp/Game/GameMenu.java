package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.knatola.kloapp.R;

/**
 * Created by knatola on 23.11.2017.
 */

/*
 need to make :
 - adapter for game fragments
 - game fragments
 - game end fragment
 -
 */
public class GameMenu extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.game_menu_layout);

    }
}
