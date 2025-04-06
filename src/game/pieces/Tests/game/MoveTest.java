package game;

import game.Move;
import game.MoveType;
import game.CASTLING;
import game.PROMOTION;
import game.pieces.King;
import game.pieces.Pawn;
import game.pieces.Piece;
import util.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testNormalMove() {
        Position from = new Position(1, 1);
        Position to = new Position(2, 2);
        Piece piece = new Pawn();
        piece.setPosition(from);
        Move move = new Move(from, to, MoveType.NORMAL, piece);

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
        assertEquals(MoveType.NORMAL, move.getMoveType());
        assertEquals(piece, move.getPiece());

        String expected = from.toString() + "-" + to.toString();
        assertEquals(expected, move.toString());
    }

    @Test
    public void testCaptureMove() {
        Position from = new Position(3, 3);
        Position to = new Position(4, 3);
        Piece piece = new Pawn();
        piece.setPosition(from);
        Move move = new Move(from, to, MoveType.CAPTURE, piece);

        assertEquals(MoveType.CAPTURE, move.getMoveType());
        String expected = from.toString() + "x" + to.toString();
        assertEquals(expected, move.toString());
    }

    @Test
    public void testEnPassantValid() {
        Position from = new Position(4, 4);
        Position to = new Position(5, 5);
        Piece piece = new Pawn();
        piece.setPosition(from);
        Move move = new Move(from, to, MoveType.EN_PASSANT, piece);

        assertEquals(MoveType.EN_PASSANT, move.getMoveType());
        String expected = from.toString() + "x" + to.toString() + " e.p.";
        assertEquals(expected, move.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnPassantInvalidForNonPawn() {
        Position from = new Position(4, 4);
        Position to = new Position(5, 5);
        Piece piece = new King();
        piece.setPosition(from);
        new Move(from, to, MoveType.EN_PASSANT, piece);
    }

    @Test
    public void testCastlingValid() {
        Position from = new Position(7, 4);
        Position to = new Position(7, 6);
        Piece piece = new King();
        piece.setPosition(from);
        Move move = new Move(from, to, MoveType.CASTLING, CASTLING.KINGSIDE, piece);

        assertEquals(MoveType.CASTLING, move.getMoveType());
        assertEquals("O-O", move.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCastlingInvalidForNonKing() {
        Position from = new Position(7, 4);
        Position to = new Position(7, 6);
        Piece piece = new Pawn();
        piece.setPosition(from);
        new Move(from, to, MoveType.CASTLING, CASTLING.KINGSIDE, piece);
    }

    @Test
    public void testPromotionValid() {
        Position from = new Position(6, 0);
        Position to = new Position(7, 0);
        Piece piece = new Pawn();
        piece.setPosition(from);
        Move move = new Move(from, to, MoveType.PROMOTION, PROMOTION.QUEEN, piece);

        assertEquals(MoveType.PROMOTION, move.getMoveType());
        String expected = from.toString() + "-" + to.toString() + "=" + PROMOTION.QUEEN;
        assertEquals(expected, move.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPromotionInvalidForNonPawn() {
        Position from = new Position(6, 0);
        Position to = new Position(7, 0);
        Piece piece = new King();
        piece.setPosition(from);
        new Move(from, to, MoveType.PROMOTION, PROMOTION.QUEEN, piece);
    }

    @Test
    public void testEqualsAndHashCode() {
        Position from = new Position(1, 1);
        Position to = new Position(2, 2);
        Piece piece = new Pawn();
        piece.setPosition(from);
        Move move1 = new Move(from, to, MoveType.NORMAL, piece);
        Move move2 = new Move(from, to, MoveType.NORMAL, piece);

        assertEquals(move1, move2);
        assertEquals(move1.hashCode(), move2.hashCode());
    }
}
