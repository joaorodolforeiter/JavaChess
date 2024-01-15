package pieces;

import game.Board;
import game.Colors;
import game.Square;

import java.util.ArrayList;
import java.util.List;

import static game.Colors.WHITE;

public class King extends Piece {

    public King(Colors color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.getColor() == WHITE ? "♔" : "♚";
    }

    @Override
    public Square[] getPossibleMovements(Square[][] squares) {

        List<Square> possibleMovements = new ArrayList<>();

        final int x = currentSquare.x;
        final int y = currentSquare.y;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (!Board.isPositionWithinBounds(y + j, x + i)) {
                    continue;
                }

                Square square = squares[y + j][x + i];

                possibleMovements.add(square);

            }

        }

        return removeSameColorPieces(possibleMovements.toArray(Square[]::new));

    }

}
