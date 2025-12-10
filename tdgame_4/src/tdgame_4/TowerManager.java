package tdgame_4;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

//Gerencia todas as torres do mapa: atualização, desenho e verificações.
public class TowerManager {

    private ArrayList<Tower> torres;          // Lista de torres ativas
    private Map mapa;                          
    private WaveManager waveManager;           
    private ProjectileManager projectileManager; 
    private Player player;                     

    public TowerManager(Map mapa, WaveManager waveManager, ProjectileManager pm, Player player) {
        this.torres = new ArrayList<>();      // Inicializa lista de torres
        this.mapa = mapa;
        this.waveManager = waveManager;
        this.projectileManager = pm;
        this.player = player;
    }

    public void adicionarTorre(Tower t) { torres.add(t); }   

    public void removerTorre(Tower t) { torres.remove(t); }  

    public void update() {
        for (Tower t : torres) t.update();    
    }

    public void desenhar(Graphics g) {
        for (Tower t : torres) t.desenhar(g); 
    }

    public List<Tower> getTorres() { return torres; }
    public WaveManager getWaveManager() { return waveManager; }
    public ProjectileManager getProjectileManager() { return projectileManager; }
    public Player getPlayer() { return player; }

    public boolean existeTorreNaCelula(int col, int lin) {
        for (Tower t : torres)
            if (t.getGridCol() == col && t.getGridLin() == lin)
                return true;                     // Retorna true se já houver torre na célula
        return false;
    }
}

