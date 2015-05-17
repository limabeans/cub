package com.angelllim.cube.cub;

import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private TextView currentTimeTextView;
    private final SimpleDateFormat minutesGT10Format = new SimpleDateFormat("mm:ss.SSS");
    private final SimpleDateFormat minutesLT10Format = new SimpleDateFormat("m:ss.SSS");
    private final SimpleDateFormat secondsLT10Format= new SimpleDateFormat("s.SSS");
    private final SimpleDateFormat secondsGT10Format= new SimpleDateFormat("ss.SSS");

    private long startTime = 0;
    private boolean timeRunning = false;
    private Handler timerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references
        RelativeLayout thisScreen = (RelativeLayout) findViewById(R.id.timerScreen);
        currentTimeTextView = (TextView) findViewById(R.id.currentTime);

        //Set listeners
        thisScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!timeRunning) {
            startTime = System.currentTimeMillis();
            timerHandler.post(timerRunnable);
        } else {
            timerHandler.removeCallbacks(timerRunnable);
        }
        timeRunning=!timeRunning;
    }

    Runnable timerRunnable = new TimerTask() {
        @Override
        public void run() {
            long millisSoFar = System.currentTimeMillis() - startTime;
            long seconds = (millisSoFar / 1000) % 60;
            long minutes = (millisSoFar / (1000 * 60)) % 60;
            //long hours = (millisSoFar / (1000 * 60 * 60)) % 24;

            if (minutes >= 10) {
                currentTimeTextView.setText(minutesGT10Format.format(millisSoFar));
            } else if (minutes >= 1) {
                currentTimeTextView.setText(minutesLT10Format.format(millisSoFar));
            } else if (seconds >= 10) {
                currentTimeTextView.setText(secondsGT10Format.format(millisSoFar));
            } else {
                currentTimeTextView.setText(secondsLT10Format.format(millisSoFar));
            }

            //Recursively call myself again.
            timerHandler.post(this);
        }
    };
}
