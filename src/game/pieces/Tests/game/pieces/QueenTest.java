package game.pieces;

import game.Board;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class QueenTest {
    private Board board;
    private Piece queen;

    @Before
    public void setUp() {
        board = new Board();
        queen = new Queen();
        queen.setPosition(new Position(3, 3)); // Place queen at d4
        board.getBoard()[3][3].setPiece(queen);
    }

    @Test
    public void testPossibleMoves() {
        List<String> moves = queen.possibleMoves(board);
        // Check diagonal moves
        assertTrue(moves.contains("e5"));
        assertTrue(moves.contains("f6"));
        assertTrue(moves.contains("g7"));
        assertTrue(moves.contains("h8"));
        assertTrue(moves.contains("c5"));
        assertTrue(moves.contains("b6"));
        assertTrue(moves.contains("a7"));
        assertTrue(moves.contains("e3"));
        assertTrue(moves.contains("f2"));
        assertTrue(moves.contains("g1"));
        assertTrue(moves.contains("c3"));
        assertTrue(moves.contains("b2"));
        assertTrue(moves.contains("a1"));
        // Check horizontal and vertical moves
        assertTrue(moves.contains("d5"));
        assertTrue(moves.contains("d6"));
        assertTrue(moves.contains("d7"));
        assertTrue(moves.contains("d8"));
        assertTrue(moves.contains("d3"));
        assertTrue(moves.contains("d2"));
        assertTrue(moves.contains("d1"));
        assertTrue(moves.contains("e4"));
        assertTrue(moves.contains("f4"));
        assertTrue(moves.contains("g4"));
        assertTrue(moves.contains("h4"));
        assertTrue(moves.contains("c4"));
        assertTrue(moves.contains("b4"));
        assertTrue(moves.contains("a4"));
    }
}