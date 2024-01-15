import game.Board;

import javax.swing.*;
import java.awt.*;

public class Chess extends JFrame {

    public static void main(String[] args) {
        new Chess();
    }

    public Chess() {
        super("Chess");

        Board board = new Board();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(board);

        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);

    }


}
