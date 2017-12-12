package com.knatola.kloapp.Game;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.SymbolFragment;

import java.util.ArrayList;

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
public class GameActivity extends FragmentActivity {

    private int questionCount = 0;
    private static final int ITEMS = 4;
    private int score = 0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    //FragmentsAdapter mFragmentAdapter;
    private FrameLayout mFragmentContainer;
    private RelativeLayout mBtnLayout;
    private static final String LOG = "Game Menu Activity:";
    private String mGameType;
    private Bundle mGameBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG, "Activity started");
        setContentView(R.layout.game_menu_layout);

        mGameBundle = new Bundle();

        if(findViewById(R.id.gameFragmentContainer) != null){
            if(savedInstanceState != null){
                return;
            }
        }

        final Symbol symbol = new Symbol("ka","か" );
        Symbol symbol1 = new Symbol("ki","き");
        Symbol symbol2 = new Symbol("ku", "く");

        final ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.add(symbol);
        symbols.add(symbol1);
        symbols.add(symbol2);

        mBtnLayout = findViewById(R.id.buttonLayout);
        //mFragmentAdapter = new FragmentsAdapter(getSupportFragmentManager());
        mFragmentContainer = findViewById(R.id.gameFragmentContainer);
        //mFragmentContainer.setAdapter(mFragmentAdapter);

        btn1 = findViewById(R.id.game1Btn);
        btn2 = findViewById(R.id.game2Btn);
        btn3 = findViewById(R.id.game3Btn);
        btn4 = findViewById(R.id.game4Btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*mGameType = "hirakana";
                mGameBundle.putString("gameType", mGameType);
                GameOneFragment gameOneFragment = new GameOneFragment();
                gameOneFragment.setArguments(mGameBundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.gameFragmentContainer, gameOneFragment);
                transaction.commit();*/
                setGameFragments(symbols, 1);
                mBtnLayout.setVisibility(View.GONE);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameFragments(symbols, 2);
                mBtnLayout.setVisibility(View.GONE);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setGameFragments("katakana", 1);
                mBtnLayout.setVisibility(View.GONE);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setGameFragments("katakana", 2);
                mBtnLayout.setVisibility(View.GONE);
            }
        });
    }
    //Helper method to dynamically return gameFragment objects
    public void setGameFragments(ArrayList<Symbol> gameSymbols, int gameType){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mGameBundle.putParcelableArrayList("gameSymbols", gameSymbols);

        if(gameType == 1){
            GameOneFragment gameOneFragment = new GameOneFragment();
            gameOneFragment.setArguments(mGameBundle);
            transaction.replace(R.id.gameFragmentContainer, gameOneFragment);
        }else{
            GameTwoFragment gameTwoFragment = new GameTwoFragment();
            gameTwoFragment.setArguments(mGameBundle);
            transaction.replace(R.id.gameFragmentContainer, gameTwoFragment);
        }

        transaction.commit();
    }

    /*public static class FragmentsAdapter extends FragmentPagerAdapter {

        public FragmentsAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    GameOneFragment frag1 = new GameOneFragment();
                    return frag1;
                case 1:
                    GameOneFragment frag2 = new GameOneFragment();
                return frag2;
                case 2:
                    SymbolFragment frag3 = new SymbolFragment();
                    return frag3;
                case 3:
                    SymbolFragment frag4 = new SymbolFragment();
                    return frag4;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id ==R.id.action_help){

        }
        return super.onOptionsItemSelected(item);
    }*/
}
