package com.knatola.kloapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.knatola.kloapp.Symbol.Symbol;

/**
 * Created by juho on 12/28/17.
 */

public class SymbolInfoActivity extends AppCompatActivity {

    private Symbol mSymbol;
    private ImageButton mImgBtn;
    private TextView mSymbolName;
    private TextView mSymbolTavu;
    private int mCounter = 0;
    private ImageButton mBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbol_info_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            mSymbol = bundle.getParcelable("symbol");

        mBackBtn = findViewById(R.id.symbolInfoBack);
        mImgBtn = findViewById(R.id.symbolImgBtn);
        mSymbolName = findViewById(R.id.symbolNameTxt);
        mSymbolTavu = findViewById(R.id.symbolTavuTxt);

        mSymbolName.setText(mSymbol.getName());
        mSymbolTavu.setText(mSymbol.getPic());

        //Stupid hard coded example
        switch(mSymbol.getName()){
            case "ka":
                mImgBtn.setImageResource(R.drawable.ka21);
                break;
            case "ke":
                mImgBtn.setImageResource(R.drawable.ke1);
                break;
            case "ki":
                mImgBtn.setImageResource(R.drawable.ki1);
                break;
            case "ko":
                mImgBtn.setImageResource(R.drawable.ko1);
                break;
            case "ku":
                mImgBtn.setImageResource(R.drawable.ku1);
                break;

        }

        mImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePic(mSymbol);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //Stupid method to change pic, just for example reasons
    public void changePic(Symbol symbol){
        String symbolName = symbol.getName();

        switch (symbolName){
            case "ka":
                if(mCounter == 0){
                    mImgBtn.setImageResource(R.drawable.ka22);
                    mCounter ++;
                }else if (mCounter == 1){
                    mImgBtn.setImageResource(R.drawable.ka23);
                    mCounter ++;
                }else{
                    mImgBtn.setImageResource(R.drawable.ka21);
                    mCounter = 0;
                }
                break;

            case "ki":
                if(mCounter == 0){
                    mImgBtn.setImageResource(R.drawable.ki2);
                    mCounter ++;
                }else if (mCounter == 1){
                    mImgBtn.setImageResource(R.drawable.ki3);
                    mCounter ++;
                }else{
                    mImgBtn.setImageResource(R.drawable.ki1);
                    mCounter = 0;
                }break;

            case "ku":
                break;

            case "ke":
                if(mCounter == 0){
                    mImgBtn.setImageResource(R.drawable.ke2);
                    mCounter ++;
                }else if (mCounter == 1){
                    mImgBtn.setImageResource(R.drawable.ke3);
                    mCounter ++;
                }else{
                    mImgBtn.setImageResource(R.drawable.ke1);
                    mCounter = 0;
                }break;

            case "ko":
                if(mCounter == 0){
                    mImgBtn.setImageResource(R.drawable.ko2);
                    mCounter ++;
                }else{
                    mImgBtn.setImageResource(R.drawable.ko1);
                    mCounter = 0;
                }break;
        }
    }
}
