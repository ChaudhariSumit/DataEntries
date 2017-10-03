package com.sumitch.dataentries;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtn;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbtn = (FloatingActionButton) findViewById(R.id.fabadd);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addnew.class));
            }

        });


    }

}
