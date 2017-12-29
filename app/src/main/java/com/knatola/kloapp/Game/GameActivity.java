package com.knatola.kloapp.Game;

import android.content.Context;
import android.content.Intent;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.knatola.kloapp.HelpActivity;
import com.knatola.kloapp.MainActivity;
import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.SymbolFragment;
import com.knatola.kloapp.SymbolMenu;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

    private Toolbar mToolbar;
    private static final int ITEMS = 4;
    private int score = 0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private FrameLayout mFragmentContainer;
    private RelativeLayout mBtnLayout;
    private static final String LOG = "Game Menu Activity:";
    private Bundle mGameBundle;
    private ImageButton mBackBtn;
    private ArrayList<Symbol> mSymbolsList;
    private ArrayList<Symbol> mSymbolsList2;
    private ArrayList<Symbol> mSymbolsList3;
    private TextView mGameHeader;
    private static final String KATAKANA = "Katakana";
    private static final String HIRAKANA = "Hiragana";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG, "Activity started");
        setContentView(R.layout.game_menu_layout);
        mGameHeader = findViewById(R.id.game_header);
        mGameBundle = getIntent().getExtras();
        mSymbolsList3 = new ArrayList<>();

        if(mGameBundle != null) {
            mSymbolsList = mGameBundle.getParcelableArrayList("symbols");
            mSymbolsList2 = mGameBundle.getParcelableArrayList("symbols1");
            mSymbolsList3.addAll(mSymbolsList);
            mSymbolsList3.addAll(mSymbolsList2);
        }

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        //mToolbar.setT
        setActionBar(mToolbar);

        if (findViewById(R.id.gameFragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        mBackBtn = findViewById(R.id.gameMenuBack);
        mBtnLayout = findViewById(R.id.buttonLayout);
        mFragmentContainer = findViewById(R.id.gameFragmentContainer);


        /*
        * Buttons set up
         */
        btn1 = findViewById(R.id.game1Btn);
        btn2 = findViewById(R.id.game2Btn);
        btn3 = findViewById(R.id.game3Btn);
        btn4 = findViewById(R.id.game4Btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeader("Hirakana game 1");
                setGameFragments(mSymbolsList3, 1);
                setBtnLayout(0);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeader("Hirakana game 2");
                setGameFragments(mSymbolsList3, 2);
                setBtnLayout(0);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameFragments(mSymbolsList3, 1);
                setBtnLayout(0);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameFragments(mSymbolsList3, 2);
                setBtnLayout(0);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

    //Method to hide/show buttons in Activity
    public void setBtnLayout(int i){
        if(i == 1){
            mBtnLayout.setVisibility(View.VISIBLE);
        }else{
            mBtnLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

        for(Fragment fragment:getSupportFragmentManager().getFragments()){

            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        if(mBtnLayout.getVisibility()== View.VISIBLE)
            super.onBackPressed();

        setHeader("Game Menu");
        setBtnLayout(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id ==R.id.action_symbols_katakana) {
            Intent symbolIntent = new Intent(GameActivity.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mSymbolsList);
            symbolIntent.putParcelableArrayListExtra("symbols1", mSymbolsList2);
            symbolIntent.putExtra("type", KATAKANA);
            startActivity(symbolIntent);
        } else if(id == R.id.action_help){
            Intent helpIntent = new Intent(GameActivity.this, HelpActivity.class);
            startActivity(helpIntent);
        }else{
            Intent symbolIntent = new Intent(GameActivity.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mSymbolsList);
            symbolIntent.putParcelableArrayListExtra("symbols1", mSymbolsList2);
            symbolIntent.putExtra("type", HIRAKANA);
            startActivity(symbolIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Method to change the toolbar title of Activity
    public void setHeader(String header){
        mGameHeader.setText(header);
    }

}
