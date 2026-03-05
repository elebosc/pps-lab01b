package it.unibo.pps.e2;
import it.unibo.pps.e2.logics.Logics;
import it.unibo.pps.e2.logics.LogicsImpl;
import it.unibo.pps.e2.logics.components.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int BOARD_SIZE = 5;
  private static final Pair<Integer, Integer> INITIAL_KNIGHT_POSITION = new Pair<>(1, 0);
  private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0, 2);

  private Logics logic;

  @BeforeEach
  public void initTest() {
    this.logic = new LogicsImpl(BOARD_SIZE, INITIAL_KNIGHT_POSITION, PAWN_POSITION);
  }

  @Test
  public void testKnightIsDetectedAtItsInitialPosition() {
    assertTrue(logic.isThereKnightAtPosition(INITIAL_KNIGHT_POSITION));
  }

  @Test
  public void testKnightIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(INITIAL_KNIGHT_POSITION.getX() + 1, INITIAL_KNIGHT_POSITION.getY() + 1);
    assertFalse(logic.isThereKnightAtPosition(wrongPosition));
  }

  @Test
  public void testPawnIsDetectedAtItsInitialPosition() {
    assertTrue(logic.isTherePawnAtPosition(PAWN_POSITION));
  }

  @Test
  public void testPawnIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(PAWN_POSITION.getX() + 1, PAWN_POSITION.getY() + 1);
    assertFalse(logic.isTherePawnAtPosition(wrongPosition));
  }

  @Test
  public void testKnightMakesAllowedMove() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    logic.moveKnightToPosition(targetPosition);
    assertTrue(logic.isThereKnightAtPosition(targetPosition));
  }

  @Test
  public void testKnightDoesNotMakeNotAllowedMove() {
    final Pair<Integer, Integer> notAllowedTargetPosition = new Pair<>(INITIAL_KNIGHT_POSITION.getX() + 1, INITIAL_KNIGHT_POSITION.getY() + 1);
    logic.moveKnightToPosition(notAllowedTargetPosition);
    assertFalse(logic.isThereKnightAtPosition(notAllowedTargetPosition));
  }

  @Test
  public void testKnightHitsPawnWhenMovingToItsPosition() {
    assertTrue(logic.moveKnightToPosition(PAWN_POSITION));
  }

  @Test
  public void testKnightDoesNotHitPawnIfItMissesIt() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    assertFalse(logic.moveKnightToPosition(targetPosition));
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
    assertThrows(IndexOutOfBoundsException.class, () -> logic.moveKnightToPosition(outOfBoundPosition));
  }

}
