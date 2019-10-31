package com.example.mediinf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mediinf.R;

public class InfoSplashActivity extends AppCompatActivity {

    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_splash);

        skip = findViewById(R.id.skip_info);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInicio();
            }
        });

    }

    public void showInicio(){

        Intent intent = new Intent(this, MainActivity.class);


            startActivity(intent);


    }

}
