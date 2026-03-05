package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.Pair;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawnPosition;
	private Pair<Integer,Integer> knightPosition;
	private final Random random;
	private final int boardSize;
	 
    public LogicsImpl(int boardSize, int seed) {
    	this.boardSize = boardSize;
		this.random = new Random(seed);
        this.pawnPosition = this.pickRandomEmptyPosition();
        this.knightPosition = this.pickRandomEmptyPosition();
    }

	public LogicsImpl(
		int boardSize,
		Pair<Integer, Integer> initialKnightPosition,
		Pair<Integer, Integer> pawnPosition
	) {
		this.boardSize = boardSize;
		this.random = new Random();
		this.knightPosition = initialKnightPosition;
		this.pawnPosition = pawnPosition;
	}
    
	private Pair<Integer,Integer> pickRandomEmptyPosition() {
    	Pair<Integer,Integer> position = new Pair<>(this.random.nextInt(boardSize), this.random.nextInt(boardSize));
		if (this.pawnPosition != null) {
			while (this.pawnPosition.equals(position)) {
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
		int x = position.getX() - this.knightPosition.getX();
		int y = position.getY() - this.knightPosition.getY();
		return x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3;
	}
    
	@Override
	public boolean hit(Pair<Integer, Integer> position) {
		checkPositionIsNotOutOfBound(position);
		if (isKnightMoveAllowed(position)) {
			this.knightPosition = position;
			return this.pawnPosition.equals(this.knightPosition);
		}
		return false;
	}

	@Override
	public boolean hasKnight(Pair<Integer, Integer> position) {
		return this.knightPosition.equals(position);
	}

	@Override
	public boolean hasPawn(Pair<Integer, Integer> position) {
		return this.pawnPosition.equals(position);
	}
}
