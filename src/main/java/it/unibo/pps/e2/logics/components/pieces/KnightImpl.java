package it.unibo.pps.e2.logics.components.pieces;

import it.unibo.pps.e2.logics.components.Pair;

public class KnightImpl implements Knight {

    private static final int MOVE_DELTA = 3;

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

    @Override
    public boolean canMoveToPosition(Pair<Integer, Integer> position) {
        final Pair<Integer, Integer> moveOffsets = new Pair<>(
            position.getX() - this.position.getX(),
            position.getY() - this.position.getY()
        );
        return moveOffsets.getX() != 0 && moveOffsets.getY() != 0
            && Math.abs(moveOffsets.getX()) + Math.abs(moveOffsets.getY()) == MOVE_DELTA;
    }

}
