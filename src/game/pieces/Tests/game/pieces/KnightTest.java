package game.pieces;

import game.Board;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class KnightTest {
    private Board board;
    private Piece knight;

    @Before
    public void setUp() {
        board = new Board();
        knight = new Knight(); // створюємо коня (за потреби можна вказати колір через конструктор)
        knight.setPosition(new Position(3, 3)); // розміщуємо коня у позиції (3,3) - d4
        board.getBoard()[3][3].setPiece(knight);
    }

    @Test
    public void testPossibleMoves() {
        List<String> moves = knight.possibleMoves(board);
        assertNotNull(moves);
        assertEquals(8, moves.size());

        assertTrue(moves.contains("e6"));
        assertTrue(moves.contains("c6"));
        assertTrue(moves.contains("e2"));
        assertTrue(moves.contains("c2"));
        assertTrue(moves.contains("f5"));
        assertTrue(moves.contains("b5"));
        assertTrue(moves.contains("f3"));
        assertTrue(moves.contains("b3"));
    }
}
