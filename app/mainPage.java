package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class mainPage extends AppCompatActivity {
    ArrayList<coffeeInformation> coffee = new ArrayList<coffeeInformation>();
    int result_menu_number;
    boolean[] checkMenu;
    int i;
    boolean tf;
    String type;
    boolean skip;

    ImageView mycoffee_img;
    TextView mycoffee_name;
    Button mycoffee_more;

    ImageButton hollys;
    ImageButton starbucks;
    ImageButton twosome;
    ImageButton tomntoms;

    TextView weather_text;
    TextView todaycoffee_name;
    ImageView weather;
    ImageView todaycoffee_img;
    Button main_shortWeather;
    Random rnd;

    ImageButton map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        map = (ImageButton) findViewById(R.id.map);
        
        map.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        mycoffee_img = (ImageView) findViewById(R.id.mycoffee_img);
        mycoffee_name = (TextView) findViewById(R.id.mycoffee_name);
        mycoffee_more = (Button) findViewById(R.id.mycoffee_more);

        hollys = (ImageButton) findViewById(R.id.hollys);
        starbucks = (ImageButton) findViewById(R.id.starbucks);
        twosome = (ImageButton) findViewById(R.id.twosome);
        tomntoms = (ImageButton) findViewById(R.id.tomntoms);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        checkMenu = intent.getExtras().getBooleanArray("checkMenu");
        skip = intent.getExtras().getBoolean("skip");

        coffee.clear();
        String tmp = makeDataString(R.raw.hollys_menu);
        makeClass(tmp,"할리스");

        tmp = makeDataString(R.raw.starbucks_menu);
        makeClass(tmp,"스타벅스");

        tmp = makeDataString(R.raw.tomtom_menu);
        makeClass(tmp,"탐앤탐스");

        tmp = makeDataString(R.raw.twosome_menu);
        makeClass(tmp,"투썸플레이스");

        weather_text = (TextView) findViewById(R.id.weather_text);
        weather = (ImageView) findViewById(R.id.weather);
        main_shortWeather = (Button) findViewById(R.id.main_shortWeather);
        weather =(ImageView) findViewById(R.id.weather);
        todaycoffee_img = (ImageView) findViewById(R.id.todaycoffee_img);
        todaycoffee_name = (TextView)findViewById(R.id.todaycoffee_name);
        //크롤링
        WeatherConnection weatherConnection = new WeatherConnection();
        AsyncTask<String, String, String> result = weatherConnection.execute("", "");

        main_shortWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShortWeatherActivity.class);
                startActivity(intent);
            }
        }); //상세 3일예보 보기

        //랜덤함수
        rnd = new Random();
        int Randnum = rnd.nextInt(326);
        System.out.println(Randnum);

        try {
            String msg = result.get();
            System.out.println(msg);
            weather_text.setText(msg.toString());


            if(true){
                if(msg.equals("맑음")){
                    weather.setImageResource(R.drawable.sunny);
                    //String[] values = getResources().getStringArray(R.array.sunny_coffee);
                    if (0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n "+ coffee.get(Randnum).name);
                    } else if (100 <= Randnum && Randnum <= 129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name );
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    } else {
                        todaycoffee_img.setImageResource(R.drawable.hollys0);
                        todaycoffee_name.setText("할리스 커피\n더블샷 바닐라 딜라이트");
                    }

                } else if (msg.equals("눈")){
                    weather.setImageResource(R.drawable.snow);

                    if(0 <= Randnum &&Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.starbucks100);
                        todaycoffee_name.setText("스타벅스 커피\n나이트로 바닐라 크림 콜드브루");
                    }

                } else if (msg.equals("흐림")) {
                    weather.setImageResource(R.drawable.cloud);

                    if(0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.tomtom213);
                        todaycoffee_name.setText("탐앤탐스 커피\n핫초콜릿");
                    }

                } else if (msg.equals("흐리고 가끔  비")) {
                    weather.setImageResource(R.drawable.rainandcloud);
                    if(0 <= Randnum &&Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.tomtom259);
                        todaycoffee_name.setText("탐앤탐스 커피\n카페모카");
                    }

                }  else if (msg.equals("흐리고 한때 비")) {
                    weather.setImageResource(R.drawable.rainandcloud);
                    if(0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.tomtom265);
                        todaycoffee_name.setText("탐앤탐스 커피\n시나몬 카페모카");
                    }
                }  else if (msg.equals("흐리고 비")) {
                    weather.setImageResource(R.drawable.rainandcloud);
                    if(0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.tomtom245);
                        todaycoffee_name.setText("탐앤탐스 커피\n시나몬 초콜릿 탐앤치노");
                    }
                } else if (msg.equals("구름많음")) {
                    weather.setImageResource(R.drawable.cloud);
                    if(0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.starbucks110);
                        todaycoffee_name.setText("스타벅스 커피\n자바 칩 프라푸치노");
                    }

                } else if (msg.equals("구름조금")) {
                    weather.setImageResource(R.drawable.cloud);
                    if(0 <= Randnum &&Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.starbucks101);
                        todaycoffee_name.setText("스타벅스 커피\n돌체 콜드 브루");
                    }

                } else if (msg.equals("약한비")) {
                    weather.setImageResource(R.drawable.rain);
                    if(0 <= Randnum &&Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.twosome306);
                        todaycoffee_name.setText("투썸플레이스 커피\n아이스 카페라떼");

                    }
                } else if (msg.equals("비")) {
                    weather.setImageResource(R.drawable.rain);
                    if(0 <= Randnum &&Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    }else if (100 <= Randnum && Randnum <=129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    }else {
                        todaycoffee_img.setImageResource(R.drawable.twosome309);
                        todaycoffee_name.setText("투썸플레이스 커피\n아이스 스페니쉬 연유 라떼");
                    }
                }  else if (msg.equals("강한비")) {
                    weather.setImageResource(R.drawable.rain);
                    if (0 <= Randnum && Randnum <= 90) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("hollys" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("할리스 커피\n"+ coffee.get(Randnum).name);
                    } else if (100 <= Randnum && Randnum <= 129) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("starbucks" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("스타벅스 커피\n"+ coffee.get(Randnum).name);
                    } else if (200 <= Randnum && Randnum <= 281) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("tomtom" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("탐앤탐스 커피\n"+ coffee.get(Randnum).name);
                    } else if (300 <= Randnum && Randnum <= 326) {
                        todaycoffee_img.setImageResource(getApplicationContext().getResources().getIdentifier("twosome" + Randnum, "drawable", getPackageName()));
                        todaycoffee_name.setText("투썸플레이스 커피\n"+ coffee.get(Randnum).name);
                    } else {
                        todaycoffee_img.setImageResource(R.drawable.twosome319);
                        todaycoffee_name.setText("투썸플레이스 커피\n애플민트티");
                    }
                }else{
                    weather.setImageResource(R.drawable.question);
                    todaycoffee_img.setImageResource(R.drawable.rogo);
                }
            }
        } catch (Exception e) {

        }

        if(skip==true) {
            i = 0;
            result_menu_number = -1;

            while (i < 327) {
                if ((i > 90 && i < 100) || (i > 129 && i < 200) || (i > 281 && i < 300) || (i>326)) {
                    i++;
                    continue;
                }
                tf = true;
                for (int j = 0; j < 7; j++) {
                    if (coffee.get(i).type.charAt(j) != type.charAt(j)) {
                        tf = false;
                    }
                }
                if (tf == true && checkMenu[i / 100] == true) {
                    result_menu_number = i;
                    break;
                }
                i++;
            }

            tmp = "";
            int res;

            if (result_menu_number == -1) {
                mycoffee_img.setImageResource(R.drawable.rogo);
                mycoffee_name.setText("결과가 없습니다.");
            }
            else {
                tmp = findImageName(result_menu_number);
                res = getResources().getIdentifier(tmp, "drawable", getPackageName());
                mycoffee_img.setImageResource(res);
                mycoffee_name.setText(coffee.get(result_menu_number).name);
            }

        }
        else{
            mycoffee_img.setImageResource(R.drawable.rogo);
            mycoffee_name.setText("결과가 없습니다.");
        }


        mycoffee_more.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                if(skip==true) {
                    Intent intent = new Intent(getApplicationContext(), resultPage.class);
                    intent.putExtra("type", type);
                    intent.putExtra("coffee", coffee);
                    intent.putExtra("checkMenu", checkMenu);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), resultNoPage.class);
                    startActivity(intent);
                }
            }
        });

        hollys.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), hollys_list.class);
                intent.putExtra("coffee",coffee);
                startActivity(intent);
            }
        });

        starbucks.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), starbucks_list.class);
                intent.putExtra("coffee",coffee);
                startActivity(intent);
            }
        });

        twosome.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), twosome_list.class);
                intent.putExtra("coffee",coffee);
                startActivity(intent);
            }
        });

        tomntoms.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), tomntoms_list.class);
                intent.putExtra("coffee",coffee);
                startActivity(intent);
            }
        });



    }

    // 네트워크 작업은 AsyncTask 를 사용해야 한다
    public class WeatherConnection extends AsyncTask<String, String, String> {
        // 백그라운드에서 작업하게 한다
        @Override
        protected String doInBackground(String... params) {
            // Jsoup을 이용한 날씨데이터 Pasing하기.
            try {
                //String path = "http://weather.naver.com/rgn/townWetr.nhn?naverRgnCd=09650510";
                String path = "https://n.weather.naver.com/today/09290118";
                Document document = Jsoup.connect(path).get();

                Elements elements = document.select("span.weather");
                //Elements elements = document.select("p.cast_txt");
                System.out.println(elements);
                Element targetElement = elements.get(2);
                String text = targetElement.text();
                System.out.println(text);
                return text;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String findImageName(int num){
        String a="";
        for (int u = 0; u < 400; u++) {
            if (num == u) {
                if (u >= 0 && u < 100) {
                    a = "hollys" + (u);
                } else if (u >= 100 && u < 200) {
                    a = "starbucks" + (u);
                } else if (u >= 200 && u < 300) {
                    a = "tomtom" + (u);
                } else if (u >= 300 && u < 400) {
                    a = "twosome" + (u);
                }
                break;
            }
        }
        return a;
    }

    public String makeDataString(int menu){
        InputStream inputStream = getResources().openRawResource(Integer.parseInt(String.valueOf(menu)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i=0;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = byteArrayOutputStream.toString();

        return tmp;
    }

    public void makeClass(String tmp,String cafe){
        String[] arr_t;
        arr_t = tmp.split("\n");

        int n=0;
        while(n<(arr_t.length)){
            coffee.add(new coffeeInformation(arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],arr_t[n++],cafe));
        }
    }
}