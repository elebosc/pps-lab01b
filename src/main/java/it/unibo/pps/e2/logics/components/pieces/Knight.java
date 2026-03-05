package it.unibo.pps.e2.logics.components.pieces;

import it.unibo.pps.e2.logics.components.Pair;

public interface Knight {

    Pair<Integer, Integer> getPosition();

    void setPosition(Pair<Integer, Integer> position);

    boolean canMoveToPosition(Pair<Integer, Integer> position);

}
