package game;

import static org.junit.jupiter.api.Assertions.*;

import game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.pieces.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.setupInitialPosition();
        board.setupInitialColor();
        board.setupInitialPieceColor();
    }

    @Test
    public void testSetupInitialPosition() {
        // Test pawns
        for (int col = 0; col < 8; col++) {
            assertInstanceOf(Pawn.class, board.getBoard()[1][col].getPiece());
            assertInstanceOf(Pawn.class, board.getBoard()[6][col].getPiece());
        }

        // Test rooks
        assertInstanceOf(Rook.class, board.getBoard()[0][0].getPiece());
        assertInstanceOf(Rook.class, board.getBoard()[0][7].getPiece());
        assertInstanceOf(Rook.class, board.getBoard()[7][0].getPiece());
        assertInstanceOf(Rook.class, board.getBoard()[7][7].getPiece());

        // Test knights
        assertInstanceOf(Knight.class, board.getBoard()[0][1].getPiece());
        assertInstanceOf(Knight.class, board.getBoard()[0][6].getPiece());
        assertInstanceOf(Knight.class, board.getBoard()[7][1].getPiece());
        assertInstanceOf(Knight.class, board.getBoard()[7][6].getPiece());

        // Test bishops
        assertInstanceOf(Bishop.class, board.getBoard()[0][2].getPiece());
        assertInstanceOf(Bishop.class, board.getBoard()[0][5].getPiece());
        assertInstanceOf(Bishop.class, board.getBoard()[7][2].getPiece());
        assertInstanceOf(Bishop.class, board.getBoard()[7][5].getPiece());

        // Test queens
        assertInstanceOf(Queen.class, board.getBoard()[0][3].getPiece());
        assertInstanceOf(Queen.class, board.getBoard()[7][3].getPiece());

        // Test kings
        assertInstanceOf(King.class, board.getBoard()[0][4].getPiece());
        assertInstanceOf(King.class, board.getBoard()[7][4].getPiece());
    }

    @Test
    public void testSetupInitialColor() {
        // Test color of the board
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    assertEquals(Color.WHITE, board.getBoard()[i][j].getColor());
                } else {
                    assertEquals(Color.BLACK, board.getBoard()[i][j].getColor());
                }
            }
        }
    }

    @Test
    public void testSetupInitialPieceColor() {
        // Test color of the pieces
        for (int i = 0; i < board.getBoard().length; i++) {
            if (i == 0 || i == 1) {
                for (int j = 0; j < board.getBoard()[i].length; j++) {
                    assertEquals(Color.WHITE, board.getBoard()[i][j].getPiece().getColor());
                }
            } else if (i == 6 || i == 7) {
                for (int j = 0; j < board.getBoard()[i].length; j++) {
                    assertEquals(Color.BLACK, board.getBoard()[i][j].getPiece().getColor());
                }
            }
        }
    }
}