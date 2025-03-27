package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class KnightTest {
    private Board board;
    private Piece knight;

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
        knight = new Knight();
        knight.setColor(Color.WHITE);
        knight.setPosition(new Position(3, 3)); //knight at d4
        board.getBoard()[3][3].setPiece(knight);
    }

    @Test
    public void testPossibleMoves() {
        List<Move> moves = knight.possibleMoves(board);
        assertNotNull(moves);
        assertEquals(8, moves.size());



        Position start = knight.getPosition();

        assertTrue("from d4 to e6",
                containsMove(moves, new Position(5, 4)));
        assertTrue("d4 to c6",
                containsMove(moves, new Position(5, 2)));
        assertTrue("from d4 to e2",
                containsMove(moves, new Position(1, 4)));
        assertTrue("from d4 to c2",
                containsMove(moves,  new Position(1, 2)));
        assertTrue("from d4 to f5",
                containsMove(moves,  new Position(4, 5)));
        assertTrue("from d4 to b5",
                containsMove(moves,  new Position(4, 1)));
        assertTrue("from d4 to f3",
                containsMove(moves,  new Position(2, 5)));
        assertTrue("from d4 to b3",
                containsMove(moves,  new Position(2, 1)));
    }

    @Test
    public void testPossibleMovesWithPiece() {
        Piece rook = new Rook();
        rook.setColor(Color.WHITE);
        rook.setPosition(new Position(5,4)); //rook at f4
        board.getBoard()[5][4].setPiece(rook);
        List<Move> moves = knight.possibleMoves(board);

        assertNotNull(moves);

        assertFalse("from d4 to e6",
                containsMove(moves,  new Position(5, 4)));
        assertTrue("from d4 to c6",
                containsMove(moves,  new Position(5, 2)));
        assertTrue("from d4 to e2",
                containsMove(moves,  new Position(1, 4)));
        assertTrue("from d4 to c2",
                containsMove(moves,  new Position(1, 2)));
        assertTrue("from d4 to f5",
                containsMove(moves,  new Position(4, 5)));
        assertTrue("from d4 to b5",
                containsMove(moves,  new Position(4, 1)));
        assertTrue("from d4 to f3",
                containsMove(moves,  new Position(2, 5)));
        assertTrue("from d4 to b3",
                containsMove(moves,  new Position(2, 1)));
    }
}
