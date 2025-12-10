package tdgame;

import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Controla a progressão das ondas,
 * o spawn de inimigos e sua atualização.
 */
public class WaveManager {

    // Ondas configuradas e inimigos ativos
    private ArrayList<Wave> waves;
    private ArrayList<Enemy> inimigos;

    // Estado da onda atual
    private int ondaAtual = 0;
    private int ticksSpawn = 0;
    private int inimigosSpawnados = 0;

    // Caminho seguido pelos inimigos
    private Path caminho;

    public WaveManager(Map mapa) {
        // Obtém o caminho definido pelo mapa
        this.caminho = mapa.getCaminho();

        waves = new ArrayList<>();
        inimigos = new ArrayList<>();

        // Configuração fixa das ondas desta entrega
        waves.add(new Wave(5, 40));
        waves.add(new Wave(8, 30));
        waves.add(new Wave(10, 25));
    }

    /*
     * Atualiza a lógica de spawn, movimento
     * e progressão das ondas a cada tick.
     */
    public void update(Player jogador) {

        // Não atualiza se todas as ondas já foram concluídas
        if (ondaAtual >= waves.size())
            return;

        Wave w = waves.get(ondaAtual);

        // Controla o spawn gradual dos inimigos
        if (inimigosSpawnados < w.getQuantidade()) {

            if (ticksSpawn >= w.getIntervalo()) {
                inimigos.add(new SimpleEnemy(caminho));
                inimigosSpawnados++;
                ticksSpawn = 0;
            } else {
                ticksSpawn++;
            }
        }

        // Atualiza o movimento de todos os inimigos ativos
        for (Enemy e : inimigos) {
            e.mover();
        }

        // Identifica inimigos que chegaram à base
        ArrayList<Enemy> remover = new ArrayList<>();

        for (Enemy e : inimigos) {
            if (e.chegouNaBase()) {
                jogador.reduzirVida(1);
                remover.add(e);
            }
        }

        // Remove inimigos processados
        inimigos.removeAll(remover);

        // Avança para a próxima onda quando a atual termina
        if (inimigosSpawnados == w.getQuantidade() && inimigos.isEmpty()) {
            ondaAtual++;
            inimigosSpawnados = 0;
            ticksSpawn = 0;
        }
    }

    // Renderiza todos os inimigos ativos
    public void desenhar(Graphics g) {
        for (Enemy e : inimigos) {
            e.desenhar(g);
        }
    }

    // Retorna a onda atual em formato humano (1, 2, 3...)
    public int getOndaAtual() {
        return ondaAtual + 1;
    }
}



