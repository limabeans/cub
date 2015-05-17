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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout thisScreen = (RelativeLayout) findViewById(R.id.timerScreen);
        thisScreen.setOnClickListener(this);

        currentTimeTextView = (TextView) findViewById(R.id.currentTime);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    long startTime = 0;
    boolean timeRunning = false;
    Runnable timerRunnable = new TimerTask() {
        @Override
        public void run() {
            final long start = startTime;
            long millis = System.currentTimeMillis() - start;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds     = seconds % 60;
            currentTimeTextView.setText(String.valueOf(millis));
            timerHandler.postDelayed(this, 10);
        }
    };

    Handler timerHandler = new Handler();
    Timer timer = new Timer();

    @Override
    public void onClick(View v) {
        if (!timeRunning) {
            timerHandler.removeCallbacks(timerRunnable);
        } else {
            startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
        }
        timeRunning=!timeRunning;
    }
}
