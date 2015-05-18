package com.angelllim.cube.cub;

import java.text.SimpleDateFormat;

/**
 * Created by lima on 5/17/15.
 */
public class Solve {

    private static final SimpleDateFormat minutesGT10Format = new SimpleDateFormat("mm:ss.SS");
    private static final SimpleDateFormat minutesLT10Format = new SimpleDateFormat("m:ss.SSS");
    private static final SimpleDateFormat secondsLT10Format= new SimpleDateFormat("s.SSS");
    private static final SimpleDateFormat secondsGT10Format= new SimpleDateFormat("ss.SSS");


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

    public String toString() {
        String str = "" + this.millisTime + " - " + this.scramble;
        return str;
    }

    public static String millisTimeFormatted(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        //long hours = (millis / (1000 * 60 * 60)) % 24;

        if (minutes >= 10) {
            return minutesGT10Format.format(millis);
        } else if (minutes >= 1) {
            return minutesLT10Format.format(millis);
        } else if (seconds >= 10) {
            return secondsGT10Format.format(millis);
        } else {
            return secondsLT10Format.format(millis);
        }
    }
}
