package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class RookTest{
    private Board board;
    private Piece rook;

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
        rook = new Rook();
        rook.setColor(Color.WHITE);
        rook.setPosition(new Position(3, 3)); // rook at d4
        board.getBoard()[3][3].setPiece(rook);
    }

    @Test
    public void testPossibleMoves() {
        List<Move> moves = rook.possibleMoves(board);
        assertNotNull(moves);


        // up
        assertTrue("from d4 to d5", containsMove(moves, new Position(4, 3)));
        assertTrue("from d4 to d6", containsMove(moves, new Position(5, 3)));
        assertTrue("from d4 to d7", containsMove(moves, new Position(6, 3)));
        assertTrue("from d4 to d8", containsMove(moves, new Position(7, 3)));

        // down
        assertTrue("from d4 to d3", containsMove(moves,new Position(2, 3)));
        assertTrue("from d4 to d2", containsMove(moves,new Position(1, 3)));
        assertTrue("from d4 to d1", containsMove(moves,new Position(0, 3)));

        // right
        assertTrue("from d4 to e4", containsMove(moves, new Position(3, 4)));
        assertTrue("from d4 to f4", containsMove(moves, new Position(3, 5)));
        assertTrue("from d4 to g4", containsMove(moves, new Position(3, 6)));
        assertTrue("from d4 to h4", containsMove(moves, new Position(3, 7)));

        // left
        assertTrue("from d4 to c4", containsMove(moves, new Position(3, 2)));
        assertTrue("from d4 to b4", containsMove(moves, new Position(3, 1)));
        assertTrue("from d4 to a4", containsMove(moves, new Position(3, 0)));
    }

    @Test
    public void testPossibleMovesWithPiece() {
        Pawn pawn = new Pawn();
        pawn.setColor(Color.BLACK);
        pawn.setPosition(new Position(3, 5)); // pawn at d6
        board.getBoard()[3][5].setPiece(pawn);

        List<Move> moves = rook.possibleMoves(board);

        assertNotNull(moves);


        // up
        assertTrue("from d4 to d5", containsMove(moves, new Position(4, 3)));
        assertTrue("from d4 to d6", containsMove(moves, new Position(5, 3)));
        assertTrue("from d4 to d7", containsMove(moves, new Position(6, 3)));
        assertTrue("from d4 to d8", containsMove(moves, new Position(7, 3)));

        // down
        assertTrue("from d4 to d3", containsMove(moves, new Position(2, 3)));
        assertTrue("from d4 to d2", containsMove(moves, new Position(1, 3)));
        assertTrue("from d4 to d1", containsMove(moves, new Position(0, 3)));

        // right
        assertTrue("from d4 to e4", containsMove(moves, new Position(3, 4)));
        assertTrue("from d4 to f4", containsMove(moves, new Position(3, 5)));
        assertFalse("from d4 to g4", containsMove(moves, new Position(3, 6)));
        assertFalse("from d4 to h4", containsMove(moves, new Position(3, 7)));

        // left
        assertTrue("from d4 to c4", containsMove(moves, new Position(3, 2)));
        assertTrue("from d4 to b4", containsMove(moves, new Position(3, 1)));
        assertTrue("from d4 to a4", containsMove(moves, new Position(3, 0)));
    }
}
