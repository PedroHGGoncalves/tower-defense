package tdgame_2;

import java.util.ArrayList;
import java.awt.Graphics;

public class TowerManager {

    private ArrayList<Tower> torres;        // torres ativas
    private Map mapa;                        // mapa do jogo
    private WaveManager waveManager;         // inimigos/ondas
    private ProjectileManager pm;            // projéteis
    private Player player;                   // jogador

    public TowerManager(Map mapa, WaveManager waveManager,
                        ProjectileManager pm, Player player) {

        this.torres = new ArrayList<>();
        this.mapa = mapa;
        this.waveManager = waveManager;
        this.pm = pm;
        this.player = player;
    }

    public void adicionarTorre(Tower t) {
        torres.add(t);                       // registra torre
    }

    public void removerTorre(Tower t) {
        torres.remove(t);                    // remove torre
    }

    public void update() {
        for (Tower t : torres) {
            t.update();                      // lógica da torre
        }
    }

    public void desenhar(Graphics g) {
        for (Tower t : torres) {
            t.desenhar(g);                   // render da torre
        }
    }

    public java.util.List<Tower> getTorres() {
        return torres;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ProjectileManager getProjectileManager() {
        return pm;
    }

    public Player getPlayer() {
        return player;
    }

    // verifica se a célula já está ocupada
    public boolean existeTorreNaCelula(int col, int lin) {

        int tx = col * Map.TILE_SIZE;        // cálculo de pixel (não usado)
        int ty = lin * Map.TILE_SIZE;

        for (Tower t : torres) {
            if (t.getGridCol() == col &&
                t.getGridLin() == lin) {
                return true;
            }
        }
        return false;
    }
}


