package pieces;

import game.Board;
import game.Colors;
import game.Square;

import java.util.ArrayList;
import java.util.List;

import static game.Colors.WHITE;

public class Knight extends Piece {

    public Knight(Colors color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.getColor() == WHITE ? "♘" : "♞";
    }

    @Override
    public Square[] getPossibleMovements(Square[][] squares) {

        List<Square> possibleMovements = new ArrayList<>();

        final int x = currentSquare.x;
        final int y = currentSquare.y;

        int[][] positions = {{+2, +1}, {+2, -1}, {-2, +1}, {-2, -1}, {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}};

        for (var position : positions) {
            if (Board.isPositionWithinBounds(y + position[0], x + position[1])) {
                Square square = squares[y + position[0]][x + position[1]];

                square.getPiece().ifPresentOrElse(piece -> {
                    if (piece.getColor() != this.getColor()) {
                        possibleMovements.add(square);
                    }
                }, () -> possibleMovements.add(square));

            }
        }

        return possibleMovements.toArray(Square[]::new);

    }
}
