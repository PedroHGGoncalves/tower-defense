package tdgame_2;

public class Projectile { 

    // Posição atual do projétil
    private double x, y;

    // Inimigo que o projétil persegue
    private Enemy alvo;

    // Velocidade de deslocamento
    private double vel;

    // Dano aplicado ao acertar
    private int dano;

    // Flag para remoção do projétil
    private boolean remover = false;

    public Projectile(double x, double y, Enemy alvo, double vel, int dano) {
        this.x = x;
        this.y = y;
        this.alvo = alvo;
        this.vel = vel;
        this.dano = dano;
    }

    public void update() {

        // Se não há alvo válido, descarta o projétil
        if (alvo == null || !alvo.isVivo()) {
            remover = true;
            return;
        }

        // Vetor direção até o alvo
        double dx = alvo.getX() - x;
        double dy = alvo.getY() - y;

        // Distância até o alvo
        double dist = Math.sqrt(dx * dx + dy * dy);

        // Alcançou o alvo
        if (dist < vel || dist < 1.0) {
            x = alvo.getX();
            y = alvo.getY();
        } 
        // Move em direção ao alvo
        else {
            x += (dx / dist) * vel;
            y += (dy / dist) * vel;
        }
    }

    public void desenhar(java.awt.Graphics g) {
        g.setColor(java.awt.Color.WHITE);
        g.fillOval((int)(x - 4), (int)(y - 4), 8, 8);
    }

    // Getters usados por colisão e renderização
    public double getX() { return x; }
    public double getY() { return y; }

    // Dano aplicado ao acertar
    public int getDano() { return dano; }

    // Controle de descarte
    public boolean isRemover() { return remover; }
    public void setRemover(boolean r) { remover = r; }
}

