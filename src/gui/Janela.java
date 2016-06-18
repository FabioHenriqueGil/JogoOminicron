/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controls.Biblioteca;
import controls.Radio;
import javax.swing.JCheckBox;

import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.NORMAL;
import java.awt.event.*;

/**
 *
 * @author fabio
 */
public class Janela extends Frame {

    private Radio radio;
    JCheckBox checkPause;
    JCheckBox checkMudo;
    JCheckBox checkRepeticao;
    Biblioteca.SonsFundo fundo;
    boolean inicioMenu = true;
    boolean menuDeOpcoes = false;
    boolean inicioDeJogo = false;
    private GroupLayout layout = new GroupLayout(this);

    public Janela() {
        radio = new Radio();
        fundo = Biblioteca.SonsFundo.SCIENCE_IS_FUN;
        acaoMusicaFundo();

        criaInterface();
        radio.tocar(Biblioteca.SonsCurtos.BOAS_VINDAS, 50);
    }

    private void criaInterface() {

        // Panel para os comandos da musica de fundo
        JPanel painelSonsFundo = new JPanel();
        painelSonsFundo.setOpaque(false);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setOpaque(false);
        panelBotoes.setLayout(new BoxLayout(panelBotoes, BoxLayout.Y_AXIS));

        this.setTitle("Executando audio em Java");
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotoes)
                        .addGap(0, 420, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(400, Short.MAX_VALUE)
                        .addComponent(panelBotoes)
                        .addGap(100, 132, Short.MAX_VALUE))
        );
        // this.getContentPane().setLayout(layout);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("clicou " + e.getKeyCode());
                radio.tocar(Biblioteca.SonsCurtos.BUTTON_POSITIVE, 30);

                //menu inicial
                if (e.getKeyCode() == 10 && inicioMenu) {//enter
                    inicioMenu = false;
                    inicioDeJogo = true;
                    radio.tocar(Biblioteca.SonsFundo.NENHUMA_MUSICA, NORMAL, true);
                    radio.tocar(Biblioteca.SonsCurtos.START, 50);
                } else if (e.getKeyCode() == 27 && inicioMenu) {//esc
                    System.exit(0);
                } else if (e.getKeyCode() == 32 && inicioMenu) {//back sapace
                    inicioMenu = false;
                    menuDeOpcoes = true;
                    radio.tocar(Biblioteca.SonsCurtos.MENU_DE_OPCOES, 50);
                }

                //menu de opções
                if (menuDeOpcoes) {
                    if (e.getKeyCode() == 10) {//enter
                        inicioMenu = true;
                        menuDeOpcoes = false;
                        radio.tocar(Biblioteca.SonsFundo.SCIENCE_IS_FUN, 15, true);
                        radio.tocar(Biblioteca.SonsCurtos.BOAS_VINDAS, 50);
                    }

                }

                //inicio de jogo
                if (inicioDeJogo) {
                    if (e.getKeyCode() == 37) {//seta esquerda
                        System.out.println("seta para esquerda");
                    } else if (e.getKeyCode() == 38) {//seta cima
                        System.out.println("seta para cima");
                    } else if (e.getKeyCode() == 39) {//seta direita
                        System.out.println("seta para direita");
                    } else if (e.getKeyCode() == 40) {//seta baixo
                        System.out.println("seta para baixo");
                    }
                }

            }

//            @Override
//            public void keyReleased(KeyEvent e) {
//                System.out.println("clicou");
//                radio.tocar(Biblioteca.SonsCurtos.BUTTON_POSITIVE, 30);
//            }
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("clicou");
//                radio.tocar(Biblioteca.SonsCurtos.BUTTON_POSITIVE, 30);
//            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Execução finalizada.");
                System.exit(0);
            }
        });
    }

    private void acaoMusicaFundo() {
        // troca de musica
        Biblioteca.SonsFundo musicaFundo = (Biblioteca.SonsFundo) fundo;
        radio.tocar(musicaFundo, 10, true);

    }

}
