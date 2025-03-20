package game.pieces;

import game.Board;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RookTest {
    private Board board;
    private Piece rook;

    @Before
    public void setUp() {
        board = new Board();
        rook = new Rook();
        rook.setPosition(new Position(3, 3)); // Place rook at d4
        board.getBoard()[3][3].setPiece(rook);
    }

    @Test
    public void testPossibleMoves() {
        List<String> moves = rook.possibleMoves(board);
        System.out.println(moves);
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