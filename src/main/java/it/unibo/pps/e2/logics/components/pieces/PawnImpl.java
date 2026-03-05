package it.unibo.pps.e2.logics.components.pieces;

import it.unibo.pps.e2.logics.components.Pair;

public class PawnImpl implements Pawn {

    private final Pair<Integer, Integer> position;

    public PawnImpl(Pair<Integer, Integer> initialPosition) {
        this.position = initialPosition;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

}
