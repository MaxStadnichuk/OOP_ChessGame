package game;

import game.pieces.Bishop;
import game.pieces.Knight;
import game.pieces.Pawn;
import game.pieces.Queen;
import game.pieces.Rook;
import game.pieces.King;
import game.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.Position;

import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.setupInitialPosition();
    }

    @Test
    public void testPossibleMovesForAllPieces() {
        Cell[][] cells = board.getBoard();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                if (!cell.isEmpty()) {
                    Piece piece = cell.getPiece();
                    Position pos = cell.getPosition();
                    List<Move> moves = piece.possibleMoves(board);
                    System.out.println(piece.getClass().getSimpleName()+ "("+piece.getColor()+")" + " at " + pos + " possible moves:");
                    System.out.println(piece.getPosition().getRow()+","+piece.getPosition().getCol());
                    for (Move move : moves) {
                        System.out.println("  " + move);
                    }


                    if (piece instanceof Pawn) {
                        // each pawn has two moves
                        assertEquals("pawn at " + pos + "("+piece.getColor()+") should have 2 moves", 2, moves.size());
                    } else if (piece instanceof Knight) {
                        // knights have two moves
                        assertEquals("knight at " + pos + " should have 2 moves", 2, moves.size());
                    } else if (piece instanceof Bishop || piece instanceof Rook ||
                            piece instanceof Queen || piece instanceof King) {
                        // other pieces are blocked by friendly pawns, so 0 legal moves
                        assertEquals(piece.getClass().getSimpleName() + " at " + pos + " should have 0 moves", 0, moves.size());
                    }
                }
            }
        }
    }
}
