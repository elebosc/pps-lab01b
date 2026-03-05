package it.unibo.pps.e2.logic.components.board;

import it.unibo.pps.e2.utils.Pair;

/**
 * This interface models the possible interactions with the board.
 */
public interface Board {

    /**
     * Gets if the specified position is out of the bounds of the board.
     *
     * @param position The targeted position.
     * @return True if the position is out of bounds, false otherwise.
     */
    boolean isPositionOutOfBounds(Pair<Integer, Integer> position);

    /**
     * Gets whether the knight is at the specified position or not.
     *
     * @param position The targeted position.
     * @return True if the knight is at that position, false otherwise.
     */
    boolean isKnightAtPosition(Pair<Integer, Integer> position);

    /**
     * Gets whether the pawn is at the specified position or not.
     *
     * @param position The targeted position.
     * @return True if the pawn is at that position, false otherwise.
     */
    boolean isPawnAtPosition(Pair<Integer, Integer> position);

    /**
     * Gets whether the knight is allowed to move to the specified position
     * from its current position.
     *
     * @param position The targeted position.
     * @return True if the knight can move to the specified position, false otherwise.
     */
    boolean canMoveKnightToPosition(Pair<Integer, Integer> position);

    /**
     * Moves the knight to the specified position.
     *
     * @param position The position to which the knight must be moved.
     */
    void moveKnightToPosition(Pair<Integer, Integer> position);

    /**
     * Gets whether the knight has hit the pawn or not.
     *
     * @return True if the knight has hit the pawn, false otherwise.
     */
    boolean hasKnightHitPawn();

}
