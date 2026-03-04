package it.unibo.pps.e2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    assertTrue(logic.hasKnight(INITIAL_KNIGHT_POSITION.getX(), INITIAL_KNIGHT_POSITION.getY()));
  }

  @Test
  public void testKnightIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(INITIAL_KNIGHT_POSITION.getX() + 1, INITIAL_KNIGHT_POSITION.getY() + 1);
    assertFalse(logic.hasKnight(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testPawnIsDetectedAtItsInitialPosition() {
    assertTrue(logic.hasPawn(PAWN_POSITION.getX(), PAWN_POSITION.getY()));
  }

  @Test
  public void testPawnIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(PAWN_POSITION.getX() + 1, PAWN_POSITION.getY() + 1);
    assertFalse(logic.hasPawn(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testKnightMakesAllowedMove() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    logic.hit(targetPosition.getX(), targetPosition.getY());
    assertTrue(logic.hasKnight(targetPosition.getX(), targetPosition.getY()));
  }

  @Test
  public void testKnightDoesNotMakeNotAllowedMove() {
    final Pair<Integer, Integer> notAllowedTargetPosition = new Pair<>(INITIAL_KNIGHT_POSITION.getX() + 1, INITIAL_KNIGHT_POSITION.getY() + 1);
    logic.hit(notAllowedTargetPosition.getX(), notAllowedTargetPosition.getY());
    assertFalse(logic.hasKnight(notAllowedTargetPosition.getX(), notAllowedTargetPosition.getY()));
  }

  @Test
  public void testKnightHitsPawnWhenMovingToItsPosition() {
    assertTrue(logic.hit(PAWN_POSITION.getX(), PAWN_POSITION.getY()));
  }

  @Test
  public void testKnightDoesNotHitPawnIfItMissesIt() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
    assertFalse(logic.hit(targetPosition.getX(), targetPosition.getY()));
  }

}
