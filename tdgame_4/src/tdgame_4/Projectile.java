package tdgame_4;

import java.awt.*;

public class Projectile {

    private double x, y;               // Posição do projétil
    private Enemy alvo;                
    private double vel;                
    private int dano;                  
    private EffectType efeito;         
    private int duracaoEfeito;         
    private double intensidadeEfeito;  
    private boolean remover = false;   // Marca para remoção

    public Projectile(double x, double y, Enemy alvo, double vel, int dano) {
        this(x, y, alvo, vel, dano, null, 0, 0.0); 
    }

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

    public void update() {
        if (alvo == null || !alvo.isVivo()) {
            remover = true; // Remove se alvo morto ou nulo
            return;
        }

        double dx = alvo.getX() - x;
        double dy = alvo.getY() - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < vel || dist < 1.0) {
            x = alvo.getX();
            y = alvo.getY();
            alvo.receberDano(dano); // Aplica dano
            if (efeito != null) {
                alvo.aplicarEfeito(efeito, duracaoEfeito, intensidadeEfeito); // Aplica efeito se houver
            }
            remover = true; // Marca para remoção
        } else {
            x += (dx / dist) * vel; // Move na direção do alvo
            y += (dy / dist) * vel;
        }
    }

    public void desenhar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)(x - 4), (int)(y - 4), 8, 8); // Desenha projétil
    }

    public boolean isRemover() { 
        return remover; // Retorna se deve ser removido
    }
}


