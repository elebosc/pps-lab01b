package it.unibo.pps.e2.logic.components.pieces;

import it.unibo.pps.e2.utils.Pair;

/**
 * This interface models the possible interactions with the knight.
 */
public interface Knight {

    /**
     * Gets the current position of the knight.
     *
     * @return The position of the knight.
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Sets the position of the knight to the specified one.
     *
     * @param position The targeted position.
     */
    void setPosition(Pair<Integer, Integer> position);

    /**
     * Gets whether the knight is allowed to move to the specified position
     * from its current position.
     *
     * @param position The targeted position.
     * @return True if the knight can move to the specified position, false otherwise.
     */
    boolean canMoveToPosition(Pair<Integer, Integer> position);

}
