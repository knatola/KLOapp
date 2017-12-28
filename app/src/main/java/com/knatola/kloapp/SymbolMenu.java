package com.knatola.kloapp;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.SymbolFragment;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbols_menu);

        Bundle listBundle = getIntent().getExtras();
        if(listBundle.getParcelableArrayList("symbols") != null){
            mSymbolsList = listBundle.getParcelableArrayList("symbols");
            mSymbolsList1 = listBundle.getParcelableArrayList("symbols1");
        }


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


}
