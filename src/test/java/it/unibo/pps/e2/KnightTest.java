package it.unibo.pps.e2;

import it.unibo.pps.e2.logic.components.pieces.Knight;
import it.unibo.pps.e2.logic.components.pieces.KnightImpl;
import it.unibo.pps.e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the knight piece.
 */
public class KnightTest {

    private static final Pair<Integer, Integer> INITIAL_KNIGHT_POSITION = new Pair<>(1, 0);

    private Knight knight;

    @BeforeEach
    public void initTest() {
        this.knight = new KnightImpl(INITIAL_KNIGHT_POSITION);
    }

    @Test
    public void testKnightIsDetectedAtItsInitialPosition() {
        assertEquals(INITIAL_KNIGHT_POSITION, this.knight.getPosition());
    }

    @Test
    public void testKnightMakesAllowedMove() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(3, 1);
        this.knight.moveToPositionIfAllowed(targetPosition);
        assertEquals(targetPosition, this.knight.getPosition());
    }

    @Test
    public void testKnightDoesNotMakeNotAllowedMove() {
        final Pair<Integer, Integer> targetPosition = new Pair<>(
            INITIAL_KNIGHT_POSITION.getX() + 1,
            INITIAL_KNIGHT_POSITION.getY() + 1
        );
        this.knight.moveToPositionIfAllowed(targetPosition);
        assertNotEquals(targetPosition, this.knight.getPosition());
    }

}
