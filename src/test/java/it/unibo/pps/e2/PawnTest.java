package it.unibo.pps.e2;

import it.unibo.pps.e2.logic.components.pieces.Pawn;
import it.unibo.pps.e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for the pawn piece.
 */
public class PawnTest {

    private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0, 2);

    private Pawn pawn;

    @BeforeEach
    public void initTest() {
        this.pawn = new Pawn(PAWN_POSITION);
    }

    @Test
    public void testPawnPositionIsRetrievedCorrectly() {
        assertEquals(PAWN_POSITION, this.pawn.position());
    }

}
