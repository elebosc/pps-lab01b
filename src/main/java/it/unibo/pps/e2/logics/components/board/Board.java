package it.unibo.pps.e2.logics.components.board;

import it.unibo.pps.e2.logics.components.Pair;

public interface Board {

    boolean isPositionOutOfBound(Pair<Integer, Integer> position);

}
