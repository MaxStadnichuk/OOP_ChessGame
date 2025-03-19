package game.pieces;

import game.Board;
import game.pieces.Bishop;
import game.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BishopTest {
    private Board board;
    private Piece bishop;

    @Before
    public void setUp() {
        board = new Board();
        bishop = new Bishop();
        bishop.setPosition(new Position(3, 3)); // Place bishop at d4
        board.getBoard()[3][3].setPiece(bishop);
    }

    @Test
    public void testPossibleMoves() {
        List<String> moves = bishop.possibleMoves(board);
        System.out.println(moves);
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
    }
}