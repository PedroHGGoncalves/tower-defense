package tdgame_4;

import javax.swing.*;
import java.awt.*;

// Janela principal do jogo, gerencia telas (menu, jogo, fim)
public class GameFrame extends JFrame {

    private CardLayout cards;          // gerencia múltiplas telas
    private JPanel root;               // painel raiz com CardLayout
    private StartScreen startScreen;   
    private GamePanel gamePanel;       
    private EndScreen gameOverScreen;  
    private EndScreen victoryScreen;   
  

    public GameFrame() {
        super("Tower Defense - Entrega 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);



        cards = new CardLayout();
        root = new JPanel(cards);

        // cria telas
        startScreen = new StartScreen(this);
        gamePanel = new GamePanel(this);
        gameOverScreen = new EndScreen(this, "Game Over");
        victoryScreen = new EndScreen(this, "Vitória!");

        // adiciona telas ao CardLayout
        root.add(startScreen, "START");
        root.add(gamePanel, "GAME");
        root.add(gameOverScreen, "GAME_OVER");
        root.add(victoryScreen, "VICTORY");

        add(root);
        pack();
        setLocationRelativeTo(null); // centraliza
        showStart();                 // mostra tela inicial
    }

    public void showStart() {
        cards.show(root, "START");           // exibe menu
        startScreen.requestFocusInWindow();  // foca para input
    }

    public void startGame() {
        gamePanel.startNewGame();         // inicia novo jogo
        cards.show(root, "GAME");          // exibe jogo
        gamePanel.requestFocusInWindow();  // foca para input
    }

    public void showGameOver() {
        cards.show(root, "GAME_OVER");      
    }

    public void showVictory() {
        cards.show(root, "VICTORY");         
    }

    public void returnToMenu() {
        showStart();                  // volta para menu
    }
}


