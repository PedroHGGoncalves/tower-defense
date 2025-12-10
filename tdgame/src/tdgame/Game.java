package tdgame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

/*
 * Classe principal do jogo.
 * Responsável por manter o loop de atualização, coordenar os sistemas
 * (mapa, jogador e ondas) e realizar o render na tela.
 */
public class Game extends JPanel {

    // Dimensões fixas da área de jogo
    private final int LARGURA = 960;
    private final int ALTURA = 576;

    // Loop principal baseado em Timer (tempo discreto)
    private Timer loop;

    // Sistemas centrais do jogo
    private Map mapa;
    private Player jogador;
    private WaveManager waves;

    public Game() {
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(Color.BLACK);

        // Inicialização dos sistemas do jogo
        mapa = new Map();
        jogador = new Player(20);          // Vida inicial da base
        waves = new WaveManager(mapa);     // Controla spawn e progressão das ondas
    }

    // Inicia o loop principal do jogo
    public void iniciar() {
        loop = new Timer(16, e -> tick()); // ~60 atualizações por segundo
        loop.start();
    }

    // Atualização lógica do jogo (game loop)
    private void tick() {

        // Atualiza o sistema de ondas e aplica danos à base
        waves.update(jogador);

        // Solicita redesenho da tela
        repaint();

        // Condição de fim de jogo
        if (jogador.getVidaBase() <= 0) {
            loop.stop();
            System.out.println("Derrota!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Renderização do estado atual do jogo
        mapa.desenhar(g);
        waves.desenhar(g);

        // HUD minimalista
        g.setColor(Color.WHITE);
        g.drawString("Base: " + jogador.getVidaBase(), 20, 20);
        g.drawString("Onda: " + waves.getOndaAtual(), 20, 40);
    }
}

