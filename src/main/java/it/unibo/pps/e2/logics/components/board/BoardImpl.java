package it.unibo.pps.e2.logics.components.board;

import it.unibo.pps.e2.logics.components.Pair;

public class BoardImpl implements Board {

    private final int size;

    public BoardImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean isPositionOutOfBound(Pair<Integer, Integer> position) {
        return position.getX() < 0 || position.getY() < 0
            || position.getX() >= this.size || position.getY() >= this.size;
    }

}
