package game.pieces;

import game.Board;
import game.Move;
import util.Position;

import java.util.List;

public interface Movable {

    List<Move> possibleMoves(Board board);

    boolean checkCell(Board board, Position position);

    boolean checkCellToAttack(Board board, Position position);


}
