package tdgame_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Controla loop, entrada do usuário e renderização
public class Game extends JPanel {

    private final int LARGURA = Map.COLS * Map.TILE_SIZE; // largura do mapa
    private final int ALTURA = Map.ROWS * Map.TILE_SIZE;  // altura do mapa

    private Timer loop;           
    private Map mapa;             
    private Player jogador;       
    private WaveManager waves;    

    private TowerManager towerManager;             
    private ProjectileManager projectileManager;   

    private int modoConstrucao = 0; // 0=nenhum, 1=Arrow, 2=Cannon

    // Inicializa jogo, gerentes e controles de entrada
    public Game() {
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(Color.BLACK);

        mapa = new Map();
        jogador = new Player(20);
        waves = new WaveManager(mapa);

        projectileManager = new ProjectileManager(waves);
        towerManager = new TowerManager(mapa, waves, projectileManager, jogador);

        // Controle do mouse: construir, upgrade e remover torres
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int col = e.getX() / Map.TILE_SIZE;
                int lin = e.getY() / Map.TILE_SIZE;

                // Clique esquerdo: construir torre
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!mapa.podeConstruir(col, lin)) return;
                    if (towerManager.existeTorreNaCelula(col, lin)) return;

                    Tower torre = null;
                    if (modoConstrucao == 1) torre = new ArrowTower(col, lin, towerManager);
                    else if (modoConstrucao == 2) torre = new CannonTower(col, lin, towerManager);

                    if (torre != null && jogador.gastarMoedas(torre.getCusto())) {
                        towerManager.adicionarTorre(torre);
                    }
                }

                // Clique direito: upgrade ou remoção
                if (SwingUtilities.isRightMouseButton(e)) {
                    for (Tower t : towerManager.getTorres()) {
                        if (t.getGridCol() == col && t.getGridLin() == lin) {
                            if (e.isShiftDown()) { // remover torre
                                jogador.ganharMoedas(t.getCusto() / 2);
                                towerManager.removerTorre(t);
                            } else {                // melhorar torre
                                t.upgrade(jogador);
                            }
                            break;
                        }
                    }
                }
            }
        });

        // Teclado: alterna tipo de torre em construção
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'a') modoConstrucao = 1;
                else if (e.getKeyChar() == 'c') modoConstrucao = 2;
                else if (e.getKeyChar() == 'n') modoConstrucao = 0;
                repaint();
            }
        });

        setFocusable(true);
    }

    // Inicia o loop do jogo (≈60 FPS)
    public void iniciar() {
        loop = new Timer(16, e -> tick());
        loop.start();
    }

    // Atualiza lógica do jogo a cada tick
    private void tick() {
        waves.update(jogador);               
        towerManager.update();              
        projectileManager.update(jogador);  

        repaint();

        // Condição de derrota
        if (jogador.getVidaBase() <= 0) {
            loop.stop();
            System.out.println("Derrota!");
        }
    }

    // Desenha mapa, entidades e HUD
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        mapa.desenhar(g);
        waves.desenhar(g);
        towerManager.desenhar(g);
        projectileManager.desenhar(g);

        // HUD básico
        g.setColor(new Color(128, 0, 128)); // indicador da base
        g.fillRect(0, 0, 10, 10);

        g.setColor(Color.WHITE);
        g.drawString("Base: " + jogador.getVidaBase(), 20, 20);
        g.drawString("Onda: " + waves.getOndaAtual(), 20, 40);
        g.drawString("Moedas: " + jogador.getMoedas(), 20, 60);

        String modo = modoConstrucao == 0 ? "Nenhum" :
                      (modoConstrucao == 1 ? "Arrow" : "Cannon");
        g.drawString("Modo: " + modo + " (a/c/n)", 20, 80);
        g.drawString("Esq: construir | Dir: upgrade | Shift+Dir: remover", 20, 100);
    }
}


