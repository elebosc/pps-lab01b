package it.unibo.pps.e2.logic;

import it.unibo.pps.e2.utils.Pair;
import it.unibo.pps.e2.logic.components.board.Board;
import it.unibo.pps.e2.logic.components.board.BoardImpl;

/**
 * This class provides the implementation of the logic of the game.
 */
public class LogicImpl implements Logic {

	private final Board board;
	 
    public LogicImpl(int boardSize, int seed) {
    	this.board = new BoardImpl(boardSize, seed);
    }

	public LogicImpl(
		int boardSize,
		Pair<Integer, Integer> initialKnightPosition,
		Pair<Integer, Integer> pawnPosition
	) {
		this.board = new BoardImpl(boardSize, initialKnightPosition, pawnPosition);
	}

	@Override
	public boolean isKnightAtPosition(Pair<Integer, Integer> position) {
		return this.board.isKnightAtPosition(position);
	}

	@Override
	public boolean isPawnAtPosition(Pair<Integer, Integer> position) {
		return this.board.isPawnAtPosition(position);
	}

	@Override
	public void moveKnightToPositionIfAllowed(Pair<Integer, Integer> position) {
		if (this.board.isPositionOutOfBounds(position)) {
			throw new IndexOutOfBoundsException("The targeted position is out of the board bounds!");
		}
		this.board.moveKnightToPositionIfAllowed(position);
	}

	@Override
	public boolean hasKnightHitPawn() {
		return this.board.hasKnightHitPawn();
	}

}
