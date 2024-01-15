package pieces;

import game.Board;
import game.Colors;
import game.Square;

import java.util.ArrayList;
import java.util.List;

import static game.Colors.*;

public class Pawn extends Piece {

    private int timesMoved = -1;

    public Pawn(Colors color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.getColor() == WHITE ? "♙" : "♟";
    }

    @Override
    public void moveTo(Square square) {
        this.currentSquare = square;
        timesMoved++;
    }

    @Override
    public Square[] getPossibleMovements(Square[][] squares) {

        List<Square> possibleMovements = new ArrayList<>();

        final int x = currentSquare.x;
        final int y = currentSquare.y;

        int direction = this.getColor() == WHITE ? -1 : 1;
        var frontSquare = squares[y + direction][x];

        if (frontSquare.getPiece().isEmpty()) {

            possibleMovements.add(frontSquare);

            if (this.timesMoved < 1) {

                var frontFrontSquare = squares[y + (direction * 2)][x];
                if (frontFrontSquare.getPiece().isEmpty()) {
                    possibleMovements.add(frontFrontSquare);
                }

            }

        }

        int[] movementsRelativeToPiece = {1, -1};

        for (var movementRelativeToPiece : movementsRelativeToPiece) {

            if (Board.isPositionWithinBounds(x + movementRelativeToPiece, y)) {

                Square square = squares[y + direction][x + movementRelativeToPiece];
                var optionalPiece = square.getPiece();
                optionalPiece.ifPresent(piece -> possibleMovements.add(square));

            }

        }

        return removeSameColorPieces(possibleMovements.toArray(Square[]::new));

    }

}
