package com.knatola.kloapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.knatola.kloapp.Game.GameActivity;


public class MainActivity extends AppCompatActivity {

    private static final String LOG = "Main menu:";
    private Button mainMenuBtn1;
    private Button mainMenuBtn2;
    private Button mainMenuBtn3;
    private Button mainMenuBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuBtn1 = findViewById(R.id.mainMenuBtn1);
        mainMenuBtn2 = findViewById(R.id.mainMenuBtn2);
        mainMenuBtn3 = findViewById(R.id.mainMenuBtn3);
        mainMenuBtn4 = findViewById(R.id.mainMenuBtn4);

        mainMenuBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG,"button 1 pressed");
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameIntent);//game menu intent here
            }
        });

        mainMenuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hiraIntent = new Intent(MainActivity.this, SymbolMenu.class);
                startActivity(hiraIntent);
            }
        });

        mainMenuBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Katakana intent here
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
        if(id ==R.id.action_help){

        }
        return super.onOptionsItemSelected(item);
    }
}
