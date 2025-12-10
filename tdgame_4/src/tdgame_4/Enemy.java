package tdgame_4;

import java.awt.*;
import java.util.ArrayList;

// Classe base para inimigos, gerencia movimento, vida, efeitos e desenho
public abstract class Enemy {

    protected double x, y;                  
    protected int gridIndex = 0;            
    protected Path caminho;                 
    protected int vida;                    
    protected double velocidade;            
    protected int recompensa;              
    protected int dano;                     

    protected Color cor = Color.RED;        
    protected ArrayList<StatusEffect> efeitos = new ArrayList<>(); // efeitos ativos

    public Enemy(Path caminho) {
        this.caminho = caminho;
        int[] start = caminho.getPontos().get(0);
        this.x = start[0] * Map.TILE_SIZE;  // posiciona no início do caminho
        this.y = start[1] * Map.TILE_SIZE;
    }

    // Move o inimigo em direção ao próximo ponto do caminho
    public void mover() {
    	 // não faz nada se inimigo morto ou já chegou na base
        if (!isVivo() || chegouNaBase()) return;

     // próximo ponto do caminho
        int[] destino = caminho.getPontos().get(gridIndex + 1);
     
        // diferença de posição
        double dx = destino[0] * Map.TILE_SIZE - x;
        double dy = destino[1] * Map.TILE_SIZE - y;
        double dist = Math.sqrt(dx*dx + dy*dy); // distância até o ponto
        double velAtual = velocidade * getVelocidadeModificador(); // velocidade considerando efeitos

        if (dist <= velAtual) {
        	// se o inimigo alcança o ponto neste tick, "pula" para ele
            x = destino[0] * Map.TILE_SIZE;
            y = destino[1] * Map.TILE_SIZE;
            gridIndex++;
        } else {
            x += (dx / dist) * velAtual;
            y += (dy / dist) * velAtual;
        }
    }

    public boolean chegouNaBase() { return gridIndex >= caminho.getPontos().size() - 1; }

    public boolean isVivo() { return vida > 0; }

    public void receberDano(int d) { vida -= d; }

    // Aplica efeito se não houver imunidade
    public void aplicarEfeito(EffectType tipo, int duracao, double intensidade) {
        if (tipo == EffectType.SLOW && this instanceof FastEnemy) return;
        if (tipo == EffectType.BURN && this instanceof TankEnemy) return;
        efeitos.add(new StatusEffect(tipo, duracao, intensidade));
    }

    // Atualiza efeitos ativos a cada tick
    public void updateStatus() {
        ArrayList<StatusEffect> remover = new ArrayList<>();
        for (StatusEffect s : efeitos) {
            s.ticks--;
            if (s.tipo == EffectType.BURN) receberDano((int)Math.ceil(s.intensidade));
            if (s.ticks <= 0) remover.add(s);
        }
        efeitos.removeAll(remover);
    }

    // Modificador de velocidade baseado em efeitos Slow
    private double getVelocidadeModificador() {
        double mod = 1.0;
        for (StatusEffect s : efeitos) if (s.tipo == EffectType.SLOW) mod *= (1.0 - s.intensidade);
        return mod;
    }

    // Desenha inimigo e barra de vida
    public void desenhar(Graphics g) {
        g.setColor(cor);
        g.fillOval((int)x, (int)y, Map.TILE_SIZE, Map.TILE_SIZE);

        g.setColor(Color.BLACK);
        int bx = (int)x;
        int by = (int)y - 6;
        g.drawRect(bx, by, Map.TILE_SIZE, 4);

        g.setColor(Color.GREEN);
        int w = Math.max(0, (int)((double)vida / getMaxVida() * Map.TILE_SIZE));
        g.fillRect(bx, by, w, 4);
    }

    protected abstract int getMaxVida(); // vida máxima do inimigo

    public double getX() { return x; }
    public double getY() { return y; }
    public int getRecompensa() { return recompensa; }
    public int getDano() { return dano; }

    // Efeito de status interno
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

