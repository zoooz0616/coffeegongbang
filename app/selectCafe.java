package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class selectCafe extends AppCompatActivity {
    Button next;
    boolean[] checkMenu = {false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cafe);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),tasteTesting.class);
                intent.putExtra("checkMenu", checkMenu);
                startActivity(intent);
            }
        });
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.hollys:
                if(checked){
                    checkMenu[0] = true;
                }
                else{
                    checkMenu[0] = false;
                }
                break;
            case R.id.starbucks:
                if(checked){
                    checkMenu[1] = true;
                }
                else{
                    checkMenu[1] = false;
                }
                break;
            case R.id.tomtom:
                if(checked){
                    checkMenu[2] = true;
                }
                else{
                    checkMenu[2] = false;
                }
                break;
            case R.id.twosome:
                if(checked){
                    checkMenu[3] = true;
                }
                else{
                    checkMenu[3] = false;
                }
                break;
        }
    }
}