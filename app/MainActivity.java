package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),startPage.class);
                startActivity(intent);
            }
        });
    }
}