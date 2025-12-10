package tdgame_4;

import javax.swing.*;
import java.awt.*;

//Tela inicial do jogo, exibe o texto de lore e o botão START.
public class StartScreen extends JPanel {
    private GameFrame parent; // Referência ao frame principal

    public StartScreen(GameFrame parent) {
        this.parent = parent;
        setPreferredSize(new Dimension(Map.COLS * Map.TILE_SIZE, Map.ROWS * Map.TILE_SIZE)); // Tamanho do painel
        setLayout(null);       // Layout absoluto
        setBackground(Color.BLACK); // Fundo preto

        // === Texto de lore ===
        String texto =
            "<html><div style='text-align: center;'>"
            + "Polygonypse Now.<br>"
            + "<br>"
            + "Os quadrados guardam a fronteira que separa os círculos de todos os polígonos.<br>"
            + "Esses bárbaros sem vértice acreditam que todos delimitados por arestas devem perecer.<br>"
            + "Prepare-se, você foi escolhido para impedir os avanços dos redondos.<br>"
            + "Que Deus abençoe os Quadrados Unidos da América."
            + "</div></html>";

        JLabel lore = new JLabel(texto);
        lore.setForeground(Color.WHITE); // Cor do texto
        lore.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte

        int screenW = Map.COLS * Map.TILE_SIZE; // Largura da tela

        lore.setBounds(
            (screenW - 600) / 2, // Centralizar horizontalmente
            60,
            600,
            120
        );
        add(lore);

        // === Botão Start ===
        JButton start = new JButton("START");
        start.setFont(new Font("Arial", Font.BOLD, 20));
        start.setBounds(
            (screenW - 160) / 2, // Centralizar horizontalmente
            200,
            160,
            40
        );
        start.addActionListener(e -> parent.startGame()); // Inicia o jogo ao clicar
        add(start);
    }
}



