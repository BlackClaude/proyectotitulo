package com.example.claud.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        /*loginButton = (TwitterLoginButton) findViewById(R.id.Login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d("succ log","TWITTER LOGIN SUCCESS!");
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;

                login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("fail log","TWITTER LOGIN FAILURE!");
            }
        });
    }

    public void login(TwitterSession session){
        String username = session.getUserName();
        Intent intent = new Intent(this, TwitterHome.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode,resultCode,data);
    }*/
    }
    public void buttonGoBeginner(View v){
        Intent intent = new Intent(this, Params.class);
        startActivity(intent);
    }





}