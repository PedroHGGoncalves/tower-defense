package tdgame_2;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JPanel {

    // Dimensão do painel baseada no grid
    private final int LARGURA = Map.COLS * Map.TILE_SIZE;
    private final int ALTURA  = Map.ROWS * Map.TILE_SIZE;

    // Loop principal do jogo
    private Timer loop;

    // Elementos centrais do jogo
    private Map mapa;
    private Player jogador;
    private WaveManager waves;

    // Sistemas auxiliares
    private TowerManager towerManager;
    private ProjectileManager projectileManager;

    // Controle de construção de torres
    private int modoConstrucao = 0; // 0=nenhum, 1=Arrow, 2=Cannon

    public Game() {

        // Configuração visual do painel
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(Color.BLACK);

        // Criação das entidades principais
        mapa = new Map();
        jogador = new Player(20);
        waves = new WaveManager(mapa);

        // Criação dos gerenciadores
        projectileManager = new ProjectileManager(waves);
        towerManager = new TowerManager(mapa, waves, projectileManager, jogador);

        // Clique do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Converte posição do clique para grid
                int col = e.getX() / Map.TILE_SIZE;
                int lin = e.getY() / Map.TILE_SIZE;

                // Clique esquerdo: construir
                if (e.getButton() == MouseEvent.BUTTON1) {

                    // Validação da célula
                    if (!mapa.podeConstruir(col, lin)) return;
                    if (towerManager.existeTorreNaCelula(col, lin)) return;

                    // ArrowTower
                    if (modoConstrucao == 1) {
                        ArrowTower t = new ArrowTower(col, lin, towerManager);
                        if (jogador.gastarMoedas(t.getCusto()))
                            towerManager.adicionarTorre(t);

                    // CannonTower
                    } else if (modoConstrucao == 2) {
                        CannonTower t = new CannonTower(col, lin, towerManager);
                        if (jogador.gastarMoedas(t.getCusto()))
                            towerManager.adicionarTorre(t);
                    }
                }

                // Clique direito: vender torre
                if (e.getButton() == MouseEvent.BUTTON3) {
                    for (Tower t : towerManager.getTorres()) {
                        if (t.getGridCol() == col && t.getGridLin() == lin) {
                            jogador.ganharMoedas(t.getCusto() / 2); // reembolso
                            towerManager.removerTorre(t);
                            break;
                        }
                    }
                }
            }
        });

        // Duplo clique: alternar modo de construção
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    modoConstrucao = (modoConstrucao + 1) % 3;
                }
            }
        });
    }

    // Inicia o loop do jogo
    public void iniciar() {
        loop = new Timer(16, e -> tick()); // ~60 FPS
        loop.start();
    }

    // Atualização geral do jogo
    private void tick() {

        // Atualizações lógicas
        waves.update(jogador);
        towerManager.update();
        projectileManager.update(jogador);

        // Atualiza renderização
        repaint();

        // Condição de derrota
        if (jogador.getVidaBase() <= 0) {
            loop.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenho do mundo
        mapa.desenhar(g);
        waves.desenhar(g);
        towerManager.desenhar(g);
        projectileManager.desenhar(g);

        // HUD
        g.setColor(Color.WHITE);
        g.drawString("Base: " + jogador.getVidaBase(), 20, 20);
        g.drawString("Onda: " + waves.getOndaAtual(), 20, 40);
        g.drawString("Moedas: " + jogador.getMoedas(), 20, 60);

        // Modo atual de construção
        String modo = modoConstrucao == 0 ? "Nenhum" :
                      (modoConstrucao == 1 ? "Arrow" : "Cannon");
        g.drawString("Modo: " + modo + " (duplo clique)", 20, 80);
    }
}



