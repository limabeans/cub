package com.angelllim.cube.cub;

/**
 * Created by lima on 5/17/15.
 */
public class Solve {
    private int id;
    private String scramble;
    private long millisTime;

    public Solve(int id, String scr, long time) {
        this.id = id;
        this.scramble = scr;
        this.millisTime = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScramble(String s) {
        this.scramble = s;
    }

    public void setMillisTime(long t) {
        this.millisTime = t;
    }

    public int getId() {
        return this.id;
    }

    public String getScramble() {
        return this.scramble;
    }

    public long getMillisTime() {
        return this.millisTime;
    }
}
