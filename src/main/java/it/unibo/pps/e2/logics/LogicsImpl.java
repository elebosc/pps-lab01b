package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.logics.components.Pair;
import it.unibo.pps.e2.logics.components.pieces.Knight;
import it.unibo.pps.e2.logics.components.pieces.KnightImpl;
import it.unibo.pps.e2.logics.components.pieces.Pawn;
import it.unibo.pps.e2.logics.components.pieces.PawnImpl;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pawn pawn;
	private final Knight knight;
	private final Random random;
	private final int boardSize;
	 
    public LogicsImpl(int boardSize, int seed) {
    	this.boardSize = boardSize;
		this.random = new Random(seed);
		this.pawn = new PawnImpl(this.pickRandomEmptyPosition());
        this.knight = new KnightImpl(this.pickRandomEmptyPosition());
    }

	public LogicsImpl(
		int boardSize,
		Pair<Integer, Integer> initialKnightPosition,
		Pair<Integer, Integer> pawnPosition
	) {
		this.boardSize = boardSize;
		this.random = new Random();
		this.knight = new KnightImpl(initialKnightPosition);
		this.pawn = new PawnImpl(pawnPosition);
	}
    
	private Pair<Integer,Integer> pickRandomEmptyPosition() {
    	Pair<Integer,Integer> position = new Pair<>(this.random.nextInt(boardSize), this.random.nextInt(boardSize));
		if (this.pawn.getPosition() != null) {
			while (this.pawn.getPosition().equals(position)) {
				position = new Pair<>(this.random.nextInt(boardSize), this.random.nextInt(boardSize));
			}
		}
    	return position;
    }

	private void checkPositionIsNotOutOfBound(Pair<Integer, Integer> position) {
		if (position.getX() < 0 || position.getY() < 0
			|| position.getX() >= this.boardSize || position.getY() >= this.boardSize) {
			throw new IndexOutOfBoundsException();
		}
	}

	private boolean isKnightMoveAllowed(Pair<Integer, Integer> position) {
		final Pair<Integer, Integer> targetPosition = new Pair<>(
			position.getX() - this.knight.getPosition().getX(),
			position.getY() - this.knight.getPosition().getY()
		);
		return targetPosition.getX() != 0 && targetPosition.getY() != 0
			&& Math.abs(targetPosition.getX()) + Math.abs(targetPosition.getY()) == 3;
	}
    
	@Override
	public boolean hit(Pair<Integer, Integer> position) {
		checkPositionIsNotOutOfBound(position);
		if (isKnightMoveAllowed(position)) {
			this.knight.setPosition(position);
			return this.pawn.getPosition().equals(this.knight.getPosition());
		}
		return false;
	}

	@Override
	public boolean hasKnight(Pair<Integer, Integer> position) {
		return this.knight.getPosition().equals(position);
	}

	@Override
	public boolean hasPawn(Pair<Integer, Integer> position) {
		return this.pawn.getPosition().equals(position);
	}

}
