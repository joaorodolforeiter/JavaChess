package game;

import pieces.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static game.Colors.BLACK;
import static game.Colors.WHITE;


public class Board extends JPanel {

    private final Square[][] squares = new Square[8][8];
    private Square currentlySelectedSquare;
    boolean isWhitesTurn = true;

    public Board() {

        this.setBorder(new LineBorder(Color.DARK_GRAY, 8));
        this.setLayout(new GridLayout(8, 8));

        initSquares();
        initDefaultPieces();

    }

    private void handleSquareClick(Square clickedSquare) {

        if (currentlySelectedSquare != null) {

            if (clickedSquare.isHighlighted()) {

                currentlySelectedSquare.moveCurrentPieceTo(clickedSquare);
                this.isWhitesTurn = !this.isWhitesTurn;

            }

            this.currentlySelectedSquare = null;
            this.resetHighlight();

        }

        if (currentlySelectedSquare == null) {

            var optionalPiece = clickedSquare.getPiece();
            if (optionalPiece.isPresent()) {

                var piece = optionalPiece.get();

                if ((piece.getColor() == BLACK && !isWhitesTurn) || (piece.getColor() == WHITE && isWhitesTurn)) {

                    this.currentlySelectedSquare = clickedSquare;
                    var possibleMovements = piece.getPossibleMovements(this.squares);

                    if (possibleMovements.length != 0) {

                        this.highlightSquares(piece.getPossibleMovements(this.squares));

                    } else {

                        this.currentlySelectedSquare = null;

                    }

                }

            }

            if (optionalPiece.isEmpty()) {
                this.resetHighlight();
            }

        }

    }

    private void initSquares() {

        for (int y = 0; y < squares.length; y++) {

            var squareLine = squares[y];
            for (int x = 0; x < squareLine.length; x++) {

                Square square = new Square((x + y) % 2 != 0 ? BLACK : WHITE, x, y);

                squareLine[x] = square;
                square.addActionListener(e -> handleSquareClick(square));
                this.add(square);

            }

        }

    }

    private void resetHighlight() {

        for (var squareLine : this.squares) {
            for (var square : squareLine) {
                square.setHighlighted(false);
            }
        }

    }

    private void highlightSquares(Square[] squares) {

        this.resetHighlight();

        for (var square : squares) {
            square.setHighlighted(true);
        }

    }

    private void initDefaultPieces() {

        var blackLine = this.squares[0];
        var whiteLine = this.squares[7];

        addDefaultPiecesToLine(blackLine, BLACK);
        addDefaultPiecesToLine(whiteLine, WHITE);

        for (var square : this.squares[1]) {
            square.addPiece(new Pawn(BLACK));
        }

        for (var square : this.squares[6]) {
            square.addPiece(new Pawn(WHITE));
        }

    }

    private void addDefaultPiecesToLine(Square[] line, Colors color) {

        line[0].addPiece(new Rook(color));
        line[1].addPiece(new Knight(color));
        line[2].addPiece(new Bishop(color));
        line[3].addPiece(new Queen(color));
        line[4].addPiece(new King(color));
        line[5].addPiece(new Bishop(color));
        line[6].addPiece(new Knight(color));
        line[7].addPiece(new Rook(color));

    }

    public static boolean isPositionWithinBounds(int x, int y) {
        return !(x >= 8 || y >= 8 || x < 0 || y < 0);
    }

}