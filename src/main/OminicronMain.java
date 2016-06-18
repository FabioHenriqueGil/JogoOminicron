package main;

import gui.Janela;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class OminicronMain {

    public static void main(String[] args) {
        System.out.println("Executando programa...");
        Janela janela = new Janela();
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }
}
