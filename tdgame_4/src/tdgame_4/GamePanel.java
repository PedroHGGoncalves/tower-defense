package tdgame_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Painel principal do jogo, gerencia lógica, entrada do jogador, atualização e renderização
public class GamePanel extends JPanel {

    private final int WIDTH = Map.COLS * Map.TILE_SIZE;
    private final int HEIGHT = Map.ROWS * Map.TILE_SIZE;

    private Timer loop;                        // loop do jogo (~60 FPS)
    private Map mapa;                           // mapa/grid
    private Player jogador;                     // estado do jogador/base
    private WaveManager waves;                  // gerencia ondas de inimigos
    private TowerManager towerManager;          // gerencia torres
    private ProjectileManager projectileManager; // gerencia projéteis
    private GameFrame parent;                   // referência à janela

    private int modoConstrucao = 0;             // 0=nenhum,1=Arrow,2=Cannon

    public GamePanel(GameFrame parent) { 
        this.parent = parent;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);

        initGame(); // inicializa objetos do jogo

        // input do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow(); // garante foco para teclado
                int col = e.getX() / Map.TILE_SIZE;
                int lin = e.getY() / Map.TILE_SIZE;

                if (SwingUtilities.isLeftMouseButton(e)) { // construir torre
                    if (!mapa.podeConstruir(col, lin)) return;
                    if (towerManager.existeTorreNaCelula(col, lin)) return;

                    Tower torre = null;
                    if (modoConstrucao == 1) torre = new ArrowTower(col, lin, towerManager);
                    else if (modoConstrucao == 2) torre = new CannonTower(col, lin, towerManager);

                    if (torre != null && jogador.gastarMoedas(torre.getCusto())) {
                        towerManager.adicionarTorre(torre);
                    }
                }

                if (SwingUtilities.isRightMouseButton(e)) { // upgrade ou remover
                    for (Tower t : towerManager.getTorres()) {
                        if (t.getGridCol() == col && t.getGridLin() == lin) {
                            if (e.isShiftDown()) { // remover torre
                                jogador.ganharMoedas(t.getCusto() / 2);
                                towerManager.removerTorre(t);
                            } else { // upgrade torre
                                t.upgrade(jogador);
                            }
                            break;
                        }
                    }
                }
                repaint();
            }
        });

        // input do teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == 'a') modoConstrucao = 1;   // selecionar Arrow
                else if (c == 'c') modoConstrucao = 2; // selecionar Cannon
                else if (c == 'n') modoConstrucao = 0; // nenhum
                repaint();
            }
        });
        setFocusable(true);
    }

    // inicializa objetos do jogo com valores padrão
    private void initGame() {
        mapa = new Map();
        jogador = new Player(20, 50); // vida inicial padrão
        waves = new WaveManager(mapa); // ondas padrão
        projectileManager = new ProjectileManager();
        towerManager = new TowerManager(mapa, waves, projectileManager, jogador);
    }

    // reinicia jogo e inicia loop
    public void startNewGame() {
        initGame();
        if (loop != null) loop.stop();
        loop = new Timer(16, e -> tick()); // ~60 FPS
        loop.start();
    }

    // lógica de cada tick
    private void tick() {
        waves.update(jogador);            // spawn e movimento inimigos
        towerManager.update();             // torres atacam
        projectileManager.update();        // projéteis movem/aplicam efeito

        if (jogador.getVidaBase() <= 0) { // derrota
            loop.stop();
            parent.showGameOver();
            return;
        }

        if (waves.isFinished()) {         // vitória
            loop.stop();
            parent.showVictory();
            return;
        }

        repaint();                        // atualiza tela
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mapa.desenhar(g);                 // desenha grid
        waves.desenhar(g);                // desenha inimigos
        towerManager.desenhar(g);         // desenha torres
        projectileManager.desenhar(g);    // desenha projéteis

        // HUD
        g.setColor(new Color(255, 140, 0)); // barra base laranja
        g.fillRect(10, 10, 120, 18);
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 120, 18);
        g.setColor(Color.WHITE);
        g.drawString("Base: " + jogador.getVidaBase(), 15, 24);
        g.drawString("Onda: " + waves.getOndaAtual() + " / " + waves.getTotalWaves(), 150, 24);
        g.drawString("Moedas: " + jogador.getMoedas(), 330, 24);

        String modo = modoConstrucao == 0 ? "Nenhum" : (modoConstrucao == 1 ? "Arrow" : "Cannon");
        g.drawString("Modo: " + modo + " (a/c/n)", 10, 44);
        g.drawString("Clique esquerdo: construir | Clique direito: upgrade | Shift+Clique direito: remover", 10, 64);
    }
}


