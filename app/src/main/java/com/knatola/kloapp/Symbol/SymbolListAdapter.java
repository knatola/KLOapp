package com.knatola.kloapp.Symbol;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.knatola.kloapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knatola on 20.11.2017.
 */

public class SymbolListAdapter extends ArrayAdapter<Symbol> {

    private Activity context;
    private int id;
    ArrayList<Symbol> symbolList;

    public SymbolListAdapter(Activity context, int resource, ArrayList<Symbol> objects) {
        super(context, resource, objects);
        this.context =  context;
        this.id = resource;
        this.symbolList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id, null);
        }

        final Symbol symbol = symbolList.get(position);
        TextView symbolPic = convertView.findViewById(R.id.symbolPic);
        TextView symbolName = convertView.findViewById(R.id.symbolName);

        symbolPic.setText(symbol.getPic());
        symbolName.setText(symbol.getName());
        return convertView;
    }
}
