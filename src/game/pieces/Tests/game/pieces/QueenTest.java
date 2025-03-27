package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import org.junit.Before;
import org.junit.Test;
import util.Position;


import java.util.List;

import static org.junit.Assert.*;

public class QueenTest {
    private Board board;
    private Piece queen;

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
        queen = new Queen();
        queen.setColor(Color.WHITE);
        queen.setPosition(new Position(3, 3)); // queen at d4
        board.getBoard()[3][3].setPiece(queen);
    }

    @Test
    public void testPossibleMoves() {
        List<Move> moves = queen.possibleMoves(board);
        assertNotNull(moves);

        Position start = queen.getPosition();

        // up-right
        assertTrue("from d4 to e5", containsMove(moves, new Position(4, 4)));
        assertTrue("from d4 to f6", containsMove(moves, new Position(5, 5)));
        assertTrue("from d4 to g7", containsMove(moves, new Position(6, 6)));
        assertTrue("from d4 to h8", containsMove(moves, new Position(7, 7)));

        // up-left
        assertTrue("from d4 to c5", containsMove(moves, new Position(4, 2)));
        assertTrue("from d4 to b6", containsMove(moves, new Position(5, 1)));
        assertTrue("from d4 to a7", containsMove(moves, new Position(6, 0)));

        // down-right
        assertTrue("from d4 to e3", containsMove(moves, new Position(2, 4)));
        assertTrue("from d4 to f2", containsMove(moves, new Position(1, 5)));
        assertTrue("from d4 to g1", containsMove(moves, new Position(0, 6)));

        // down-left
        assertTrue("from d4 to c3", containsMove(moves, new Position(2, 2)));
        assertTrue("from d4 to b2", containsMove(moves, new Position(1, 1)));
        assertTrue("from d4 to a1", containsMove(moves, new Position(0, 0)));

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
        assertTrue("from d4 to g4", containsMove(moves, new Position(3, 6)));
        assertTrue("from d4 to h4", containsMove(moves, new Position(3, 7)));

        // left
        assertTrue("from d4 to c4", containsMove(moves, new Position(3, 2)));
        assertTrue("from d4 to b4", containsMove(moves, new Position(3, 1)));
        assertTrue("from d4 to a4", containsMove(moves, new Position(3, 0)));
    }
}
