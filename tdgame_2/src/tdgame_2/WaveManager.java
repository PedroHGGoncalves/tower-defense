package tdgame_2;

import java.awt.Graphics;
import java.util.ArrayList;

public class WaveManager {     // controla ondas e inimigos

    private ArrayList<Wave> waves;   // ondas configuradas
    private ArrayList<Enemy> inimigos;   // inimigos ativos

    private int ondaAtual = 0;    // índice da onda atual
    private int ticksSpawn = 0;    // tempo desde último spawn
    private int inimigosSpawnados = 0;  // spawnados na onda

    private Path caminho;    // caminho dos inimigos

    public WaveManager(Map mapa) {
        this.caminho = mapa.getCaminho(); // usa caminho do mapa

        waves = new ArrayList<>();
        inimigos = new ArrayList<>();

        waves.add(new Wave(5, 40));    // onda 1
        waves.add(new Wave(8, 30));    // onda 2
        waves.add(new Wave(10, 25));   // onda 3
    }

    public void update(Player jogador) {

        if (ondaAtual >= waves.size())  // sem mais ondas
            return;

        Wave w = waves.get(ondaAtual);  // onda atual

        // spawn de inimigos
        if (inimigosSpawnados < w.getQuantidade()) {
            if (ticksSpawn >= w.getIntervalo()) {
                inimigos.add(new SimpleEnemy(caminho));
                inimigosSpawnados++;
                ticksSpawn = 0;
            } else {
                ticksSpawn++;
            }
        }

        // movimenta inimigos
        for (Enemy e : inimigos) {
            e.mover();
        }

        ArrayList<Enemy> remover = new ArrayList<>();

        // checa morte ou chegada na base
        for (Enemy e : inimigos) {
            if (!e.isVivo()) {
                jogador.ganharMoedas(e.getRecompensa());
                remover.add(e);
            } else if (e.chegouNaBase()) {
                jogador.reduzirVida(1);
                remover.add(e);
            }
        }

        inimigos.removeAll(remover);   // limpa lista

        // avança onda se terminou
        if (inimigosSpawnados == w.getQuantidade() && inimigos.isEmpty()) {
            ondaAtual++;
            inimigosSpawnados = 0;
            ticksSpawn = 0;
        }
    }

    public void desenhar(Graphics g) {  // desenha inimigos vivos
        for (Enemy e : inimigos) {
            if (e.isVivo())
                e.desenhar(g);
        }
    }

    public int getOndaAtual() {     // retorna onda (1-based)
        return ondaAtual + 1;
    }

    public ArrayList<Enemy> getInimigos() {  // acesso aos inimigos
        return inimigos;
    }
}



