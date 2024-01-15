package game;

import pieces.Piece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;

public class Square extends JButton {

    final public int x;
    final public int y;

    private Piece piece;
    private boolean isHighlighted = false;

    public Square(Colors color, int x, int y) {

        this.x = x;
        this.y = y;

        switch (color) {
            case BLACK -> this.setBackground(Color.LIGHT_GRAY);
            case WHITE -> this.setBackground(Color.WHITE);
        }

        this.setFont(new Font(this.getFont().getFamily(), Font.BOLD, 24));

    }

    public void addPiece(Piece piece) {

        this.piece = piece;
        this.piece.moveTo(this);
        this.setText(this.toString());

    }

    public void moveCurrentPieceTo(Square square) {

        this.piece.moveTo(square);
        square.addPiece(this.piece);
        removePiece();

    }

    public void removePiece() {

        this.piece = null;
        this.setText(this.toString());

    }

    public void setHighlighted(boolean value) {

        if (value) {

            this.isHighlighted = true;

            if (piece != null) {
                this.setBorder(new LineBorder(Color.RED, 2));
            } else {
                this.setBorder(new LineBorder(Color.ORANGE, 2));
            }

        } else {

            this.isHighlighted = false;
            this.setBorder(new JButton().getBorder());

        }

    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public Optional<Piece> getPiece() {
        return Optional.ofNullable(this.piece);
    }

    @Override
    public String toString() {

        if (piece == null) {
            return "";
        }

        return piece.toString();

    }

}
