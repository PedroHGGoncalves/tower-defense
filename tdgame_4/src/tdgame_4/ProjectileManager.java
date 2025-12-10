package tdgame_4;

import java.awt.Graphics;
import java.util.ArrayList;

public class ProjectileManager {

    private ArrayList<Projectile> projeteis; // Lista de projéteis ativos

    public ProjectileManager() {
        this.projeteis = new ArrayList<>(); // Inicializa lista
    }

    public void spawn(Projectile p) {
        projeteis.add(p); // Adiciona projétil à lista
    }

    public void update() {
        ArrayList<Projectile> remover = new ArrayList<>(); // Lista temporária de remoção
        for (Projectile p : projeteis) {
            p.update();               // Atualiza projétil
            if (p.isRemover()) remover.add(p); // Marca para remoção
        }
        projeteis.removeAll(remover); // Remove projéteis marcados
    }

    public void desenhar(Graphics g) {
        for (Projectile p : projeteis) 
            p.desenhar(g); // Desenha todos os projéteis
    }
}


