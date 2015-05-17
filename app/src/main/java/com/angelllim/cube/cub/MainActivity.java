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

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements OnClickListener {
    private TextView currentTimeTextView;

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
            long millis = System.currentTimeMillis() - startTime;
            currentTimeTextView.setText(String.valueOf(millis));
            timerHandler.post(this);
        }
    };
}
