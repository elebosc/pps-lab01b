package it.unibo.pps.e2.logics.components.pieces;

import it.unibo.pps.e2.logics.components.Pair;

public class KnightImpl implements Knight {

    private Pair<Integer, Integer> position;

    public KnightImpl(Pair<Integer, Integer> initialPosition) {
        this.position = initialPosition;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

}
