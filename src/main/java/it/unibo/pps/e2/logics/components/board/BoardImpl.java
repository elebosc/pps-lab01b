package it.unibo.pps.e2.logics.components.board;

import it.unibo.pps.e2.logics.components.Pair;
import it.unibo.pps.e2.logics.components.pieces.Knight;
import it.unibo.pps.e2.logics.components.pieces.KnightImpl;
import it.unibo.pps.e2.logics.components.pieces.Pawn;
import it.unibo.pps.e2.logics.components.pieces.PawnImpl;

import java.util.Random;

public class BoardImpl implements Board {

    private final int size;
    private final Pawn pawn;
    private final Knight knight;

    public BoardImpl(int size, int seed) {
        this.size = size;
        this.pawn = new PawnImpl(this.getRandomEmptyPosition(seed));
        this.knight = new KnightImpl(this.getRandomEmptyPosition(seed));
    }

    public BoardImpl(
        int size,
        Pair<Integer, Integer> initialKnightPosition,
        Pair<Integer, Integer> pawnPosition
    ) {
        this.size = size;
        this.knight = new KnightImpl(initialKnightPosition);
        this.pawn = new PawnImpl(pawnPosition);
    }

    private Pair<Integer,Integer> getRandomEmptyPosition(int seed) {
        final Random random = new Random(seed);
        Pair<Integer,Integer> position = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        if (this.pawn != null) {
            while (this.pawn.getPosition().equals(position)) {
                position = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
            }
        }
        return position;
    }

    @Override
    public boolean isPositionOutOfBound(Pair<Integer, Integer> position) {
        return position.getX() < 0 || position.getY() < 0
            || position.getX() >= this.size || position.getY() >= this.size;
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
        return this.pawn.getPosition().equals(this.knight.getPosition());
    }

    @Override
    public Pair<Integer, Integer> getKnightPosition() {
        return this.knight.getPosition();
    }

    @Override
    public Pair<Integer, Integer> getPawnPosition() {
        return this.pawn.getPosition();
    }

}
