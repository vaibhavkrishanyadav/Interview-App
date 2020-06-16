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

public class difficult_questions extends AppCompatActivity implements View.OnClickListener {

    TextView tvques, tvans, tvtl_yy, tvpi_xx;

    Button bleft, bcentre, bright;

    String[] difficult_question, difficult_answer;

    int index, result;

    TextToSpeech ttsobject;

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
        tv_cat.setText("Difficult Questions");

        tvques = (TextView)findViewById(R.id.tvquestion);
        tvans = (TextView)findViewById(R.id.tvanswer);
        tvtl_yy = (TextView)findViewById(R.id.tvyy);
        tvpi_xx = (TextView)findViewById(R.id.tvxx);

        bleft = (Button)findViewById(R.id.bleft);
        bcentre = (Button)findViewById(R.id.bcentre);
        bright = (Button)findViewById(R.id.bright);

        difficult_question = getResources().getStringArray(R.array.difficult_ques);
        difficult_answer = getResources().getStringArray(R.array.difficult_ans);

        bleft.setOnClickListener(this);
        bcentre.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bmute.setOnClickListener(this);

        index=0;
        tvques.setText(difficult_question[index]);
        tvans.setText(difficult_answer[index]);
        tvpi_xx.setText(String.valueOf(index+1));
        tvtl_yy.setText("/"+String.valueOf(difficult_question.length));


        ttsobject = new TextToSpeech(difficult_questions.this,
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
                index--;
                if (index == (-1))
                {
                    index = difficult_question.length - 1;
                    tvques.setText(difficult_question[index]);
                    tvans.setText(difficult_answer[index]);
                    tvpi_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvques.setText(difficult_question[index]);
                    tvans.setText(difficult_answer[index]);
                    tvpi_xx.setText(String.valueOf(index + 1));
                }

                break;
            case R.id.bcentre:
                tvans.setText(difficult_answer[index]);

                break;
            case R.id.bright:
                index++;
                if (index == difficult_answer.length)
                {
                    index =0;
                    tvques.setText(difficult_question[index]);
                    tvans.setText(difficult_answer[index]);
                    tvpi_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvques.setText(difficult_question[index]);
                    tvans.setText(difficult_answer[index]);
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
                    ttsobject.speak(difficult_answer[index], TextToSpeech.QUEUE_FLUSH, null);
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
