package tdgame_3;

import java.awt.Graphics;

// Projétil disparado por uma torre
public class Projectile {

    private double x, y;              
    private Enemy alvo;               
    private double vel;              
    private int dano;                 
    private EffectType efeito;        
    private int duracaoEfeito;        
    private double intensidadeEfeito; 
    private boolean remover = false;  

    // Constrói um projétil sem efeito adicional
    public Projectile(double x, double y, Enemy alvo, double vel, int dano) {
        this(x, y, alvo, vel, dano, null, 0, 0.0);
    }

    // Constrói um projétil com efeito opcional
    public Projectile(double x, double y, Enemy alvo, double vel, int dano,
                      EffectType efeito, int duracaoEfeito, double intensidadeEfeito) {
        this.x = x;
        this.y = y;
        this.alvo = alvo;
        this.vel = vel;
        this.dano = dano;
        this.efeito = efeito;
        this.duracaoEfeito = duracaoEfeito;
        this.intensidadeEfeito = intensidadeEfeito;
    }

    // Atualiza o movimento e verifica impacto com o alvo
    public void update() {
        // Remove se o alvo não existir ou já morreu
        if (alvo == null || !alvo.isVivo()) {
            remover = true;
            return;
        }

        // Calcula direção até o alvo
        double dx = alvo.getX() - x;
        double dy = alvo.getY() - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        // Impacto: aplica dano e efeitos
        if (dist < vel || dist < 1.0) {
            x = alvo.getX();
            y = alvo.getY();

            alvo.receberDano(dano);

            if (efeito != null) {
                alvo.aplicarEfeito(efeito, duracaoEfeito, intensidadeEfeito);
            }

            remover = true;
        } else {
            // Move em direção ao alvo
            x += (dx / dist) * vel;
            y += (dy / dist) * vel;
        }
    }

    // Desenha o projétil como um ponto simples
    public void desenhar(Graphics g) {
        g.setColor(java.awt.Color.WHITE);
        g.fillOval((int) (x - 4), (int) (y - 4), 8, 8);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getDano() { return dano; }
    public boolean isRemover() { return remover; }
}

