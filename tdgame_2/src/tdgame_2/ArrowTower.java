package tdgame_2;

//Torre barata, com dano pequeno, alcance médio e cooldown pequeno 
public class ArrowTower extends Tower {

    public ArrowTower(int col, int lin, TowerManager manager) {
        // Inicializa posição e vínculo com o gerenciador
        super(col, lin, manager);

        // Configuração específica desta torre
        this.alcance = 120;
        this.dano = 2;
        this.custo = 20;
        this.cooldownMax = 10;
    }

    @Override
    protected void atirar(Enemy alvo) {
        // Cria projétil a partir do centro da torre, seguindo o alvo
        Projectile p = new Projectile(
            x + Map.TILE_SIZE / 2,
            y + Map.TILE_SIZE / 2,
            alvo,
            6.0,
            dano
        );

        // Envia o projétil ao gerenciador global
        manager.getProjectileManager().spawn(p);
    }

    @Override
    public void desenhar(java.awt.Graphics g) {
        // Representação visual simples da torre
        g.setColor(java.awt.Color.CYAN);
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);

        g.setColor(java.awt.Color.BLACK);
        g.drawString("A", x + 6, y + 18);
    }
}


