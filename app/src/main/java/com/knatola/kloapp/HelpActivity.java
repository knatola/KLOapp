package com.knatola.kloapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by OMISTAJA on 24.11.2017.
 */

public class HelpActivity extends AppCompatActivity {

    private static final String LOG = "HelpActivity:";
    private ImageButton mBackBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        mBackBtn = findViewById(R.id.helpBack);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Log.d(LOG, "Activity started");
    }
}
