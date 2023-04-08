package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;



public class tasteTesting extends AppCompatActivity {
    ImageView image;
    TextView question;
    Button yes;
    Button no;
    boolean[] checkMenu;

    int i = 1;
    String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste_testing);

        Intent intent = getIntent();
        final String a = intent.getExtras().getString("hollys");
        checkMenu = intent.getExtras().getBooleanArray("checkMenu");

        image = (ImageView) findViewById(R.id.image);
        question = (TextView) findViewById(R.id.question);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);


        image.setImageResource(R.drawable.q1);
        question.setText("아이스 음료를 좋아하세요?");



        yes.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                type += "1";
                switch(i){
                    case 0:
                        image.setImageResource(R.drawable.q1);
                        question.setText("아이스 음료를 좋아하세요?");
                        break;
                    case 1:
                        image.setImageResource(R.drawable.q2);
                        question.setText("카페인 음료를 좋아하세요?");
                        break;
                    case 2:
                        image.setImageResource(R.drawable.q3);
                        question.setText("우유를 좋아하세요?");
                        break;
                    case 3:
                        image.setImageResource(R.drawable.q5);
                        question.setText("차를 좋아하세요?");
                        break;
                    case 4:
                        image.setImageResource(R.drawable.q4);
                        question.setText("과일을 좋아하세요?");
                        break;
                    case 5:
                        image.setImageResource(R.drawable.q6);
                        question.setText("달콤한 음료를 좋아하세요?");
                        break;
                    case 6:
                        image.setImageResource(R.drawable.q7);
                        question.setText("초콜릿을 좋아하세요?");
                        break;
                }
                i++;
                if(i>=8){
                    Intent intent = new Intent(getApplicationContext(),mainPage.class);
                    intent.putExtra("type", type);
                    intent.putExtra("checkMenu", checkMenu);
                    intent.putExtra("skip", true);
                    startActivity(intent);
                }
            }
        });

        no.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                type+="0";
                switch(i){
                    case 0:
                        image.setImageResource(R.drawable.q1);
                        question.setText("아이스 음료를 좋아하세요?");
                        break;
                    case 1:
                        image.setImageResource(R.drawable.q2);
                        question.setText("카페인 음료를 좋아하세요?");
                        break;
                    case 2:
                        image.setImageResource(R.drawable.q3);
                        question.setText("우유를 좋아하세요?");
                        break;
                    case 3:
                        image.setImageResource(R.drawable.q5);
                        question.setText("차를 좋아하세요?");
                        break;
                    case 4:
                        image.setImageResource(R.drawable.q4);
                        question.setText("과일을 좋아하세요?");
                        break;
                    case 5:
                        image.setImageResource(R.drawable.q6);
                        question.setText("달콤한 음료를 좋아하세요?");
                        break;
                    case 6:
                        image.setImageResource(R.drawable.q7);
                        question.setText("초콜릿을 좋아하세요?");
                        break;
                }
                i++;
                if(i>=8){
                    Intent intent = new Intent(getApplicationContext(),mainPage.class);
                    intent.putExtra("type", type);
                    intent.putExtra("checkMenu", checkMenu);
                    intent.putExtra("skip", true);
                    startActivity(intent);
                }
            }
        });
    }
}