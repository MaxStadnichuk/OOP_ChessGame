package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.pieces.Bishop;
import game.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class BishopTest {
    private Board board;
    private Piece bishop;

    public boolean containsMove(List<Move> moves, Position to) {
        for (Move m : moves) {
            if (m.getTo().equals(to)) {
                return true;
            }
        }
        return false;
    }

    @Before
    public void setUp() {
        board = new Board();
        bishop = new Bishop();
        bishop.setColor(Color.WHITE);
        bishop.setPosition(new Position(3, 3)); //bishop at d4
        board.getBoard()[3][3].setPiece(bishop);
    }

    @Test
    public void testPossibleMoves() {
        List<Move> moves = bishop.possibleMoves(board);

        assertNotNull(moves);

        // Up-right
        assertTrue("from d4 to e5", containsMove(moves, new Position(4, 4)));
        assertTrue("from d4 to f6", containsMove(moves, new Position(5, 5)));
        assertTrue("from d4 to g7", containsMove(moves, new Position(6, 6)));
        assertTrue("from d4 to h8", containsMove(moves, new Position(7, 7)));

        // Up-left
        assertTrue("from d4 to c5", containsMove(moves,  new Position(4, 2)));
        assertTrue("from d4 to b6", containsMove(moves,  new Position(5, 1)));
        assertTrue("from d4 to a7", containsMove(moves,  new Position(6, 0)));

        // Down-right
        assertTrue("from d4 to e3", containsMove(moves,  new Position(2, 4)));
        assertTrue("from d4 to f2", containsMove(moves,  new Position(1, 5)));
        assertTrue("from d4 to g1", containsMove(moves,  new Position(0, 6)));

        // Down-left
        assertTrue("from d4 to c3", containsMove(moves,  new Position(2, 2)));
        assertTrue("from d4 to b2", containsMove(moves,  new Position(1, 1)));
        assertTrue("from d4 to a1", containsMove(moves,  new Position(0, 0)));
    }
}
