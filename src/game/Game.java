package game;

import game.pieces.*;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Game {
    // private static instance — the only one allowed
    private static Game instance;

    private Game() {
    }

    // public static method to access the single instance
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game(); // instance created once (lazy initialization)
        }
        return instance;
    }

    private final List<Move> moveHistory = new ArrayList<>();


    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public void executeMove(Move move, Board board) {
        if (board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()].isEmpty()) {
            throw new IllegalArgumentException("No piece at the given position");
        } else if (!board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()]
                .getPiece().possibleMoves(board).contains(move)) {
            throw new IllegalArgumentException("Invalid move");
        }

        moveHistory.add(move);

        Cell sourceCell = board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()];
        Cell destinationCell = board.getBoard()[move.getTo().getRow()][move.getTo().getCol()];

        if (sourceCell.getPiece().equals(move.getPiece())) {
            switch (move.getMoveType()) {
                case NORMAL, CAPTURE -> {
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                }
                case EN_PASSANT -> {
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                    int capturedRow = move.getTo().getRow() + (move.getPiece().getColor() == Color.WHITE ? -1 : 1);
                    board.getBoard()[capturedRow][move.getTo().getCol()].setPiece(null);
                }
                case CASTLING -> {
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                    if (move.getCastlingType() == CASTLING.KINGSIDE) {
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 1]
                                .setPiece(board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1].getPiece());
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1].setPiece(null);
                    } else {
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1]
                                .setPiece(board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 2].getPiece());
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 2].setPiece(null);
                    }
                }
                case PROMOTION -> {
                    Piece promotedPiece = switch (move.getPromotionType()) {
                        case QUEEN -> new Queen(move.getTo(), move.getPiece().getColor());
                        case ROOK -> new Rook(move.getTo(), move.getPiece().getColor());
                        case BISHOP -> new Bishop(move.getTo(), move.getPiece().getColor());
                        case KNIGHT -> new Knight(move.getTo(), move.getPiece().getColor());
                    };
                    destinationCell.setPiece(promotedPiece);
                    sourceCell.setPiece(null);
                }
            }
            move.getPiece().setPosition(move.getTo());
        }
    }
}
