package tdgame_3;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// Gerencia todas as torres: criação, atualização e desenho
public class TowerManager {

    private ArrayList<Tower> torres;
    private Map mapa;
    private WaveManager waveManager;
    private ProjectileManager projectileManager;
    private Player player;

    // Inicializa manager com referências a mapa, ondas, projéteis e jogador
    public TowerManager(Map mapa, WaveManager waveManager, ProjectileManager pm, Player player) {
        this.torres = new ArrayList<>();
        this.mapa = mapa;
        this.waveManager = waveManager;
        this.projectileManager = pm;
        this.player = player;
    }

    // Adiciona torre ao jogo
    public void adicionarTorre(Tower t) {
        torres.add(t);
    }

    // Remove torre do jogo
    public void removerTorre(Tower t) {
        torres.remove(t);
    }

    // Atualiza todas as torres (cooldown e ataque)
    public void update() {
        for (Tower t : torres) t.update();
    }

    // Desenha todas as torres no mapa
    public void desenhar(Graphics g) {
        for (Tower t : torres) t.desenhar(g);
    }

    public List<Tower> getTorres() { return torres; }
    public WaveManager getWaveManager() { return waveManager; }
    public ProjectileManager getProjectileManager() { return projectileManager; }
    public Player getPlayer() { return player; }

    // Verifica se já existe torre na célula
    public boolean existeTorreNaCelula(int col, int lin) {
        for (Tower t : torres)
            if (t.getGridCol() == col && t.getGridLin() == lin)
                return true;
        return false;
    }
}


