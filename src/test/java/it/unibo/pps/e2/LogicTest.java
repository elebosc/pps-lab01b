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
  private Pair<Integer, Integer> initialPawnPosition;

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
      if (initialPosition.getY() + moveOffsets.getY() < BOARD_SIZE
        && initialPosition.getX() + moveOffsets.getX() < BOARD_SIZE) {
        return moveOffsets;
      }
    }
    throw new IllegalStateException("No allowed moves found");
  }

  @BeforeEach
  public void initTest() {
    this.logic = new LogicsImpl(BOARD_SIZE, SEED);
    this.initialKnightPosition = getKnightPosition();
    this.initialPawnPosition = getPawnPosition();
  }

  @Test
  public void testKnightAndPawnAreInitiallyNotOnTheSameCell() {
    assertNotSame(initialKnightPosition, initialPawnPosition);
  }

  @Test
  public void testKnightMovesAsExpected() {
    final Pair<Integer, Integer> moveOffsets = getFirstAllowedMoveOffsets(initialKnightPosition);
    final Pair<Integer, Integer> targetCell = new Pair<>(
        initialKnightPosition.getX() + moveOffsets.getX(),
        initialKnightPosition.getY() + moveOffsets.getY()
    );
    logic.hit(targetCell.getX(), targetCell.getY());
    assertTrue(logic.hasKnight(targetCell.getX(), targetCell.getY()));
  }

}
