package game;

import game.pieces.Piece;
import util.Position;

public class Cell {
    private Position position;
    private Piece piece;
    private Color color;

    Cell(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }
    public Cell(Position position) {
        this.position = position;
        this.piece = null;
    }
    Cell(){}

    public void clear() {
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    @Override
    public String toString() {
        return "Cell{" + "position=" + position + ", piece=" + piece + '}';
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
}
