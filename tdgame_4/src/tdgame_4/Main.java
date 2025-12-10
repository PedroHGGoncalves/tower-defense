package tdgame_4;

import javax.swing.*;

//Ponto de entrada da aplicação; inicializa o GameFrame na Event Dispatch Thread.
public class Main {
    public static void main(String[] args) {
    	// Inicializa o jogo na Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }
}

