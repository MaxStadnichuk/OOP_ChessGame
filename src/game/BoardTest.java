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
    }

    @Test
    public void testSetupInitialPosition() {
        // Test pawns
        for (int col = 0; col < 8; col++) {
            assertInstanceOf(Pawn.class, board.board[1][col].getPiece());
            assertInstanceOf(Pawn.class, board.board[6][col].getPiece());
        }

        // Test rooks
        assertInstanceOf(Rook.class, board.board[0][0].getPiece());
        assertInstanceOf(Rook.class, board.board[0][7].getPiece());
        assertInstanceOf(Rook.class, board.board[7][0].getPiece());
        assertInstanceOf(Rook.class, board.board[7][7].getPiece());

        // Test knights
        assertInstanceOf(Knight.class, board.board[0][1].getPiece());
        assertInstanceOf(Knight.class, board.board[0][6].getPiece());
        assertInstanceOf(Knight.class, board.board[7][1].getPiece());
        assertInstanceOf(Knight.class, board.board[7][6].getPiece());

        // Test bishops
        assertInstanceOf(Bishop.class, board.board[0][2].getPiece());
        assertInstanceOf(Bishop.class, board.board[0][5].getPiece());
        assertInstanceOf(Bishop.class, board.board[7][2].getPiece());
        assertInstanceOf(Bishop.class, board.board[7][5].getPiece());

        // Test queens
        assertInstanceOf(Queen.class, board.board[0][3].getPiece());
        assertInstanceOf(Queen.class, board.board[7][3].getPiece());

        // Test kings
        assertInstanceOf(King.class, board.board[0][4].getPiece());
        assertInstanceOf(King.class, board.board[7][4].getPiece());
    }
}