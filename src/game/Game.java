package game;

import game.pieces.Bishop;
import game.pieces.Knight;
import game.pieces.Queen;
import game.pieces.Rook;
import util.Position;

public class Game {

    public void executeMove(Move move, Board board) {
        /**
         * TODO: Implement the turn checking logic
         */
        if (board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()].isEmpty()) {
            throw new IllegalArgumentException("No piece at the given position");
        } else if (!board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()].getPiece().possibleMoves(board).contains(move)) {
            throw new IllegalArgumentException("Invalid move");
        }

        Cell sourceCell = board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()];
        Cell destinationCell = board.getBoard()[move.getTo().getRow()][move.getTo().getCol()];

        if (board.getBoard()[move.getFrom().getRow()][move.getFrom().getCol()].getPiece().equals(move.getPiece())) {
            switch (move.getMoveType()){
                case NORMAL:
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                    break;
                case CAPTURE:
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                    break;
                case EN_PASSANT:
                    destinationCell.setPiece(move.getPiece());
                    sourceCell.setPiece(null);
                    board.getBoard()[move.getTo().getRow() + (move.getPiece().getColor() == Color.WHITE ? -1 : 1)][move.getTo().getCol()].setPiece(null);
                    break;
                case CASTLING:
                    if (move.getCastlingType() == CASTLING.KINGSIDE) {
                        destinationCell.setPiece(move.getPiece());
                        sourceCell.setPiece(null);
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 1].setPiece(board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1].getPiece());
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1].setPiece(null);
                    } else {
                        destinationCell.setPiece(move.getPiece());
                        sourceCell.setPiece(null);
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() + 1].setPiece(board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 2].getPiece());
                        board.getBoard()[move.getTo().getRow()][move.getTo().getCol() - 2].setPiece(null);
                    }
                    break;
                case PROMOTION:
                    switch (move.getPromotionType()) {
                        case QUEEN:
                            destinationCell.setPiece(new Queen(move.getTo(), move.getPiece().getColor()));
                            break;
                        case ROOK:
                            destinationCell.setPiece(new Rook(move.getTo(), move.getPiece().getColor()));
                            break;
                        case BISHOP:
                            destinationCell.setPiece(new Bishop(move.getTo(), move.getPiece().getColor()));
                            break;
                        case KNIGHT:
                            destinationCell.setPiece(new Knight(move.getTo(), move.getPiece().getColor()));
                            break;
                    }
                    break;
            }
        }
    }
}
