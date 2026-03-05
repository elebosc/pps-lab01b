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
     * Sets the position of the knight to the specified one,
     * if allowed to move there from its current position.
     *
     * @param position The targeted position.
     */
    void moveToPositionIfAllowed(Pair<Integer, Integer> position);

}
