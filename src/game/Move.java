package game;

import util.Position;

import java.util.Objects;


public class Move {
    private Position from;
    private Position to;

    private MoveType moveType;
    private CASTLING castlingType;

    public Move(Position from, Position to, MoveType moveType, CASTLING castlingType) {
        if (!validateMove(from, to, moveType, castlingType)) {
            throw new IllegalArgumentException("Invalid move");
        }
        this.from = from;
        this.to = to;
        this.moveType = moveType;
        this.castlingType = castlingType;
    }

    private boolean validateMove(Position from, Position to, MoveType moveType, CASTLING castlingType) {
        if (from == null || to == null || moveType == null) {
            return false;
        }

        return switch (moveType) {
            case NORMAL, PROMOTION, CAPTURE, EN_PASSANT ->
                    true;
            case CASTLING -> {
                if (castlingType == null) {
                    yield false;
                }
                yield castlingType == CASTLING.KINGSIDE || castlingType == CASTLING.QUEENSIDE;
            }
            default -> false;
        };
    }

    /**
     * refactored toString method which returns the move in a more readable format
     * <br><br>replace "Q" with the piece that the pawn is promoted to
     */
    @Override
    public String toString() {
        switch (moveType) {
            case NORMAL:
                return from + "-" + to;
            case CASTLING:
                if (castlingType == CASTLING.KINGSIDE) {
                    return "O-O";
                } else {
                    return "O-O-O";
                }
            case EN_PASSANT:
                return from + "x" + to + " e.p.";
            case PROMOTION:
                return from + "-" + to + "=" + "Q";
            case CAPTURE:
                return from + "x" + to;
            default:
                return "Unknown move type";
        }
    }


    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public CASTLING getCastlingType() {
        return castlingType;
    }

    public void setCastlingType(CASTLING castlingType) {
        this.castlingType = castlingType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(from, move.from) && Objects.equals(to, move.to) && moveType == move.moveType && castlingType == move.castlingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, moveType, castlingType);
    }
}
