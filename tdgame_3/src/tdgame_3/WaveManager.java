package tdgame_3;

import java.awt.Graphics;
import java.util.ArrayList;

// Gerencia o spawn e atualização de inimigos por ondas
public class WaveManager {

    private ArrayList<Wave> waves;      // lista de ondas configuradas
    private ArrayList<Enemy> inimigos;  // inimigos ativos no mapa
    private int ondaAtual = 0;          
    private int ticksSpawn = 0;         
    private int inimigosSpawnados = 0;  
    private Path caminho;               

    // Inicializa ondas e caminho do mapa
    public WaveManager(Map mapa) {
        this.caminho = mapa.getCaminho();
        waves = new ArrayList<>();
        inimigos = new ArrayList<>();
        // ondas com progressão de dificuldade
        waves.add(new Wave(5, 40));
        waves.add(new Wave(8, 35));
        waves.add(new Wave(10, 30));
        waves.add(new Wave(12, 25));
        waves.add(new Wave(15, 20));
    }

    // Atualiza spawn, movimento e efeitos dos inimigos
    public void update(Player jogador) {
        if (ondaAtual >= waves.size()) return;
        Wave w = waves.get(ondaAtual);

        // Spawn de inimigos conforme intervalo
        if (inimigosSpawnados < w.getQuantidade()) {
            if (ticksSpawn >= w.getIntervalo()) {
                Enemy e;
                int tipo = (int)(Math.random() * 3); // 0=Simples,1=Rápido,2=Tanque
                switch(tipo){
                    case 0: e = new SimpleEnemy(caminho); break;
                    case 1: e = new FastEnemy(caminho); break;
                    default: e = new TankEnemy(caminho); break;
                }
                inimigos.add(e);
                inimigosSpawnados++;
                ticksSpawn = 0;
            } else ticksSpawn++;
        }

        // Movimenta inimigos
        for (Enemy e : inimigos) e.mover();

        // Remove inimigos mortos ou que chegaram à base
        ArrayList<Enemy> remover = new ArrayList<>();
        for (Enemy e : inimigos) {
            if (!e.isVivo()) {
                jogador.ganharMoedas(e.getRecompensa());
                remover.add(e);
            } else if (e.chegouNaBase()) {
                jogador.reduzirVida(1);
                remover.add(e);
            } else {
                e.updateStatus(); // aplica efeitos de status
            }
        }
        inimigos.removeAll(remover);

        // Avança para próxima onda quando atual terminar
        if (inimigosSpawnados == w.getQuantidade() && inimigos.isEmpty()) {
            ondaAtual++;
            inimigosSpawnados = 0;
            ticksSpawn = 0;
        }
    }

    // Desenha inimigos ativos
    public void desenhar(Graphics g) {
        for (Enemy e : inimigos) if (e.isVivo()) e.desenhar(g);
    }

    public int getOndaAtual() { return ondaAtual + 1; }
    public ArrayList<Enemy> getInimigos() { return inimigos; }
}

