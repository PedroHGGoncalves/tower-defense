package tdgame_4;

import javax.swing.*;
import java.awt.*;

// Tela final do jogo (vitória ou derrota)
public class EndScreen extends JPanel {

    private GameFrame parent; // referência para mudar tela
    private String message;   // mensagem a exibir

    public EndScreen(GameFrame parent, String message) {
        this.parent = parent;
        this.message = message;
        setPreferredSize(new Dimension(Map.COLS * Map.TILE_SIZE, Map.ROWS * Map.TILE_SIZE));
        setLayout(null);

        // Label central com mensagem
        JLabel txt = new JLabel(message, SwingConstants.CENTER);
        txt.setFont(new Font("Arial", Font.BOLD, 40));
        txt.setBounds(0, 80, Map.COLS * Map.TILE_SIZE, 60);
        add(txt);

        // Botão para voltar ao menu
        JButton menu = new JButton("Voltar ao Menu");
        menu.setBounds(220, 220, 160, 36);
        menu.addActionListener(e -> parent.returnToMenu());
        add(menu);

        // Botão para reiniciar o jogo
        JButton restart = new JButton("Reiniciar");
        restart.setBounds(220, 260, 160, 36);
        restart.addActionListener(e -> parent.startGame());
        add(restart);
    }
}


