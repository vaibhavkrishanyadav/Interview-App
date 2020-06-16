package com.vibhu.interviewapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class simple_questions<dosomething> extends AppCompatActivity implements View.OnClickListener {

    TextView tvques, tvans, tvtl_yy, tvpi_xx;

    Button bleft, bcentre, bright;

    int index, result;

    String[] simple_question, simple_answer;

    TextToSpeech ttsobject;

    private static final String default_answer = "Press ANSWER button to view the answer of the question";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.questions);

        LinearLayout front_1 = (LinearLayout)findViewById(R.id.ques_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        Button bspeak = (Button)findViewById(R.id.bspeak);
        Button bmute = (Button)findViewById(R.id.bmute);
        TextView tv_cat = (TextView)findViewById(R.id.tvques);
        tv_cat.setText("Simple Questions");

        tvques = (TextView)findViewById(R.id.tvquestion);
        tvans = (TextView)findViewById(R.id.tvanswer);
        tvtl_yy = (TextView)findViewById(R.id.tvyy);
        tvpi_xx = (TextView)findViewById(R.id.tvxx);

        bleft = (Button)findViewById(R.id.bleft);
        bcentre = (Button)findViewById(R.id.bcentre);
        bright = (Button)findViewById(R.id.bright);

        simple_question = getResources().getStringArray(R.array.simple_ques);
        simple_answer = getResources().getStringArray(R.array.simple_ans);

        bleft.setOnClickListener(this);
        bcentre.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bmute.setOnClickListener(this);

        index=0;
        tvques.setText(simple_question[index]);
        tvans.setText("Press answer");
        tvpi_xx.setText(String.valueOf(index+1));
        tvtl_yy.setText("/"+String.valueOf(simple_question.length));



        ttsobject = new TextToSpeech(simple_questions.this,
                new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS)
                {
                    result = ttsobject.setLanguage(Locale.ENGLISH);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Feature not supported in your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bleft:
                tvans.setText(default_answer);
                index--;
                if (index == (-1))
                {
                    index = simple_question.length - 1;
                    tvques.setText(simple_question[index]);
                    tvpi_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvques.setText(simple_question[index]);
                    tvpi_xx.setText(String.valueOf(index + 1));
                }
                break;
            case R.id.bcentre:
                tvans.setText(simple_answer[index]);

                break;
            case R.id.bright:
                tvans.setText(default_answer);
                index++;
                if (index == simple_question.length)
                {
                    index =0;
                    tvques.setText(simple_question[index]);
                    tvpi_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvques.setText(simple_question[index]);
                    tvpi_xx.setText(String.valueOf(index + 1));
                }
                break;

            case R.id.bspeak:

                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(),
                            "Feature not supported in your device",
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    if (tvans.getText().toString().equals(default_answer))
                    {}
                    else {
                        ttsobject.speak(simple_answer[index], TextToSpeech.QUEUE_FLUSH, null);
                    }
                }

                break;
            case R.id.bmute:

                if (ttsobject != null){
                    ttsobject.stop();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsobject != null){
            ttsobject.stop();
            ttsobject.shutdown();
        }

    }
}
