package com.example.claud.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class TwitterHome extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterhome);
    }

    public void buttonlist(View v){
        listTweet();
    }
    //@GET("https://api.twitter.com/1.1/search/tweets.json?q=%20%23bitcoin")
    public void listTweet() {
        new Thread(() -> {
            String urlString = "https://api.twitter.com/1.1/search/tweets.json?q=%20%23bitcoin";

                /*
            */

            URL url;
            StringBuilder response = new StringBuilder();
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("invalid url");
            }

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "OAuth");
                conn.setRequestProperty("Accept","application/json");
                conn.setRequestProperty("OAuth oauth_consumer_key", "RXQfRamUDuZc0reCjS6zilqkY");
                conn.setRequestProperty("oauth_consumer_key_secret", "O9KaItb5FIzaTpI19KOezmMPxXPE1xU8HE9T82fbllooKhCNV5");
                conn.setRequestProperty("oauth_signature_method", "HMAC-SHA1");
                conn.setRequestProperty("oauth_token", "1248927414-G6xtZwXfHDZw2dHpIPG550gX2r36blzHym7ibWA");
                conn.setRequestProperty("oauth_token_secret", "pmWfEFoMqRf9SFXMszdwEQNsdNcMKsScmKztcbBa2oW3T");
                conn.setRequestProperty("oauth_version", "1.0");

                // handle the response
                int status = conn.getResponseCode();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }

                //Here is your json in string format
                String responseJSON = response.toString();
            }




        }).start();



     /*   OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.twitter.com/1.1/search/tweets.json?q=%20%23bitcoin")
                .get()
                .addHeader("personalization_id","%22v1_HGikNOCQxrhTjCCRgX%2Fk8g%3D%3D%22")
                .addHeader("guest_id","v1%253A153195250386274308")
                .addHeader("authorization","Oauth 1.0a")
                .addHeader("oauth_consumer_key","J1e2fEmln8VlcvXQzBxpoZAnc")
                .addHeader("oauth_nonce","qFwvFZHYTBZt6EpGurVVm03vi3MSf0s4")
                .addHeader("oauth_signature","ZYqHb0PVS2ilJd5FsAupAuUxlA4%3D")
                .addHeader("oauth_signature_method","HMAC-SHA1")
                .addHeader("oauth_token","1248927414-G6xtZwXfHDZw2dHpIPG550gX2r36blzHym7ibWA")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("hola", response.body().toString());

            List<Response> responses = Collections.singletonList(response);
            if(responses.isEmpty()){
                Log.d("fail", "listTweet: vacío");
                return null;
            }else {
                return responses;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    }
}



