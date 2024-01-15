package pieces;

import game.Colors;
import game.Square;


import static game.Colors.WHITE;


public class Rook extends Piece {

    public Rook(Colors color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.getColor() == WHITE ? "♖" : "♜";
    }

    @Override
    public Square[] getPossibleMovements(Square[][] squares) {

        return this.getPossibleMovementLines(squares, horizontalAndVerticalDirections);

    }

}
