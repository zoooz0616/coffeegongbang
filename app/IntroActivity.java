package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class IntroActivity extends AppCompatActivity {
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        linear = (LinearLayout) findViewById(R.id.linear);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.animation1);
        linear.startAnimation(ani);

        IntroThread introThread = new IntroThread(handler);
        introThread.start();

    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(IntroActivity.this, startPage.class);
                startActivity(intent);
            }
        }
    };
}