package game.pieces;

import game.Board;
import game.Color;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PawnTest {
    private Board board;
    private Piece whitePawn;
    private Piece blackPawn;

    @Before
    public void setUp() {
        board = new Board();
        whitePawn = new Pawn();
        whitePawn.setColor(Color.WHITE);
        whitePawn.setPosition(new Position(1, 1)); // Place white pawn at b2
        board.getBoard()[1][1].setPiece(whitePawn);

        blackPawn = new Pawn();
        blackPawn.setColor(Color.BLACK);
        blackPawn.setPosition(new Position(6, 1)); // Place black pawn at b7
        board.getBoard()[6][1].setPiece(blackPawn);
    }

    @Test
    public void testWhitePawnPossibleMoves() {
        List<String> moves = whitePawn.possibleMoves(board);
        assertTrue(moves.contains("b3"));
        assertTrue(moves.contains("b4"));
    }

    @Test
    public void testBlackPawnPossibleMoves() {
        List<String> moves = blackPawn.possibleMoves(board);
        assertTrue(moves.contains("b6"));
        assertTrue(moves.contains("b5"));
    }
}