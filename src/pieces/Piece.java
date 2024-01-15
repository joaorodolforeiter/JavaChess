package pieces;

import game.Board;
import game.Colors;
import game.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Piece {

    final static protected int[][] diagonalDirections = {
            {+1, +1},
            {-1, -1},
            {+1, -1},
            {-1, +1}
    };

    final static protected int[][] horizontalAndVerticalDirections = {
            {-1, +0},
            {+1, +0},
            {+0, +1},
            {+0, -1}
    };

    protected Square currentSquare;

    private final Colors color;

    public Piece(Colors color) {
        this.color = color;
    }

    public void moveTo(Square square) {
        this.currentSquare = square;
    }

    protected Square[] getPossibleMovementLines(Square[][] squares, int[][] directions) {

        List<Square> possibleMovements = new ArrayList<>();

        for (var direction : directions) {

            for (int i = 1; i < 8; i++) {

                if (!Board.isPositionWithinBounds(
                        currentSquare.x + (i * direction[0]),
                        currentSquare.y + (i * direction[1]))
                ) {
                    break;
                }

                Square square = squares
                        [currentSquare.y + (i * direction[1])]
                        [currentSquare.x + (i * direction[0])];

                var optionalPiece = square.getPiece();

                if (optionalPiece.isEmpty()) {
                    possibleMovements.add(square);
                    continue;
                }

                possibleMovements.add(square);

                break;

            }

        }

        return removeSameColorPieces(possibleMovements.toArray(Square[]::new));

    }

    protected Square[] removeSameColorPieces(Square[] squares) {
        return Arrays
                .stream(squares)
                .filter(
                        square -> {
                            if (square.getPiece().isPresent()) {

                                var piece = square.getPiece().get();
                                return !(piece.getColor() == this.getColor());

                            }
                            return true;
                        }
                )
                .toArray(Square[]::new);
    }

    public Colors getColor() {
        return color;
    }

    @Override
    abstract public String toString();
    abstract public Square[] getPossibleMovements(Square[][] squares);

}
