package game;

import game.pieces.King;
import game.pieces.Pawn;
import game.pieces.Piece;
import util.Position;

import java.util.Objects;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece piece; // upcasting
    private final MoveType moveType;
    private final CASTLING castlingType;
    private final PROMOTION promotionType;

    // ===== Constructors =====

    public Move(Position from, Position to, MoveType moveType, Piece piece) {
        this(from, to, moveType, null, null, piece);
    }

    public Move(Position from, Position to, MoveType moveType, PROMOTION promotionType, Piece piece) {
        this(from, to, moveType, null, promotionType, piece);
    }

    public Move(Position from, Position to, MoveType moveType, CASTLING castlingType, Piece piece) {
        this(from, to, moveType, castlingType, null, piece);
    }

    private Move(Position from, Position to, MoveType moveType, CASTLING castlingType, PROMOTION promotionType, Piece piece) {
        validateInputs(from, to, moveType, castlingType, promotionType, piece);
        this.from = from;
        this.to = to;
        this.moveType = moveType;
        this.castlingType = castlingType;
        this.promotionType = promotionType;
        this.piece = piece; // upcasting
    }


    private void validateInputs(Position from, Position to, MoveType moveType,
                                CASTLING castlingType, PROMOTION promotionType, Piece piece) {
        if (from == null || to == null || moveType == null || piece == null) {
            throw new IllegalArgumentException("Invalid move: null values provided.");
        }

        switch (moveType) {
            case CASTLING -> {
                if (!(piece instanceof King)) {
                    throw new IllegalArgumentException("only kings can castle");
                }
                if (castlingType == null) {
                    throw new IllegalArgumentException("castling type must be specified");
                }
            }
            case PROMOTION -> {
                //  Downcasting
                if (!(piece instanceof Pawn)) {
                    throw new IllegalArgumentException("only pawns can be promoted");
                }
                if (promotionType == null) {
                    throw new IllegalArgumentException("promotion type must be specified");
                }
            }
            case EN_PASSANT -> {
                if (!(piece instanceof Pawn)) {
                    throw new IllegalArgumentException("only pawns can perform en passant");
                }
            }
            default -> {
            }
        }
    }


    @Override
    public String toString() {
        return switch (moveType) {
            case NORMAL -> from + "-" + to;
            case CAPTURE -> from + "x" + to;
            case EN_PASSANT -> from + "x" + to + " e.p.";
            case CASTLING -> (castlingType == CASTLING.KINGSIDE) ? "O-O" : "O-O-O";
            case PROMOTION -> from + "-" + to + "=" + promotionType;
        };
    }


    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public CASTLING getCastlingType() {
        return castlingType;
    }

    public PROMOTION getPromotionType() {
        return promotionType;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Move other)) return false;
        return Objects.equals(from, other.from)
                && Objects.equals(to, other.to)
                && moveType == other.moveType
                && castlingType == other.castlingType
                && promotionType == other.promotionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, moveType, castlingType, promotionType);
    }
}
