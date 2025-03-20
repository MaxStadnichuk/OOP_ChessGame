package game.pieces;

import game.Board;
import game.Color;
import game.pieces.King;
import game.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {
    private Board board;
    private Piece king;

    @Before
    public void setUp() {
        board = new Board();
        king = new King();
        king.setColor(Color.WHITE);
        king.setPosition(new Position(3, 3)); // Place king at d4
        board.getBoard()[3][3].setPiece(king);
    }

    @Test
    public void testPossibleMoves() {
        List<String> moves = king.possibleMoves(board);
        // Check all possible moves for the king
        assertTrue(moves.contains("d5"));
        assertTrue(moves.contains("d3"));
        assertTrue(moves.contains("c4"));
        assertTrue(moves.contains("e4"));
        assertTrue(moves.contains("c5"));
        assertTrue(moves.contains("e5"));
        assertTrue(moves.contains("c3"));
        assertTrue(moves.contains("e3"));
    }

    @Test
    public void testPossibleMovesInCheck() {
        // Place an enemy piece that puts the king in check
        Piece enemyRook = new Rook();
        enemyRook.setColor(Color.BLACK);
        enemyRook.setPosition(new Position(3, 5)); // Place enemy rook at f4
        board.getBoard()[3][5].setPiece(enemyRook);

        List<String> moves = king.possibleMoves(board);
        // Check that the king cannot move to cells that are in check
        assertFalse(moves.contains("d4")); // Current position
        assertFalse(moves.contains("b4")); // In check by rook
        assertFalse(moves.contains("e4")); // In check by rook
        assertTrue(moves.contains("c5"));
        assertTrue(moves.contains("d5"));
        assertTrue(moves.contains("e5"));
        assertTrue(moves.contains("c3"));
        assertTrue(moves.contains("d3"));
        assertTrue(moves.contains("e3"));
    }

    @Test
    public void testPossibleMovesWithCellsUnderAttack() {
        // Place an enemy piece that puts the king in check
        Piece enemyRook = new Rook();
        Piece enemyRook2 = new Rook();
        enemyRook.setColor(Color.BLACK);
        enemyRook2.setColor(Color.BLACK);
        enemyRook.setPosition(new Position(3, 5)); // Place enemy rook at f4
        enemyRook2.setPosition(new Position(4, 5)); // Place enemy rook at f4
        board.getBoard()[3][5].setPiece(enemyRook);
        board.getBoard()[4][5].setPiece(enemyRook2);

        List<String> moves = king.possibleMoves(board);
        System.out.println(moves);
        // Check that the king cannot move to cells that are in check
        assertFalse(moves.contains("d4")); // Current position
        assertFalse(moves.contains("b4")); // In check by rook
        assertFalse(moves.contains("e4")); // In check by rook
        assertFalse(moves.contains("c5"));
        assertFalse(moves.contains("d5"));
        assertFalse(moves.contains("e5"));
        assertTrue(moves.contains("c3"));
        assertTrue(moves.contains("d3"));
        assertTrue(moves.contains("e3"));
    }
}