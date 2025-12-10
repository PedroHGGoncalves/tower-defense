package tdgame_2;

// Torre cara, com alto dano, maior alcance e cooldown grande
public class CannonTower extends Tower {

    public CannonTower(int col, int lin, TowerManager manager) {
        // Inicializa posição e vínculo com o gerenciador
        super(col, lin, manager);

        // Configuração da torre pesada
        this.alcance = 140;
        this.dano = 6;
        this.custo = 50;
        this.cooldownMax = 30;
    }

    @Override
    protected void atirar(Enemy alvo) {
        // Dispara projétil mais lento, porém de alto dano
        Projectile p = new Projectile(
            x + Map.TILE_SIZE / 2,
            y + Map.TILE_SIZE / 2,
            alvo,
            3.5,
            dano
        );

        // Registra o projétil no sistema global
        manager.getProjectileManager().spawn(p);
    }

    @Override
    public void desenhar(java.awt.Graphics g) {
        // Representação visual da torre
        g.setColor(java.awt.Color.ORANGE);
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);

        g.setColor(java.awt.Color.BLACK);
        g.drawString("C", x + 6, y + 18);
    }
}


