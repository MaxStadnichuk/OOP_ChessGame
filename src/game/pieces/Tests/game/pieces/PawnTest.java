package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class PawnTest {
    private Board board;
    private Piece whitePawn;
    private Piece blackPawn;

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

        // white pawn at b2
        whitePawn = new Pawn();
        whitePawn.setColor(Color.WHITE);
        whitePawn.setPosition(new Position(6, 1));
        board.getBoard()[6][1].setPiece(whitePawn);

        // black pawn at b7
        blackPawn = new Pawn();
        blackPawn.setColor(Color.BLACK);
        blackPawn.setPosition(new Position(1, 1));
        board.getBoard()[1][1].setPiece(blackPawn);
    }

    @Test
    public void testWhitePawnPossibleMoves() {
        List<Move> moves = whitePawn.possibleMoves(board);

        assertNotNull(moves);

        // b3 and b4
        assertTrue("can move to b3",
                containsMove(moves, new Position(5, 1)));
        assertTrue("can move to b4",
                containsMove(moves, new Position(4, 1)));
    }

    @Test
    public void testBlackPawnPossibleMoves() {
        List<Move> moves = blackPawn.possibleMoves(board);
        assertNotNull(moves);
        // b6 and b5
        assertTrue("can move to b6",
                containsMove(moves, new Position(2, 1)));
        assertTrue("can move to b5",
                containsMove(moves, new Position(3, 1)));
    }
}
