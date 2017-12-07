package com.knatola.kloapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.SymbolFragment;


import java.util.ArrayList;

/**
 * Created by knatola on 20.11.2017.
 */

public class SymbolMenu extends AppCompatActivity{

    private FragmentsAdapter mFragmentsAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbols_menu);

        mFragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mFragmentsAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
            return 2;
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
