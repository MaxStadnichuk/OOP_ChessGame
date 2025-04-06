package ui;

import game.Board;
import game.Game;
import game.Move;
import game.pieces.Piece;
import util.Position;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class ChessGUI extends Application {

    private Board board;
    private Game game;
    private GridPane boardGrid;
    // Keep track of the selected cell (source)
    private Position selectedPosition;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board();
        board.setupInitialPosition();
        // Singleton
        game = Game.getInstance();

        boardGrid = new GridPane();
        boardGrid.setPadding(new Insets(10));
        boardGrid.setHgap(2);
        boardGrid.setVgap(2);
        boardGrid.setAlignment(Pos.CENTER);

        updateBoardGrid();

        Scene scene = new Scene(boardGrid, 600, 600);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateBoardGrid() {
        boardGrid.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                Button cellButton = new Button();
                cellButton.setMinSize(60, 60);

                String style = ((row + col) % 2 == 0) ? "-fx-background-color: beige;" : "-fx-background-color: gray;";
                cellButton.setStyle(style);

                // Display piece text if there is a piece on the cell
                if (!board.getCell(pos).isEmpty()) {
                    Piece piece = board.getCell(pos).getPiece();
                    cellButton.setText(piece.toString());
                }

                final int r = row, c = col;
                cellButton.setOnAction(e -> handleCellClick(new Position(r, c)));
                boardGrid.add(cellButton, col, row);
            }
        }
    }


    private void handleCellClick(Position pos) {
        if (selectedPosition == null) {
            if (!board.getCell(pos).isEmpty()) {
                selectedPosition = pos;
                System.out.println("Selected cell: " + pos+", Piece: row=" + board.getCell(pos).getPosition().getRow()+" col="+board.getCell(pos).getPosition().getCol());
                Piece piece = board.getCell(pos).getPiece();
                List<Move> possibleMoves = piece.possibleMoves(board);
                System.out.println("Possible moves for piece at " + pos + ":");
                for (Move move : possibleMoves) {
                    System.out.println(move);
                }
            }
        } else {
            Piece movingPiece = board.getCell(selectedPosition).getPiece();
            if (movingPiece != null) {
                List<Move> possibleMoves = movingPiece.possibleMoves(board);
                Move chosenMove = null;
                for (Move m : possibleMoves) {
                    if (m.getTo().equals(pos)) {
                        chosenMove = m;
                        break;
                    }
                }
                if (chosenMove != null) {
                    try {
                        game.executeMove(chosenMove, board);
                        System.out.println("Executed move: " + chosenMove);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid move: " + ex.getMessage());
                    }
                } else {
                    System.out.println("No legal move from " + selectedPosition + " to " + pos);
                }
            }

            selectedPosition = null;
            updateBoardGrid();
        }
    }
}
