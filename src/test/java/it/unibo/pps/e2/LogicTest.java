package it.unibo.pps.e2;
import it.unibo.pps.e2.logic.Logic;
import it.unibo.pps.e2.logic.LogicImpl;
import it.unibo.pps.e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the logic module of the game.
 */
public class LogicTest {

  private static final int BOARD_SIZE = 5;
  private static final Pair<Integer, Integer> INITIAL_KNIGHT_POSITION = new Pair<>(1, 0);
  private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0, 2);

  private Logic logic;

  @BeforeEach
  public void initTest() {
    this.logic = new LogicImpl(BOARD_SIZE, INITIAL_KNIGHT_POSITION, PAWN_POSITION);
  }

  @Test
  public void testKnightIsDetectedAtItsInitialPosition() {
    assertTrue(logic.isKnightAtPosition(INITIAL_KNIGHT_POSITION));
  }

  @Test
  public void testKnightIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(
        INITIAL_KNIGHT_POSITION.getX() + 1,
        INITIAL_KNIGHT_POSITION.getY() + 1
    );
    assertFalse(logic.isKnightAtPosition(wrongPosition));
  }

  @Test
  public void testPawnIsDetectedAtItsInitialPosition() {
    assertTrue(logic.isPawnAtPosition(PAWN_POSITION));
  }

  @Test
  public void testPawnIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(
      PAWN_POSITION.getX() + 1,
      PAWN_POSITION.getY() + 1
    );
    assertFalse(logic.isPawnAtPosition(wrongPosition));
  }

  @Test
  public void testKnightMakesAllowedMove() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    logic.moveKnightToPositionIfAllowed(targetPosition);
    assertTrue(logic.isKnightAtPosition(targetPosition));
  }

  @Test
  public void testKnightDoesNotMakeNotAllowedMove() {
    final Pair<Integer, Integer> notAllowedTargetPosition = new Pair<>(
      INITIAL_KNIGHT_POSITION.getX() + 1,
      INITIAL_KNIGHT_POSITION.getY() + 1
    );
    logic.moveKnightToPositionIfAllowed(notAllowedTargetPosition);
    assertFalse(logic.isKnightAtPosition(notAllowedTargetPosition));
  }

  @Test
  public void testKnightHitsPawnWhenMovingToItsPosition() {
    logic.moveKnightToPositionIfAllowed(PAWN_POSITION);
    assertTrue(logic.hasKnightHitPawn());
  }

  @Test
  public void testKnightDoesNotHitPawnIfItMissesIt() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    logic.moveKnightToPositionIfAllowed(targetPosition);
    assertFalse(logic.hasKnightHitPawn());
  }

  @ParameterizedTest
  @CsvSource({
      "-1, 0",
      "0, -1",
      BOARD_SIZE + ", 0",
      "0, " + BOARD_SIZE
  })
  public void testCannotMoveToOutOfBoundPosition(int row, int column) {
    final Pair<Integer, Integer> outOfBoundPosition = new Pair<>(row, column);
    assertThrows(IndexOutOfBoundsException.class, () -> logic.moveKnightToPositionIfAllowed(outOfBoundPosition));
  }

}
