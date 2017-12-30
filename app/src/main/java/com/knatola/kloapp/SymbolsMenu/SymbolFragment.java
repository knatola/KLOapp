package com.knatola.kloapp.SymbolsMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.knatola.kloapp.R;
import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.Symbol.SymbolListAdapter;
import com.knatola.kloapp.SymbolsMenu.SymbolInfoActivity;

import java.util.ArrayList;

/**
 * Created by knatola on 23.11.2017.
 */

/*
Fragment that displays a passed Symbol List in a ListView.
So Fragment can be reused to display dynamically different symbols.
 */
public class SymbolFragment extends android.support.v4.app.Fragment{

    private View rootView;
    ArrayList<Symbol> symbols;
    private static final String LOG = "SymbolFragment:";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.hirasymbol_layout, container, false);
        //check args
        Bundle args = getArguments();
        if (args != null) {
            symbols = args.getParcelableArrayList("symbolsList");
        }

        //ListView init with the adapter
        ListView symbolsListView = rootView.findViewById(R.id.symbolsList);
        SymbolListAdapter mAdapter = new SymbolListAdapter(getActivity(), R.layout.hirasymbol_list_row,
                symbols);
        symbolsListView.setAdapter(mAdapter);

        //OnClckListener for the listview items. Clicked symbol is passed to SymbolInfoActivity
        symbolsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(LOG, symbols.get(i).getName());
                Bundle symbolBundle = new Bundle();
                symbolBundle.putParcelable("symbol", symbols.get(i));
                Intent infoIntent = new Intent(getActivity(), SymbolInfoActivity.class);
                infoIntent.putExtras(symbolBundle);
                startActivity(infoIntent);
            }
        });

        return rootView;
    }
}
