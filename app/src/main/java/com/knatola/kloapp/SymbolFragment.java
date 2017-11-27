package com.knatola.kloapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.knatola.kloapp.Symbol.Symbol;
import com.knatola.kloapp.Symbol.SymbolListAdapter;

import java.util.ArrayList;

/**
 * Created by knatola on 23.11.2017.
 */

public class SymbolFragment extends android.support.v4.app.Fragment{

    private View rootView;
    ArrayList<Symbol> symbols;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.hirasymbol_layout, container, false);

        Bundle args = getArguments();

        if (args != null) {
            symbols = args.getParcelableArrayList("symbolsList");
        }

        Symbol symbol = new Symbol("ka","か" );
        Symbol symbol1 = new Symbol("ki","き");
        Symbol symbol2 = new Symbol("ku", "く");

        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.add(symbol);
        symbols.add(symbol1);
        symbols.add(symbol2);

        ListView lista = rootView.findViewById(R.id.symbolsList);

        SymbolListAdapter mAdapter = new SymbolListAdapter(getActivity(), R.layout.hirasymbol_list_row,
                symbols);
        lista.setAdapter(mAdapter);


        return rootView;
    }
}
