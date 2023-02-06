package com.example.assingment_github_api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    EditText usernameEditText;
    Button button;
    TextView title;
    ImageView logo;

    Animation animate_title, animate_btn_et, animate_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        usernameEditText = findViewById(R.id.username);
        logo = findViewById(R.id.github);
        title = findViewById(R.id.splash_text);
        button = findViewById(R.id.button);

        animate_btn_et = AnimationUtils.loadAnimation(this,R.anim.animate_btn_et);
        animate_logo = AnimationUtils.loadAnimation(this, R.anim.animate_logo);
        animate_title = AnimationUtils.loadAnimation(this,R.anim.animate_title);

        button.setAnimation(animate_btn_et);
        usernameEditText.setAnimation(animate_btn_et);
        logo.setAnimation(animate_logo);
        title.setAnimation(animate_title);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(usernameEditText.getText().toString())){
                    usernameEditText.setError("Username cannot be empty");
                }else{
                    String username = usernameEditText.getText().toString();
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });

    }

}