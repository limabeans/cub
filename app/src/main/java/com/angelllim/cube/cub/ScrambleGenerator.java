package com.angelllim.cube.cub;

/*
 * returns scrambles in String format
 * scrambles are not truly random (Math.random() seeded).
 */
public class ScrambleGenerator {

    static String[] moves = {"F","R","B","L","U","D" };
    static String[] note = {" ", "2 ", "' "};

    public static String genScramble() {
        String scramble = "";
        scramble = scramble + moves[(int)(Math.random()*moves.length)] + note[(int)(Math.random()*note.length)];
        String prevMove = scramble;
        String nextMove = "";
        for (int x = 1; x<=20;x++) {

            nextMove = nextMove(prevMove);
            scramble = scramble + nextMove;
            prevMove = nextMove;
        }
        return scramble.trim();
    }

    /*
     *Generates the next "random" move
     */
    private static String nextMove(String prev) {
        String move = "";
        int index = (int)(Math.random()*moves.length);
        while(moves[index].charAt(0)==prev.charAt(0)) {
            index = (int)(Math.random()*moves.length);
        }
        move = moves[index] + note[(int)(Math.random()*note.length)];
        return move;
    }
}

