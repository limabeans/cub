package com.angelllim.cube.cub;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lima on 5/17/15.
 */
public class SolvesActivity extends Activity {

    private ListView solvesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solves);

        solvesListView = (ListView) findViewById(R.id.solvesListView);

        SolveDB db = new SolveDB(this);
        ArrayList<Solve> solves = db.getSolves();

        for (Solve s : solves) {
            Log.d("solves", s.toString());
        }

        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
        for(Solve s : solves) {
            HashMap<String,String> solv= new HashMap<String,String>();
            solv.put("time", String.valueOf(s.getMillisTime()));
            solv.put("scramble", s.getScramble());

            data.add(solv);
        }

        int resource = R.layout.listview_solve;
        String[] from = {"time","scramble"};
        int[] to = {R.id.timeTextView, R.id.scrambleTextView};

        SimpleAdapter adapter = new SimpleAdapter(this,data,resource,from,to);
        solvesListView.setAdapter(adapter);
    }

}
