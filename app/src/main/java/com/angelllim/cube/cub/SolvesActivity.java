package com.angelllim.cube.cub;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lima on 5/17/15.
 */
public class SolvesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solves);

        SolveDB db = new SolveDB(this);
        ArrayList<Solve> solves = db.getSolves();

        for (Solve s : solves) {
            Log.d("solves", s.toString());
        }
    }

}
