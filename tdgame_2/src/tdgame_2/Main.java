package tdgame_2;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // Janela principal do jogo
        JFrame janela = new JFrame("Tower Defense - Entrega 2");

        // Painel onde o jogo acontece
        Game game = new Game();

        // Configuração básica da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);

        // Integra o jogo à janela
        janela.add(game);
        janela.pack();                    // Ajusta ao tamanho do painel
        janela.setLocationRelativeTo(null); // Centraliza na tela
        janela.setVisible(true);

        // Inicia o loop principal
        game.iniciar();
    }
}


