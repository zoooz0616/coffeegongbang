package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class resultNoPage extends AppCompatActivity {
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_no_page);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        image.setImageResource(R.drawable.rogo);
    }
}