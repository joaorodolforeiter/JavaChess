package pieces;

import game.Colors;
import game.Square;

import java.util.Arrays;
import java.util.stream.Stream;

import static game.Colors.WHITE;

public class Queen extends Piece {


    public Queen(Colors color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.getColor() == WHITE ? "♕" : "♛";
    }

    @Override
    public Square[] getPossibleMovements(Square[][] squares) {

        return this.getPossibleMovementLines(
                squares,
                Stream
                        .concat(
                                Arrays.stream(horizontalAndVerticalDirections),
                                Arrays.stream(diagonalDirections))
                        .toArray(int[][]::new)
        );

    }

}
