package main;

import gui.Janela;
import javax.swing.JFrame;

public class OminicronMain {

    public static void main(String[] args) {
        System.out.println("Executando programa...");
        Janela janela = new Janela();
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }
}
