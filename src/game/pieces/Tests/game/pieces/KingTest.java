package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class KingTest {
    private Board board;
    private Piece king;

    @Before
    public void setUp() {
        board = new Board();
        king = new King();
        king.setColor(Color.WHITE);
        king.setPosition(new Position(3, 3)); // king at d4
        board.getBoard()[3][3].setPiece(king);
    }

    // returns true if moves contains a move with the given position
    private boolean containsMove(List<Move> moves, Position to) {
        for (Move m : moves) {
            if (m.getTo().equals(to)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testPossibleMoves() {
        List<Move> moves = king.possibleMoves(board);
        assertNotNull( moves);
        assertEquals( 8, moves.size());

        assertTrue("from d4 to d5", containsMove(moves, new Position(4, 3)));
        assertTrue("from d4 to d3", containsMove(moves, new Position(2, 3)));
        assertTrue("from d4 to c4", containsMove(moves, new Position(3, 2)));
        assertTrue("from d4 to e4", containsMove(moves, new Position(3, 4)));
        assertTrue("from d4 to c5", containsMove(moves, new Position(4, 2)));
        assertTrue("from d4 to e5", containsMove(moves, new Position(4, 4)));
        assertTrue("from d4 to c3", containsMove(moves, new Position(2, 2)));
        assertTrue("from d4 to e3", containsMove(moves, new Position(2, 4)));
    }

    @Test
    public void testPossibleMovesInCheck() {
        // enemy rook that puts some king moves under attack
        Piece enemyRook = new Rook();
        enemyRook.setColor(Color.BLACK);
        enemyRook.setPosition(new Position(3, 5)); //rook at f4
        board.getBoard()[3][5].setPiece(enemyRook);

        List<Move> moves = king.possibleMoves(board);

        assertNotNull(moves);

        assertFalse(containsMove(moves, king.getPosition()));
        assertFalse("cant move to e4", containsMove(moves, new Position(3, 4)));
        assertTrue("can move to d5", containsMove(moves, new Position(4, 3)));
        assertTrue("can move to d3", containsMove(moves, new Position(2, 3)));
        assertFalse("cant move to c4", containsMove(moves, new Position(3, 2)));
        assertFalse("cant move to e4", containsMove(moves, new Position(3, 4)));
    }

    @Test
    public void testPossibleMovesWithCellsUnderAttack() {
        //two enemy rooks to restrict moves
        Piece enemyRook1 = new Rook();
        Piece enemyRook2 = new Rook();
        enemyRook1.setColor(Color.BLACK);
        enemyRook2.setColor(Color.BLACK);
        enemyRook1.setPosition(new Position(3, 5)); // f4
        enemyRook2.setPosition(new Position(4, 5)); //f5
        board.getBoard()[3][5].setPiece(enemyRook1);
        board.getBoard()[4][5].setPiece(enemyRook2);

        List<Move> moves = king.possibleMoves(board);

        assertNotNull("Moves list should not be null", moves);

        //moves to e4 (3,4) d5 (4,3) and e5 (4,4) cannot be made
        assertFalse(containsMove(moves, new Position(3, 4)));
        assertFalse(containsMove(moves, new Position(4, 3)));
        assertFalse(containsMove(moves, new Position(4, 4)));

        assertTrue("allowed move to c3", containsMove(moves, new Position(2, 2)));
        assertTrue("allowed move to d3", containsMove(moves, new Position(2, 3)));
        assertTrue("allowed move to e3", containsMove(moves, new Position(2, 4)));
    }
}
