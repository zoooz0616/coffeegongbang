package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShortWeatherActivity extends AppCompatActivity {
    TextView textView_shortWeather;
    TextView exweather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_weather);
        textView_shortWeather = (TextView) findViewById(R.id.shortWeather);
        exweather =(TextView) findViewById(R.id.exweather);
        new ReceiveShortWeather().execute();
    }
    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {
        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();
        public Long doInBackground(URL... urls) {
            String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1129057500";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                parseXML(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public void onPostExecute(Long result) {
            String data = "";
            int i;
            for (i = 0; i < shortWeathers.size(); i++) {
                data +=
                        "시간: " + shortWeathers.get(i).getHour() + ", " +    // 시간
                                "날짜: "+shortWeathers.get(i).getDay() + ", " +   // 0 == 오늘 1 == 내일 2 == 모레
                                "기온: " +shortWeathers.get(i).getTemp() + ", " +// 기온
                                "날씨: "+shortWeathers.get(i).getWfKor() + ", " +//한국날씨
                                "강수확률: "+shortWeathers.get(i).getPop() + "\n";
            }
            textView_shortWeather.setText(data);
        }

        void parseXML(String xml) {
            try {
                String tagName = "";
                boolean onHour = false;
                boolean onDay = false;
                boolean onTem = false;
                boolean onWfKor = false;
                boolean onPop = false;
                boolean onEnd = false;
                boolean isItemTag1 = false;
                int i = 0;

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();
                        if (tagName.equals("data")) {
                            shortWeathers.add(new ShortWeather());
                            onEnd = false;
                            isItemTag1 = true;
                        }
                    } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                        if (tagName.equals("hour") && !onHour) {
                            shortWeathers.get(i).setHour(parser.getText());
                            onHour = true;
                        }
                        if (tagName.equals("day") && !onDay) {
                            shortWeathers.get(i).setDay(parser.getText());
                            onDay = true;
                        }
                        if (tagName.equals("temp") && !onTem) {
                            shortWeathers.get(i).setTemp(parser.getText());
                            shortWeathers.get(i).setTemp(parser.getText());
                            onTem = true;
                        }
                        if (tagName.equals("wfKor") && !onWfKor) {
                            shortWeathers.get(i).setWfKor(parser.getText());
                            onWfKor = true;
                        }
                        if (tagName.equals("pop") && !onPop) {
                            shortWeathers.get(i).setPop(parser.getText());
                            onPop = true;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("s06") && onEnd == false) {
                            i++;
                            onHour = false;
                            onDay = false;
                            onTem = false;
                            onWfKor = false;
                            onPop = false;
                            isItemTag1 = false;
                            onEnd = true;
                        }
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}