package tdgame_3;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

// Classe base para os inimigos do jogo
public abstract class Enemy {

    protected double x, y;    // posição atual no mapa
    protected int gridIndex = 0;     // índice do ponto atual no caminho
    protected Path caminho;    // caminho que o inimigo percorre
    protected int vida;
    protected double velocidade;
    protected int recompensa;
    protected int dano;         

    protected Color cor = Color.RED;

    // Lista de efeitos de status ativos no inimigo
    protected ArrayList<StatusEffect> efeitos = new ArrayList<>();

    // Cria o inimigo associado a um caminho específico
    // Posiciona o inimigo exatamente no primeiro ponto do caminho
    public Enemy(Path caminho) {
        this.caminho = caminho;                            // armazena o caminho a seguir
        int[] start = caminho.getPontos().get(0);          // primeiro ponto do caminho
        this.x = start[0] * Map.TILE_SIZE;                 // converte grid para coordenada real
        this.y = start[1] * Map.TILE_SIZE;
    }

    // Move o inimigo em direção ao próximo ponto do caminho
    // A velocidade final considera efeitos ativos (ex: slow)
    public void mover() {
        // Não move se já morreu ou se já alcançou a base
        if (!isVivo() || chegouNaBase()) return;

        // Próximo ponto do caminho a ser alcançado
        int[] destino = caminho.getPontos().get(gridIndex + 1);

        // Vetor direção até o próximo ponto
        double dx = destino[0] * Map.TILE_SIZE - x;
        double dy = destino[1] * Map.TILE_SIZE - y;

        // Distância até o ponto alvo
        double dist = Math.sqrt(dx * dx + dy * dy);

        // Velocidade ajustada por efeitos de status
        double velAtual = velocidade * getVelocidadeModificador();

        // Se alcançou (ou ultrapassaria) o ponto, avança no caminho
        if (dist <= velAtual) {
            x = destino[0] * Map.TILE_SIZE;
            y = destino[1] * Map.TILE_SIZE;
            gridIndex++;                                   // avança para o próximo ponto
        } else {
            // Move proporcionalmente na direção do destino
            x += (dx / dist) * velAtual;
            y += (dy / dist) * velAtual;
        }
    }

    // Indica se o inimigo já alcançou o final do caminho
    public boolean chegouNaBase() {
        return gridIndex >= caminho.getPontos().size() - 1;
    }

    // Verifica se o inimigo ainda está vivo
    public boolean isVivo() {
        return vida > 0;
    }

    // Reduz a vida do inimigo
    public void receberDano(int d) {
        vida -= d;
    }

    // Aplica um efeito de status, respeitando imunidades por tipo de inimigo
    public void aplicarEfeito(EffectType tipo, int duracao, double intensidade) {
        if (tipo == EffectType.SLOW && this instanceof FastEnemy) return;
        if (tipo == EffectType.BURN && this instanceof TankEnemy) return;

        efeitos.add(new StatusEffect(tipo, duracao, intensidade));
    }

    // Atualiza efeitos ativos (dano por tick e remoção ao expirar)
    public void updateStatus() {
        ArrayList<StatusEffect> remover = new ArrayList<>();
        for (StatusEffect s : efeitos) {
            s.ticks--;
            if (s.tipo == EffectType.BURN) {
                receberDano((int) Math.ceil(s.intensidade));
            }
            if (s.ticks <= 0) remover.add(s);
        }
        efeitos.removeAll(remover);
    }

    // Calcula o fator multiplicador da velocidade com base em slows acumulados
    private double getVelocidadeModificador() {
        double mod = 1.0;
        for (StatusEffect s : efeitos) {
            if (s.tipo == EffectType.SLOW) mod *= (1.0 - s.intensidade);
        }
        return mod;
    }

    // Desenha o inimigo no mapa
    public void desenhar(Graphics g) {
        g.setColor(cor);
        g.fillOval((int) x, (int) y, Map.TILE_SIZE, Map.TILE_SIZE);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getRecompensa() { return recompensa; }

    // Retorna o dano causado ao alcançar a base
    public int getDano() { return dano; }

    // Estrutura interna para armazenar efeitos ativos
    protected static class StatusEffect {
        EffectType tipo;
        int ticks;
        double intensidade;

        public StatusEffect(EffectType tipo, int ticks, double intensidade) {
            this.tipo = tipo;
            this.ticks = ticks;
            this.intensidade = intensidade;
        }
    }
}



