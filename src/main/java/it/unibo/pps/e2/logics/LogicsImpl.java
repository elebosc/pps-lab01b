package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.logics.components.Pair;
import it.unibo.pps.e2.logics.components.board.Board;
import it.unibo.pps.e2.logics.components.board.BoardImpl;

public class LogicsImpl implements Logics {

	private final Board board;
	 
    public LogicsImpl(int boardSize, int seed) {
    	this.board = new BoardImpl(boardSize, seed);
    }

	public LogicsImpl(
		int boardSize,
		Pair<Integer, Integer> initialKnightPosition,
		Pair<Integer, Integer> pawnPosition
	) {
		this.board = new BoardImpl(boardSize, initialKnightPosition, pawnPosition);
	}

	@Override
	public boolean moveKnightToPosition(Pair<Integer, Integer> position) {
		if (this.board.isPositionOutOfBound(position)) {
			throw new IndexOutOfBoundsException("The targeted position is out of board bounds!");
		}
		if (this.board.canKnightMoveToPosition(position)) {
			this.board.moveKnightToPosition(position);
			return this.board.hasKnightHitPawn();
		}
		return false;
	}

	@Override
	public boolean isThereKnightAtPosition(Pair<Integer, Integer> position) {
		return this.board.getKnightPosition().equals(position);
	}

	@Override
	public boolean isTherePawnAtPosition(Pair<Integer, Integer> position) {
		return this.board.getPawnPosition().equals(position);
	}

}
