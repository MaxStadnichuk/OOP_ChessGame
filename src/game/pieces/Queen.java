package game.pieces;

import game.Board;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{


    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();

        Position positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol());
        boolean rightUp = true;
        boolean rightDown = true;
        boolean leftUp = true;
        boolean leftDown = true;
        // Check possible moves for bishop
        for (int i = 1; i < 8; i++) {
            //check right up
            positionToMove.setRow(this.getPosition().getRow() + i);
            positionToMove.setCol(this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightUp) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    rightUp = false;
                }
            }
            //check left up
            positionToMove.setRow(this.getPosition().getRow() + i);
            positionToMove.setCol(this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftUp) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    leftUp = false;
                }

            }
            //check right down
            positionToMove.setRow(this.getPosition().getRow() - i);
            positionToMove.setCol(this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightDown) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    rightDown = false;
                }
            }
            //check left down
            positionToMove.setRow(this.getPosition().getRow() - i);
            positionToMove.setCol(this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftDown) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    leftDown = false;
                }
            }
        }

        Position currentPosition = this.getPosition();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Check possible moves to the right
        for (int col = currentCol + 1; col < 8; col++) {
            positionToMove = new Position(currentRow, col);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves to the left
        for (int col = currentCol - 1; col >= 0; col--) {
            positionToMove = new Position(currentRow, col);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves up
        for (int row = currentRow + 1; row < 8; row++) {
            positionToMove = new Position(row, currentCol);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves down
        for (int row = currentRow - 1; row >= 0; row--) {
            positionToMove = new Position(row, currentCol);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }
}
