package com.angelllim.cube.cub;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnTouchListener {


    private TextView currentTimeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout thisScreen = (RelativeLayout) findViewById(R.id.timerScreen);
        thisScreen.setOnTouchListener(this);

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


    long startTime;
    Handler handle = new Handler();
    Handler handle2 = new Handler();
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        currentTimeTextView.setText("0.000");
        currentTimeTextView.setTextColor(Color.RED);
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handle.postDelayed(null,500);
                break;
            case MotionEvent.ACTION_UP:
                startTime = System.currentTimeMillis();
                Runnable time = new Runnable() {
                    @Override
                    public void run() {
                        long timeInMillis = System.currentTimeMillis() - startTime;
                        currentTimeTextView.setText(String.valueOf(timeInMillis));
                    }
                };
                handle2.postDelayed(time, 0);
                break;
            default:
                currentTimeTextView.setTextColor(Color.BLACK);
                break;

        }
        return true;
    }
}
