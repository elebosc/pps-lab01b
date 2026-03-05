package it.unibo.pps.e2.logic.components.board;

import it.unibo.pps.e2.utils.Pair;
import it.unibo.pps.e2.logic.components.pieces.Knight;
import it.unibo.pps.e2.logic.components.pieces.KnightImpl;
import it.unibo.pps.e2.logic.components.pieces.Pawn;

import java.util.Random;

/**
 * This class provides the implementation of the board.
 */
public class BoardImpl implements Board {

    private final int size;
    private final Pawn pawn;
    private final Knight knight;

    public BoardImpl(int size, int seed) {
        this.size = size;
        this.pawn = new Pawn(this.getRandomEmptyPosition(seed));
        this.knight = new KnightImpl(this.getRandomEmptyPosition(seed));
    }

    public BoardImpl(
        int size,
        Pair<Integer, Integer> initialKnightPosition,
        Pair<Integer, Integer> pawnPosition
    ) {
        this.size = size;
        this.knight = new KnightImpl(initialKnightPosition);
        this.pawn = new Pawn(pawnPosition);
    }

    private Pair<Integer,Integer> getRandomEmptyPosition(int seed) {
        final Random random = new Random(seed);
        Pair<Integer,Integer> position = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        if (this.pawn != null) {
            while (this.pawn.position().equals(position)) {
                position = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
            }
        }
        return position;
    }

    @Override
    public boolean isPositionOutOfBounds(Pair<Integer, Integer> position) {
        return position.getX() < 0 || position.getY() < 0
            || position.getX() >= this.size || position.getY() >= this.size;
    }

    @Override
    public boolean isKnightAtPosition(Pair<Integer, Integer> position) {
        return this.knight.getPosition().equals(position);
    }

    @Override
    public boolean isPawnAtPosition(Pair<Integer, Integer> position) {
        return this.pawn.position().equals(position);
    }

    @Override
    public boolean canKnightMoveToPosition(Pair<Integer, Integer> position) {
        return this.knight.canMoveToPosition(position);
    }

    @Override
    public void moveKnightToPosition(Pair<Integer, Integer> position) {
        this.knight.setPosition(position);
    }

    @Override
    public boolean hasKnightHitPawn() {
        return this.knight.getPosition().equals(this.pawn.position());
    }


}
