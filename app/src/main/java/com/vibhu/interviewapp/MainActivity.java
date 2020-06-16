package com.vibhu.interviewapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bsq, bdq, bsoa, brn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout front_1 = (LinearLayout)findViewById(R.id.main_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_main_title_bar);

        bsq = (Button)findViewById(R.id.bsq);
        bdq = (Button)findViewById(R.id.bdq);
        bsoa = (Button)findViewById(R.id.bsoa);
        brn = (Button)findViewById(R.id.brn);

        bsq.setOnClickListener(this);
        bdq.setOnClickListener(this);
        bsoa.setOnClickListener(this);
        brn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bsq:
                Intent i = new Intent(MainActivity.this, simple_questions.class);
                startActivity(i);

                break;
            case R.id.bdq:
                Intent j = new Intent(MainActivity.this, difficult_questions.class);
                startActivity(j);

                break;
            case R.id.bsoa:
                Uri uril = Uri.parse("market://search?id=Tushar Ojha");
                Intent gotomarket = new Intent(Intent.ACTION_VIEW, uril);
                startActivity(gotomarket);

                break;
            case R.id.brn:
                    Uri uriil = Uri.parse("market://details?id=" + getPackageName());
                    Intent gotoomarket = new Intent(Intent.ACTION_VIEW, uriil);
                    startActivity(gotoomarket);

                break;
        }

    }
}
