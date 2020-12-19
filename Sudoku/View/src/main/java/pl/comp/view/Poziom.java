package pl.comp.view;

import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public enum Poziom {
        LATWY(14),
        SREDNI(27),
        TRUDNY(36);

        private int liczba;

        Poziom(int i) {
            this.liczba = i;
        }

        void start(GridPane board) {
            ObservableList<Node> childrens = board.getChildren();

            int liczbaPowtorzen = 0;

            for (int i = 0; i < liczba; i++) {
                Random rand = new Random();
                int x = rand.nextInt(81);
                    if (((TextField) childrens.get(x)).isEditable()) {
                        i--;
                    } else {
                        ((TextField) childrens.get(x)).setText("");
                        ((TextField) childrens.get(x)).setEditable(true);
                    }
                }
        }

}


