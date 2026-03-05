package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.logics.components.Pair;

public interface Logics{
    
    /**
     * attempt to move Knight on position row,col, if possible
     * 
     * @param position
     * @return whether the pawn has been hit 
     */
    boolean hit(Pair<Integer, Integer> position);
    
    /**
     * @param position
     * @return whether position row,col has the knight
     */
    boolean hasKnight(Pair<Integer, Integer> position);
    
    /**
     * @param position
     * @return whether position row,col has the pawn
     */
    boolean hasPawn(Pair<Integer, Integer> position);

}
