package it.unibo.pps.e2.logics.components.board;

import it.unibo.pps.e2.logics.components.Pair;

public interface Board {

    boolean isPositionOutOfBound(Pair<Integer, Integer> position);

    boolean canKnightMoveToPosition(Pair<Integer, Integer> position);

    void moveKnightToPosition(Pair<Integer, Integer> position);

    boolean hasKnightHitPawn();

    Pair<Integer, Integer> getKnightPosition();

    Pair<Integer, Integer> getPawnPosition();

}
