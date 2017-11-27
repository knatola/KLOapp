package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.knatola.kloapp.R;
import com.knatola.kloapp.SymbolFragment;

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
public class GameMenu extends FragmentActivity {

    private int questionCount = 0;
    static final int ITEMS = 4;
    private int score = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.game_menu_layout);

    }
    public static class FragmentsAdapter extends FragmentPagerAdapter {

        public FragmentsAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    SymbolFragment tab1 = new SymbolFragment();
                    return tab1;
                case 1:
                    SymbolFragment tab2 = new SymbolFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return ITEMS;
        }
        @Override
        public CharSequence getPageTitle(int position){

            switch(position){
                case 0:
                    return "ka";
                case 1:
                    return "ku";


            }
            return null;
        }
        //Helper method for returning SymbolFragments in the FragmentsAdapter
        public SymbolFragment getSymbols(int position){
            SymbolFragment symbolFragment = new SymbolFragment();
            Bundle symbolsBundle = new Bundle();

            // need a way to return symbolsList here
            //symbolsBundle.putParcelableArrayList("symbolsList", symbolsList);

            symbolFragment.setArguments(symbolsBundle);
            return symbolFragment;
        }
    }
}
