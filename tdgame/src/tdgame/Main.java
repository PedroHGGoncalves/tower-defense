package tdgame;

import javax.swing.JFrame;

/*
 * Classe de inicialização do jogo.
 * Configura a janela
 * Inicia o loop principal do jogo.
 */
public class Main {

    public static void main(String[] args) {

        // Cria a janela principal 
        JFrame janela = new JFrame("Tower Defense - Entrega 1");

        Game game = new Game();

        // Configuração básica da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.add(game);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        // Inicia o loop de atualização do jogo
        game.iniciar();
    }
}



