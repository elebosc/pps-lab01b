package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.logics.components.Pair;
import it.unibo.pps.e2.logics.components.board.Board;
import it.unibo.pps.e2.logics.components.board.BoardImpl;
import it.unibo.pps.e2.logics.components.pieces.Knight;
import it.unibo.pps.e2.logics.components.pieces.KnightImpl;
import it.unibo.pps.e2.logics.components.pieces.Pawn;
import it.unibo.pps.e2.logics.components.pieces.PawnImpl;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pawn pawn;
	private final Knight knight;
	private final Random random;
	private final Board board;
	 
    public LogicsImpl(int boardSize, int seed) {
    	this.board = new BoardImpl(boardSize);
		this.random = new Random(seed);
		this.pawn = new PawnImpl(this.getRandomEmptyPosition(boardSize));
        this.knight = new KnightImpl(this.getRandomEmptyPosition(boardSize));
    }

	public LogicsImpl(
		int boardSize,
		Pair<Integer, Integer> initialKnightPosition,
		Pair<Integer, Integer> pawnPosition
	) {
		this.board = new BoardImpl(boardSize);
		this.random = new Random();
		this.knight = new KnightImpl(initialKnightPosition);
		this.pawn = new PawnImpl(pawnPosition);
	}

	private int getRandomInteger(int bound) {
		return this.random.nextInt(bound);
	}
    
	private Pair<Integer,Integer> getRandomEmptyPosition(int boardSize) {
    	Pair<Integer,Integer> position = new Pair<>(getRandomInteger(boardSize), getRandomInteger(boardSize));
		if (this.pawn.getPosition() != null) {
			while (this.pawn.getPosition().equals(position)) {
				position = new Pair<>(getRandomInteger(boardSize), getRandomInteger(boardSize));
			}
		}
    	return position;
    }
    
	@Override
	public boolean hit(Pair<Integer, Integer> position) {
		if (this.board.isPositionOutOfBound(position)) {
			throw new IndexOutOfBoundsException();
		}
		if (this.knight.canMoveToPosition(position)) {
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
