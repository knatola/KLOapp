package com.knatola.kloapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.knatola.kloapp.Game.GameActivity;
import com.knatola.kloapp.Symbol.Symbol;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String LOG = "Main menu:";
    private Button mainMenuBtn1;
    private Button mainMenuBtn2;
    private Button mainMenuBtn3;
    private Button mainMenuBtn4;
    private android.support.v7.widget.Toolbar mToolbar;
    private ArrayList<Symbol> mHiraSymbols;
    private ArrayList<Symbol> mHiraSymbols2;
    private static final String KATAKANA = "Katakana";
    private static final String HIRAKANA = "Hiragana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hard coded Example Hirakana symbol List
        Symbol symbol = new Symbol("ka","か");
        Symbol symbol1 = new Symbol("ki","き");
        Symbol symbol2 = new Symbol("ku", "く");
        Symbol symbol3 = new Symbol("ke", "け");
        Symbol symbol4 = new Symbol("ko", "こ");
        Symbol symbol5 = new Symbol("sa", "さ");
        Symbol symbol6 = new Symbol("shi", "し");
        Symbol symbol7 = new Symbol("su", "す");
        Symbol symbol8 = new Symbol("se", "せ");
        Symbol symbol9 = new Symbol("so", "そ");
        mHiraSymbols = new ArrayList<>();
        mHiraSymbols2 = new ArrayList<>();
        mHiraSymbols.add(symbol);
        mHiraSymbols.add(symbol1);
        mHiraSymbols.add(symbol2);
        mHiraSymbols.add(symbol3);
        mHiraSymbols.add(symbol4);
        mHiraSymbols2.add(symbol5);
        mHiraSymbols2.add(symbol6);
        mHiraSymbols2.add(symbol7);
        mHiraSymbols2.add(symbol8);
        mHiraSymbols2.add(symbol9);

        mainMenuBtn1 = findViewById(R.id.mainMenuBtn1);
        mainMenuBtn2 = findViewById(R.id.mainMenuBtn2);
        mainMenuBtn3 = findViewById(R.id.mainMenuBtn3);
        mainMenuBtn4 = findViewById(R.id.mainMenuBtn4);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mainMenuBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG,"button 1 pressed");
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                //mHiraSymbols.addAll(mHiraSymbols2);
                gameIntent.putParcelableArrayListExtra("symbols", mHiraSymbols);
                gameIntent.putParcelableArrayListExtra("symbols1", mHiraSymbols2);
                startActivity(gameIntent);//game menu intent here
            }
        });

        mainMenuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hiraIntent = new Intent(MainActivity.this, SymbolMenu.class);
                hiraIntent.putParcelableArrayListExtra("symbols", mHiraSymbols);
                hiraIntent.putParcelableArrayListExtra("symbols1", mHiraSymbols2);
                hiraIntent.putExtra("type", HIRAKANA);
                startActivity(hiraIntent);
            }
        });

        mainMenuBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kataIntent = new Intent(MainActivity.this, SymbolMenu.class);
                kataIntent.putParcelableArrayListExtra("symbols", mHiraSymbols);
                kataIntent.putParcelableArrayListExtra("symbols1", mHiraSymbols2);
                kataIntent.putExtra("type", KATAKANA);
                startActivity(kataIntent);
            }
        });

        mainMenuBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //Check the button pressed and start intent
        if(id == R.id.action_symbols_hirakana){
            Intent symbolIntent = new Intent(MainActivity.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mHiraSymbols);
            symbolIntent.putParcelableArrayListExtra("symbols1", mHiraSymbols2);
            symbolIntent.putExtra("type", HIRAKANA);
            startActivity(symbolIntent);
        }else if(id == R.id.action_help){
            Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(helpIntent);
        }else{
            Intent symbolIntent = new Intent(MainActivity.this, SymbolMenu.class);
            symbolIntent.putParcelableArrayListExtra("symbols", mHiraSymbols);
            symbolIntent.putParcelableArrayListExtra("symbols1", mHiraSymbols2);
            symbolIntent.putExtra("type", KATAKANA);
            startActivity(symbolIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
