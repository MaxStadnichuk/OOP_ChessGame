package game.pieces;

import game.Board;
import util.Position;

import java.util.List;

public interface Movable {

    List<String> possibleMoves(Board board);

    boolean checkCell(Board board, Position position);

    boolean checkCellToAttack(Board board, Position position);


}
