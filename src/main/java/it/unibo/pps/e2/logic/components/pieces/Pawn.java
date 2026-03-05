package it.unibo.pps.e2.logic.components.pieces;

import it.unibo.pps.e2.utils.Pair;

/**
 * This class provides the implementation of the pawn piece.
 *
 * @param position The position to which the pawn must be placed.
 */
public record Pawn(Pair<Integer, Integer> position) {}
