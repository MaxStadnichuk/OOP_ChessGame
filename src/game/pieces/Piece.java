package game.pieces;

import game.Board;
import game.Color;
import util.Position;

//* TODO: Create a PDF document with pointing on OOP principles and how they are used in the project

//Abstraction
public abstract class Piece implements Movable {
    // encapsulation
    private Color color;
    private Position position;
    private boolean firstMove;



    public Piece(Position position, Color color) {
        this.firstMove = true;
        this.position = position;
        this.color = color;
    }

    public Piece() {

    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean checkCell(Board board, Position position){
        if (position.getRow() < 0 || position.getRow() > 7 || position.getCol() < 0 || position.getCol() > 7) return false;
        return board.getBoard()[position.getRow()][position.getCol()].isEmpty();
    }

    @Override
    public boolean checkCellToAttack(Board board, Position position){
        if (position.getRow() < 0 || position.getRow() > 7 || position.getCol() < 0 || position.getCol() > 7) return false;
        return (!board.getBoard()[position.getRow()][position.getCol()].isEmpty() && board.getBoard()[position.getRow()][position.getCol()].getPiece().getColor() != this.getColor());
    }
}
