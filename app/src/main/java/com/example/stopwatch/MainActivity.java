package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int second=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            running=savedInstanceState.getBoolean("running");
            second=savedInstanceState.getInt("seconds");
        }
        runTime();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putInt("seconds", second);
    }

    private void runTime() {
        final TextView time=(TextView) findViewById(R.id.textView);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour=second/3600;
                int minute=(second%3600)/60;
                int sec=second%60;

                time.setText(String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minute,sec));
                if(running)
                {
                    second++;
                }
                handler.postDelayed(this,1000);
            }

        });


    }

    public void onStart(View view) {
        running=true;
    }

    public void onPause(View view) {
        running=false;
    }

    public void onReset(View view) {
        running=false;
        second=0;
    }
}