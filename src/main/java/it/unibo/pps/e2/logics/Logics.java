package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.logics.components.Pair;

public interface Logics{
    
    /**
     * attempt to move Knight on position row,col, if possible
     * 
     * @param position
     * @return whether the pawn has been hit 
     */
    boolean moveKnightToPosition(Pair<Integer, Integer> position);
    
    /**
     * @param position
     * @return whether position row,col has the knight
     */
    boolean isKnightAtPosition(Pair<Integer, Integer> position);
    
    /**
     * @param position
     * @return whether position row,col has the pawn
     */
    boolean isPawnAtPosition(Pair<Integer, Integer> position);

}
