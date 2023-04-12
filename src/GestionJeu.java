import java.util.List;
import java.util.ArrayList;

public class GestionJeu{
    private EnsembleChaines chaines;
    private Vaisseau leVaisseau;
    private List<Projectile> listeProj;
    private List<Alien> aliens;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.leVaisseau = new Vaisseau();
        this.listeProj = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.aliens.add(new Alien(85, 85));
    }

    public int getHauteur(){
        return 90;
    }

    public int getLargeur(){
        return 301;
    }

    public void toucheGauche(){
        leVaisseau.deplace(-5);
    }
    
    public void toucheDroite(){
        leVaisseau.deplace(5);
    }

    public void toucheEspace(){
        this.listeProj.add(new Projectile(leVaisseau.positionCanon(), 8));
    }

    public EnsembleChaines getChaines(){
        this.chaines = new EnsembleChaines();
        this.chaines.union(leVaisseau.getEnsembleChaines());
        for(Projectile proj: this.listeProj){
            this.chaines.union(proj.getEnsembleChaines());
        }
        for(Alien alien: aliens){
            this.chaines.union(alien.getEnsembleChaines());
        }
        return this.chaines;
    }

    public void jouerUnTour(){
        if(this.listeProj.size() > 0){
            for(Projectile projectileElem: this.listeProj){
                projectileElem.evolue();
            }
        }
        if(this.aliens.size() > 0){
            for(Alien alien: this.aliens){
                alien.evolue();
            }
        }
    }
}