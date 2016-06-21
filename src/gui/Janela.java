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

    private final Radio radio;
    JCheckBox checkPause;
    JCheckBox checkMudo;
    JCheckBox checkRepeticao;
    Biblioteca.SonsFundo fundo;
    boolean inicioMenu = true;
    boolean menuDeOpcoes = false;
    boolean inicioDeJogo = false;
    private final GroupLayout layout = new GroupLayout(this);

    public Janela() {
        radio = new Radio();
        fundo = Biblioteca.SonsFundo.SCIENCE_IS_FUN;
        acaoMusicaFundo();
        //radio.volumeMusicaFundo(3);

        criaInterface();
        radio.tocar(Biblioteca.SonsCurtos.BOAS_VINDAS, 50);
    }

    private void criaInterface() {

        // Panel para os comandos da musica de fundo
        JPanel painelSonsFundo = new JPanel();
        painelSonsFundo.setOpaque(false);

        JPanel panelInterno = new JPanel();
        panelInterno.setOpaque(false);
        panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.Y_AXIS));

        this.setTitle("Executando audio em Java");
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(panelInterno)
                        .addGap(0, 420, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(400, Short.MAX_VALUE)
                        .addComponent(panelInterno)
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
                if (inicioMenu) {
                    switch (e.getKeyCode()) {
                        case 10:
                            //enter
                            inicioMenu = false;
                            inicioDeJogo = true;
                            radio.tocar(Biblioteca.SonsFundo.NENHUMA_MUSICA, NORMAL, true);
                            radio.tocar(Biblioteca.SonsCurtos.START, 50);
                            break;
                        case 27:
                            //esc
                            System.exit(0);
                        case 32:
                            //back sapace
                            inicioMenu = false;
                            menuDeOpcoes = true;
                            radio.tocar(Biblioteca.SonsCurtos.MENU_DE_OPCOES, 50);
                            break;
                        default:
                            break;
                    }
                }

                //menu de opções
                if (menuDeOpcoes) {
                    if (e.getKeyCode() == 10) {//enter
                        inicioMenu = true;
                        menuDeOpcoes = false;
                        radio.tocar(Biblioteca.SonsFundo.SCIENCE_IS_FUN, 3, true);
                        radio.tocar(Biblioteca.SonsCurtos.BOAS_VINDAS, 50);
                    }

                }

                //inicio de jogo
                if (inicioDeJogo) {
                    switch (e.getKeyCode()) {
                        case 37:
                            //seta esquerda
                            System.out.println("seta para esquerda");
                            break;
                        case 38:
                            //seta cima
                            System.out.println("seta para cima");
                            break;
                        case 39:
                            //seta direita
                            System.out.println("seta para direita");
                            break;
                        case 40:
                            //seta baixo
                            System.out.println("seta para baixo");
                            break;
                        default:
                            break;
                    }
                }

                //radio.volumeMusicaFundo(3);
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
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Execução finalizada.");
                System.exit(0);
            }
        });
    }

    private void acaoMusicaFundo() {
        // troca de musica
        Biblioteca.SonsFundo musicaFundo = (Biblioteca.SonsFundo) fundo;
        radio.tocar(musicaFundo, 3, true);

    }

}
