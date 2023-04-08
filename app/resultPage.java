package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class resultPage extends AppCompatActivity {
    ImageButton arrow_left;
    ImageButton arrow_right;
    ImageView coffee_result;
    TextView text;


    private int x=0;
    private int c=0;
    private int count = 0;
    private String type;
    private boolean tf;
    private ArrayList<coffeeInformation> coffee;
    private int[] result_menu_number = new int[100];
    private boolean[] checkMenu = {false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        arrow_left = (ImageButton) findViewById(R.id.arrow_left);
        arrow_right = (ImageButton) findViewById(R.id.arrow_right);
        coffee_result = (ImageView) findViewById(R.id.coffee_result);
        text = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();

        type = intent.getStringExtra("type");
        coffee = (ArrayList<coffeeInformation>) intent.getSerializableExtra("coffee");
        checkMenu = intent.getExtras().getBooleanArray("checkMenu");

        text.setMovementMethod(new ScrollingMovementMethod());


        x=0;
        result_menu_number[0] = -1;

        count=0;
        while(x < 329){
            if((x>90&&x<100)||(x>129&&x<200)||(x>281&&x<300)||(x>326)) {
                x++;
                continue;
            }
            tf = true;
            for(int j=0;j<7;j++){
                if(coffee.get(x).type.charAt(j)!=type.charAt(j)){
                    tf = false;
                }
            }
            if(tf==true&&checkMenu[x/100]==true) {
                    result_menu_number[count] = x;
                    count++;
            }
            x++;
        }

        String a ="";
        for(int u=0;u<400;u++){
            if(result_menu_number[0]==u) {
                if(u>=0&&u<100) {
                    a = "hollys" + (u);
                }
                else if(u>=100&&u<200){
                    a = "starbucks"+(u);
                }
                else if(u>=200&&u<300){
                    a = "tomtom" + (u);
                }
                else if(u>=300&&u<400){
                    a = "twosome" + (u);
                }
                break;
            }
        }
        int res = getResources().getIdentifier(a, "drawable", getPackageName());

        if(result_menu_number[0]==-1) {
            coffee_result.setImageResource(R.drawable.rogo);
            text.setText("결과가 없습니다.");
        }
        else {
            coffee_result.setImageResource(res);
            text.setText("카페: " + coffee.get(result_menu_number[0]).cafeName + "\n" +
                    "메뉴: " + coffee.get(result_menu_number[0]).name + "\n" +
                    "내용: " + coffee.get(result_menu_number[0]).explanation + "\n" +
                    "용량: " + coffee.get(result_menu_number[0]).size + "\n" +
                    "가격: " + coffee.get(result_menu_number[0]).price + "\n" +
                    "칼로리: " + coffee.get(result_menu_number[0]).calorie + "\n" +
                    "카페인: " + coffee.get(result_menu_number[0]).caffine + "\n" +
                    "당류: " + coffee.get(result_menu_number[0]).sweet + "\n" +
                    "포화지방: " + coffee.get(result_menu_number[0]).saturation + "\n" +
                    "나트륨: " + coffee.get(result_menu_number[0]).protein);
        }

        arrow_right.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                if(c>=count-1){
                    Toast.makeText(getApplicationContext(),"더 이상 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    c++;
                    String a ="";
                    for(int u=0;u<=400;u++){
                        if(result_menu_number[c]==u) {
                            if(u>=0&&u<100) {
                                a = "hollys" + (u);
                            }
                            else if(u>=100&&u<200){
                                a = "starbucks"+(u);
                            }
                            else if(u>=200&&u<300){
                                a = "tomtom" + (u);
                            }
                            else if(u>=300&&u<400){
                                a = "twosome" + (u);
                            }
                            break;
                        }
                    }

                    int res = getResources().getIdentifier(a, "drawable", getPackageName());
                    coffee_result.setImageResource(res);

                    text.setText("카페: " + coffee.get(result_menu_number[c]).cafeName + "\n" +
                            "메뉴: " + coffee.get(result_menu_number[c]).name + "\n" +
                            "내용: " + coffee.get(result_menu_number[c]).explanation + "\n" +
                            "용량: " + coffee.get(result_menu_number[c]).size + "\n" +
                            "가격: " + coffee.get(result_menu_number[c]).price + "\n" +
                            "칼로리: " + coffee.get(result_menu_number[c]).calorie + "\n" +
                            "카페인: " + coffee.get(result_menu_number[c]).caffine + "\n" +
                            "당류: " + coffee.get(result_menu_number[c]).sweet + "\n" +
                            "포화지방: " + coffee.get(result_menu_number[c]).saturation + "\n" +
                            "나트륨: " + coffee.get(result_menu_number[c]).protein);
                }
            }
        });
        arrow_left.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                if(c<=0){
                    Toast.makeText(getApplicationContext(),"더 이상 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    c--;
                    String a ="";
                    for(int u=0;u<=400;u++){
                        if(result_menu_number[c]==u) {
                            if(u>=0&&u<100) {
                                a = "hollys" + (u);
                            }
                            else if(u>=100&&u<200){
                                a = "starbucks"+(u);
                            }
                            else if(u>=200&&u<300){
                                a = "tomtom" + (u);
                            }
                            else if(u>=300&&u<400){
                                a = "twosome" + (u);
                            }
                            break;
                        }
                    }

                    int res = getResources().getIdentifier(a, "drawable", getPackageName());
                    coffee_result.setImageResource(res);

                    text.setText("카페: " + coffee.get(result_menu_number[c]).cafeName + "\n" +
                            "메뉴: " + coffee.get(result_menu_number[c]).name + "\n" +
                            "내용: " + coffee.get(result_menu_number[c]).explanation + "\n" +
                            "용량: " + coffee.get(result_menu_number[c]).size + "\n" +
                            "가격: " + coffee.get(result_menu_number[c]).price + "\n" +
                            "칼로리: " + coffee.get(result_menu_number[c]).calorie + "\n" +
                            "카페인: " + coffee.get(result_menu_number[c]).caffine + "\n" +
                            "당류: " + coffee.get(result_menu_number[c]).sweet + "\n" +
                            "포화지방: " + coffee.get(result_menu_number[c]).saturation + "\n" +
                            "나트륨: " + coffee.get(result_menu_number[c]).protein);
                }
            }
        });
    }
}