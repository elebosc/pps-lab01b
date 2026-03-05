package it.unibo.pps.e2;

import it.unibo.pps.e2.logic.components.board.Board;
import it.unibo.pps.e2.logic.components.board.BoardImpl;
import it.unibo.pps.e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the board component.
 */
public class BoardTest {

    private static final int BOARD_SIZE = 5;
    private static final Pair<Integer, Integer> INITIAL_KNIGHT_POSITION = new Pair<>(1, 0);
    private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0, 2);

    private Board board;

    @BeforeEach
    public void initTest() {
        this.board = new BoardImpl(BOARD_SIZE, INITIAL_KNIGHT_POSITION, PAWN_POSITION);
    }

    @ParameterizedTest
    @CsvSource({
        "-1, 0",
        "0, -1",
        BOARD_SIZE + ", 0",
        "0, " + BOARD_SIZE
    })
    public void testOutOfBoundsPositionIsDetectedAsSuch(int row, int column) {
        final Pair<Integer, Integer> outOfBoundPosition = new Pair<>(row, column);
        assertTrue(this.board.isPositionOutOfBounds(outOfBoundPosition));
    }

    @Test
    public void testValidPositionIsNotDetectedAsOutOfBound() {
        final Pair<Integer, Integer> validPosition = new Pair<>(0, 0);
        assertFalse(this.board.isPositionOutOfBounds(validPosition));
    }

    @Test
    public void testKnightIsDetectedAtItsInitialPosition() {
        assertTrue(this.board.isKnightAtPosition(INITIAL_KNIGHT_POSITION));
    }

    @Test
    public void testKnightIsNotDetectedOutsideItsPosition() {
        final Pair<Integer, Integer> wrongPosition = new Pair<>(
            INITIAL_KNIGHT_POSITION.getX() + 1,
            INITIAL_KNIGHT_POSITION.getY() + 1
        );
        assertFalse(this.board.isKnightAtPosition(wrongPosition));
    }

    @Test
    public void testPawnIsDetectedAtItsInitialPosition() {
        assertTrue(this.board.isPawnAtPosition(PAWN_POSITION));
    }

    @Test
    public void testPawnIsNotDetectedOutsideItsPosition() {
        final Pair<Integer, Integer> wrongPosition = new Pair<>(
            PAWN_POSITION.getX() + 1,
            PAWN_POSITION.getY() + 1
        );
        assertFalse(this.board.isPawnAtPosition(wrongPosition));
    }

    @Test
    public void testKnightIsAbleToMakeAllowedMove() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
        assertTrue(this.board.canMoveKnightToPosition(targetPosition));
    }

    @Test
    public void testKnightIsNotAbleToMakeNotAllowedMove() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(
            INITIAL_KNIGHT_POSITION.getX() + 1,
            INITIAL_KNIGHT_POSITION.getY() + 1
        );
        assertFalse(this.board.canMoveKnightToPosition(targetPosition));
    }

    @Test
    public void testKnightMakesAllowedMove() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
        this.board.moveKnightToPosition(targetPosition);
        assertTrue(this.board.isKnightAtPosition(targetPosition));
    }

    @Test
    public void testKnightHitsPawnWhenMovingToItsPosition() {
        this.board.moveKnightToPosition(PAWN_POSITION);
        assertTrue(this.board.hasKnightHitPawn());
    }

    @Test
    public void testKnightDoesNotHitPawnIfItMissesIt() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
        this.board.moveKnightToPosition(targetPosition);
        assertFalse(this.board.hasKnightHitPawn());
    }

}
