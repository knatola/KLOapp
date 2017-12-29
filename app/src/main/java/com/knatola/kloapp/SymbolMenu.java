package com.knatola.kloapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.SymbolFragment;


import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by knatola on 20.11.2017.
 */

public class SymbolMenu extends AppCompatActivity{

    private FragmentsAdapter mFragmentsAdapter;
    private ViewPager mViewPager;
    private ArrayList<Symbol> mSymbolsList;
    private ImageButton mBackBtn;
    private ArrayList<Symbol> mSymbolsList1;
    private String type;
    private android.support.v7.widget.Toolbar mToolbar;
    private TextView mTitle;
    private static final String KATAKANA = "Katakana";
    private static final String HIRAKANA = "Hirakana";
    private boolean mRemember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbols_menu);

        Bundle listBundle = getIntent().getExtras();

        if(listBundle != null){
            mSymbolsList = listBundle.getParcelableArrayList("symbols");
            mSymbolsList1 = listBundle.getParcelableArrayList("symbols1");
            type = listBundle.getString("type");
            mRemember = listBundle.getBoolean("remember");
        }
        mTitle = findViewById(R.id.symbolMenuHeader);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        setmTitle(type);

        mFragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager());
        mBackBtn = findViewById(R.id.symbolsMenuBack);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mFragmentsAdapter);


        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        Log.d("SymbolMenu", "ACtivity started");

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //Custom fragment adapter
    public class FragmentsAdapter extends FragmentPagerAdapter{

        public FragmentsAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    //SymbolFragment tab1 = new SymbolFragment();
                    return getSymbols(position);
                case 1:
                    //SymbolFragment tab2 = new SymbolFragment();
                    return getSymbols(position);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){

            switch(position){
                case 0:
                    return "K";
                case 1:
                    return "S";


            }
            return null;
        }

        //Helper method for returning SymbolFragments in the FragmentsAdapter
        public SymbolFragment getSymbols(int position){
            SymbolFragment symbolFragment = new SymbolFragment();
            Bundle symbolsBundle = new Bundle();

            if(position == 0){
                symbolsBundle.putParcelableArrayList("symbolsList", mSymbolsList);
            }else{
                symbolsBundle.putParcelableArrayList("symbolsList", mSymbolsList1);
            }
            // need a way to return symbolsList here


            symbolFragment.setArguments(symbolsBundle);
            return symbolFragment;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem help =  menu.findItem(R.id.action_help);
        MenuItem symbolsKatakana = menu.findItem(R.id.action_symbols_katakana);
        MenuItem symbolsHirakana = menu.findItem(R.id.action_symbols_hirakana);

        if(type.equals("Hirakana")){
            symbolsHirakana.setVisible(false);
        }else{
            symbolsKatakana.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        /*
        * Check what menu item is pressed and if user has already pressed one symbolmenu type
        * onBackPressed is called if he has pressed
         */
        if(id == R.id.action_symbols_hirakana && !mRemember){
            Intent symbolIntent = new Intent(SymbolMenu.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mSymbolsList);
            symbolIntent.putParcelableArrayListExtra("symbols1", mSymbolsList1);
            symbolIntent.putExtra("type", HIRAKANA);
            symbolIntent.putExtra("remember", true);
            startActivity(symbolIntent);
        }else if(id == R.id.action_help){
            Intent helpIntent = new Intent(SymbolMenu.this, HelpActivity.class);
            startActivity(helpIntent);
        }else if(id == R.id.action_symbols_katakana && !mRemember){
            Intent symbolIntent = new Intent(SymbolMenu.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mSymbolsList);
            symbolIntent.putParcelableArrayListExtra("symbols1", mSymbolsList1);
            symbolIntent.putExtra("type", KATAKANA);
            symbolIntent.putExtra("remember", true);
            startActivity(symbolIntent);
        }else{
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setmTitle(String title){
        String finalTitle = title + " Symbols";
        mTitle.setText(finalTitle);
    }

}
