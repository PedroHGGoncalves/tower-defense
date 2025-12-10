package tdgame_4;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gerencia o spawn, atualização e remoção de inimigos em ondas.
 */
public class WaveManager {

    private List<Wave> waves;              // Lista de ondas
    private ArrayList<Enemy> inimigos;     // Inimigos ativos
    private int ondaAtual = 0;             
    private int ticksSpawn = 0;            
    private int inimigosSpawnados = 0;     
    private Path caminho;                   
    private Random random;                  

    public WaveManager(Map mapa) {
        this.caminho = mapa.getCaminho();
        this.inimigos = new ArrayList<>();
        this.random = new Random();         // Seed aleatória
        this.waves = new ArrayList<>();
        // Ondas padrão
        waves.add(new Wave(5, 42)); //"(5,40)" - número de inimigos, intervalo de spawn
        waves.add(new Wave(8, 39));
        waves.add(new Wave(12, 36));
        waves.add(new Wave(16, 32));
        waves.add(new Wave(20, 28));
        waves.add(new Wave(25, 23));
        waves.add(new Wave(33, 17));
        waves.add(new Wave(43, 13));
        waves.add(new Wave(55, 9));
        waves.add(new Wave(72, 7));
    }

    public void update(Player jogador) {
        if (ondaAtual >= waves.size()) return;    // Todas as ondas concluídas
        Wave w = waves.get(ondaAtual);

        // Spawn de inimigos
        if (inimigosSpawnados < w.getQuantidade()) {
            if (ticksSpawn >= w.getIntervalo()) {
                Enemy e;
                int tipo = random.nextInt(100);
                
             // Definir faixa de onda para ajustar probabilidades
                int onda = ondaAtual + 1; // ondas começam de 1

                if (onda <= 4) { // ondas iniciais: quase só simples
                    if (tipo < 80) e = new SimpleEnemy(caminho);
                    else if (tipo < 95) e = new FastEnemy(caminho);
                    else e = new TankEnemy(caminho);
                } else if (onda <= 7) { // ondas médias: mistura equilibrada
                    if (tipo < 50) e = new SimpleEnemy(caminho);
                    else if (tipo < 80) e = new FastEnemy(caminho);
                    else e = new TankEnemy(caminho);
                } else { // ondas finais: mais rápidos e tanques
                    if (tipo < 30) e = new SimpleEnemy(caminho);
                    else if (tipo < 70) e = new FastEnemy(caminho);
                    else e = new TankEnemy(caminho);
                }

                inimigos.add(e);
                inimigosSpawnados++;
                ticksSpawn = 0;
            } else ticksSpawn++;
        }

        // Atualizar estado e remover inimigos mortos ou que chegaram à base
        ArrayList<Enemy> remover = new ArrayList<>();
        for (Enemy e : inimigos) {
            if (!e.isVivo()) {
                jogador.ganharMoedas(e.getRecompensa());
                remover.add(e);
            } else if (e.chegouNaBase()) {
                jogador.reduzirVida(e.getDano());
                remover.add(e);
            } else {
                e.updateStatus(); // Atualiza efeitos, cooldowns, etc.
            }
        }
        
     // Mover inimigos
        for (Enemy e : inimigos) e.mover();
        
        
        inimigos.removeAll(remover);

        // Avança para a próxima onda se todos os inimigos foram spawnados e removidos
        if (inimigosSpawnados == w.getQuantidade() && inimigos.isEmpty()) {
            ondaAtual++;
            inimigosSpawnados = 0;
            ticksSpawn = 0;
        }
    }

    public void desenhar(Graphics g) {
        for (Enemy e : inimigos) if (e.isVivo()) e.desenhar(g); // Desenha inimigos vivos
    }

    public int getOndaAtual() { return Math.min(ondaAtual + 1, waves.size()); } // Índice visível da onda

    public int getTotalWaves() { return waves.size(); }

    public ArrayList<Enemy> getInimigos() { return inimigos; }

    public boolean isFinished() { return ondaAtual >= waves.size(); } // Todas ondas concluídas
}


