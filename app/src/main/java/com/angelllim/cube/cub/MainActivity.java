package com.angelllim.cube.cub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import android.preference.PreferenceManager;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private TextView currentTimeTextView;
    private TextView currentScramble;


    private long startTime = 0;
    private long millisTime = 0;
    private boolean timeRunning = false;
    private Handler timerHandler = new Handler();

    private SolveDB db;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references
        RelativeLayout thisScreen = (RelativeLayout) findViewById(R.id.timerScreen);
        currentTimeTextView = (TextView) findViewById(R.id.currentTime);
        currentScramble = (TextView) findViewById(R.id.scramble);

        db = new SolveDB(getApplicationContext());

        //Set listeners
        thisScreen.setOnClickListener(this);
        currentScramble.setText(ScrambleGenerator.genScramble());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onPause() {

        Editor editor = prefs.edit();
        editor.putString("scramble", currentScramble.getText().toString());
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String backupScramble = ScrambleGenerator.genScramble();
        String pastScramble = prefs.getString("scramble", "");

        if(pastScramble.equals("")) {
            pastScramble = backupScramble;
        }

        currentScramble.setText(pastScramble);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_solves:
                Intent solvesIntent = new Intent(getApplicationContext(), SolvesActivity.class);
                startActivity(solvesIntent);
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    public void onClick(View v) {
        if (!timeRunning) {
            startTime = System.currentTimeMillis();
            timerHandler.post(timerRunnable);
        } else {
            // Store time in database.
            Solve solve = new Solve(0, currentScramble.getText().toString(), millisTime);
            db.insertSolve(solve);

            // Prepare for new solve.
            timerHandler.removeCallbacks(timerRunnable);
            currentScramble.setText(ScrambleGenerator.genScramble());
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

            currentTimeTextView.setText(Solve.millisTimeFormatted(millisSoFar));

            millisTime = millisSoFar;
            //Recursively call myself again.
            timerHandler.post(this);
        }
    };


}
