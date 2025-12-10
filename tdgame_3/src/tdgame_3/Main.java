package tdgame_3;

import javax.swing.JFrame;

// Classe de entrada do jogo (ponto inicial da aplicação)
public class Main {

    public static void main(String[] args) {

        // Cria a janela principal do jogo
        JFrame janela = new JFrame("Tower Defense - Entrega 3");

        // Instancia o painel principal do jogo
        Game game = new Game();

        // Configurações básicas da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.add(game);
        janela.pack();                    // ajusta ao tamanho do jogo
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        // Inicia o loop principal do jogo
        game.iniciar();
    }
}



