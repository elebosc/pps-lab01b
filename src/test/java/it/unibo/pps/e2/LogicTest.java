package it.unibo.pps.e2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int BOARD_SIZE = 5;
  private static final int SEED = 0;
  private static final List<Pair<Integer, Integer>> ALLOWED_KNIGHT_MOVES_OFFSETS = List.of(
      new Pair<>(-2, -1),
      new Pair<>(-1, 2),
      new Pair<>(2, 1),
      new Pair<>(1, -2)
  );

  private Logics logic;
  private Pair<Integer, Integer> initialKnightPosition;
  private Pair<Integer, Integer> pawnPosition;

  private Pair<Integer, Integer> getKnightPosition() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (logic.hasKnight(i, j)) {
          return new Pair<>(i, j);
        }
      }
    }
    throw new IllegalStateException("Knight not found.");
  }

  private Pair<Integer, Integer> getPawnPosition() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (logic.hasPawn(i, j)) {
          return new Pair<>(i, j);
        }
      }
    }
    throw new IllegalStateException("Pawn not found.");
  }

  private Pair<Integer, Integer> getFirstAllowedMoveOffsets(Pair<Integer, Integer> initialPosition) {
    for (final Pair<Integer, Integer> moveOffsets : ALLOWED_KNIGHT_MOVES_OFFSETS) {
      if (
        initialPosition.getX() + moveOffsets.getX() >= 0
        && initialPosition.getX() + moveOffsets.getX() < BOARD_SIZE
        && initialPosition.getY() + moveOffsets.getY() >= 0
        && initialPosition.getY() + moveOffsets.getY() < BOARD_SIZE) {
        return moveOffsets;
      }
    }
    throw new IllegalStateException("No allowed moves found");
  }

  private int getNotAllowedMoveOffset(int position) {
    return (position == BOARD_SIZE - 1) ? -1 : 1;
  }

  private Pair<Integer, Integer> getTargetPosition(Pair<Integer, Integer> initialPosition, Pair<Integer, Integer> moveOffsets) {
    return new Pair<>(
    initialPosition.getX() + moveOffsets.getX(),
    initialPosition.getY() + moveOffsets.getY()
    );
  }

  private Pair<Integer, Integer> makeFirstValidAllowedMove(Pair<Integer, Integer> initialPosition) {
    final Pair<Integer, Integer> moveOffsets = getFirstAllowedMoveOffsets(initialPosition);
    final Pair<Integer, Integer> targetPosition = new Pair<>(
        initialPosition.getX() + moveOffsets.getX(),
        initialPosition.getY() + moveOffsets.getY()
    );
    logic.hit(targetPosition.getX(), targetPosition.getY());
    return targetPosition;
  }

  private Pair<Integer, Integer> attemptNotAllowedMove(Pair<Integer, Integer> initialPosition) {
    final Pair<Integer, Integer> notAllowedMoveOffset = new Pair<>(
        getNotAllowedMoveOffset(initialPosition.getX()),
        getNotAllowedMoveOffset(initialPosition.getY())
    );
    final Pair<Integer, Integer> targetPosition = getTargetPosition(initialPosition, notAllowedMoveOffset);
    logic.hit(targetPosition.getX(), targetPosition.getY());
    return targetPosition;
  }

  private void makeMoves(List<Pair<Integer, Integer>> targets) {
    targets.forEach((target) -> logic.hit(target.getX(), target.getY()));
  }

  @BeforeEach
  public void initTest() {
    this.logic = new LogicsImpl(BOARD_SIZE, SEED);
    this.initialKnightPosition = getKnightPosition();
    this.pawnPosition = getPawnPosition();
  }

  @Test
  public void testKnightAndPawnAreInitiallyNotAtTheSamePosition() {
    assertNotSame(initialKnightPosition, pawnPosition);
  }

  @Test
  public void testKnightIsDetectedAtItsInitialPosition() {
    assertTrue(logic.hasKnight(initialKnightPosition.getX(), initialKnightPosition.getY()));
  }

  @Test
  public void testKnightIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(
      initialKnightPosition.getX() + 1,
      initialKnightPosition.getY() + 1
    );
    assertFalse(logic.hasKnight(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testPawnIsDetectedAtItsInitialPosition() {
    assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY()));
  }

  @Test
  public void testPawnIsNotDetectedOutsideItsPosition() {
    final Pair<Integer, Integer> wrongPosition = new Pair<>(
      pawnPosition.getX() + 1,
      pawnPosition.getY() + 1
    );
    assertFalse(logic.hasPawn(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testKnightMakesAllowedMove() {
    final Pair<Integer, Integer> targetPosition = makeFirstValidAllowedMove(initialKnightPosition);
    assertTrue(logic.hasKnight(targetPosition.getX(), targetPosition.getY()));
  }

  @Test
  public void testKnightMoveIsNotMadeIfNotAllowed() {
    final Pair<Integer, Integer> targetPosition = attemptNotAllowedMove(initialKnightPosition);
    assertFalse(logic.hasKnight(targetPosition.getX(), targetPosition.getY()));
  }

  @Test
  public void testKnightHitsPawnWhenMovingToItsPosition() {
    makeMoves(List.of(
        new Pair<>(3, 4),
        new Pair<>(2, 2)
    ));
    assertTrue(logic.hit(pawnPosition.getX(), pawnPosition.getY()));
  }

  @Test
  public void testKnightDoesNotHitPawnIfItMissesIt() {
    final Pair<Integer, Integer> targetPosition = new Pair<>(3, 4);
    assertFalse(logic.hit(targetPosition.getX(), targetPosition.getY()));
  }

}
