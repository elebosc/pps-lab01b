package it.unibo.pps.e2.logic;

import it.unibo.pps.e2.utils.Pair;

/**
 * This interface models the possible interactions with the logic of the game.
 */
public interface Logic {
    
    /**
     * Gets whether the knight is at the specified position or not.
     *
     * @param position The position at which the presence of the knight must be checked.
     * @return True if the knight is at that position, false otherwise.
     */
    boolean isKnightAtPosition(Pair<Integer, Integer> position);

    /**
     * Gets whether the pawn is at the specified position or not.
     *
     * @param position The position at which the presence of the pawn must be checked.
     * @return True if the pawn is at that position, false otherwise.
     */
    boolean isPawnAtPosition(Pair<Integer, Integer> position);

    /**
     * Attempts to move the knight to the specified position,
     * if allowed to move it there from its current position.
     *
     * @param position The targeted position.
     * @throws IndexOutOfBoundsException if the position is out of the bounds of the board.
     */
    void moveKnightToPositionIfAllowed(Pair<Integer, Integer> position);

    /**
     * Gets whether the knight has hit the pawn or not.
     *
     * @return True if the knight has hit the pawn, false otherwise.
     */
    boolean hasKnightHitPawn();

}
