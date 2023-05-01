import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GestionJeu{
    private EnsembleChaines chaines;
    private Vaisseau leVaisseau;
    private List<Projectile> listeProj;
    private List<Alien> aliens;
    private List<Boss> boss;
    private int tourDeJeu;
    private int shootingCooldown;
    private float spawnCooldown;
    private boolean gameOver;
    private int statusVictoire;
    private int score;
    private float tempsDeSurvie;
    private float timer;
    private int multi;
    private int typeDerniereVague;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.leVaisseau = new Vaisseau();
        this.listeProj = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.boss = new ArrayList<>();
        this.tourDeJeu = 0;
        this.shootingCooldown = 0;
        this.spawnCooldown = 0;
        this.gameOver = false;
        this.statusVictoire = 0;
        this.score = 0;
        this.tempsDeSurvie = 0;
        this.timer = 0;
        this.multi = 3;
        this.typeDerniereVague = 0;
    }

    public int getHauteur(){
        return 90;
    }

    public int getLargeur(){
        return 300;
    }

    public void toucheGauche(){
        if(leVaisseau.getPosX() - (5*this.multi) >= 0)
            leVaisseau.deplace(-5, this.multi);
    }
    
    public void toucheDroite(){
        if(leVaisseau.getPosX() + (5*this.multi) < this.getLargeur() - 21)
            leVaisseau.deplace(5, this.multi);
    }

    public void toucheEspace(){
        if(shootingCooldown == 0 && !this.gameOver){
            this.listeProj.add(new Projectile(leVaisseau.positionCanon(), 8));
            shootingCooldown = 20;
        }
    }

    public EnsembleChaines getChaines(){
        this.chaines = new EnsembleChaines();
        if(!this.gameOver)
            this.chaines.union(leVaisseau.getEnsembleChaines());
            for(Projectile proj: this.listeProj){
                this.chaines.union(proj.getEnsembleChaines());
            }
            for(Alien alien: aliens){
                this.chaines.union(alien.getEnsembleChaines());
            }
            for(Boss bossElem: boss){
                this.chaines.union(bossElem.getEnsembleChaines());
            }
        return this.chaines;
    }

    public void initialisationDuJeu(){
        if(this.multi != 10){
            this.aliens.add(new Alien(this.getLargeur()/2 - 10, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 + 26 - 10, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 + 52 - 10, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 - 26 - 10, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 - 52 - 10, this.getHauteur()-5));

            this.aliens.add(new Alien(this.getLargeur()/2-10, this.getHauteur()-15,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 + 26 - 10, this.getHauteur()-15,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 + 52 - 10, this.getHauteur()-15,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 26 - 10, this.getHauteur()-15,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 52 - 10, this.getHauteur()-15,"gauche"));
        }
    }

    public void incrementeMulti(){
        this.multi += 1;
    }

    public boolean allClear(){
        return (this.aliens.isEmpty() && this.boss.isEmpty());
        
    }

    public void summonAllien(){
        this.spawnCooldown = (float) 5 - (float) (0.5 * this.multi);
        int nouvelleVague = 0;
        Random rand = new Random();
        List<Integer> nouvelleVaguePossible = new ArrayList<>();
        if(typeDerniereVague == 0){
            nouvelleVaguePossible.add(1);
            nouvelleVaguePossible.add(2);
            nouvelleVaguePossible.add(3);
        }
        if(typeDerniereVague == 1){
            nouvelleVaguePossible.add(2);
            nouvelleVaguePossible.add(3);
        }
        if(typeDerniereVague == 2){
            nouvelleVaguePossible.add(1);
            nouvelleVaguePossible.add(3);
        }
        if(typeDerniereVague == 3){
            nouvelleVaguePossible.add(1);
            nouvelleVaguePossible.add(2);
        }
        nouvelleVague = nouvelleVaguePossible.get(rand.nextInt(nouvelleVaguePossible.size()));
        if(nouvelleVague == 1){
            this.aliens.add(new Alien(this.getLargeur()/2 + 25 - 10 + 1*this.multi, this.getHauteur()-5,"droite"));
            this.aliens.add(new Alien(this.getLargeur()/2 + 50 - 10 + 2*this.multi, this.getHauteur()-5,"droite"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 25 - 10 - 1*this.multi, this.getHauteur()-5,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 50 - 10 - 2*this.multi, this.getHauteur()-5,"gauche"));
            this.typeDerniereVague = 1;
        }
        if(nouvelleVague == 2){
            this.aliens.add(new Alien(this.getLargeur()/2 - 10, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 + 25 - 10 + 1*this.multi, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 + 50 - 10 + 2*this.multi, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 - 25 - 10 - 1*this.multi, this.getHauteur()-5));
            this.aliens.add(new Alien(this.getLargeur()/2 - 50 - 10 - 2*this.multi, this.getHauteur()-5));
            this.typeDerniereVague = 2;
        }
        if(nouvelleVague == 3){
            this.aliens.add(new Alien(this.getLargeur()/2 -  10, this.getHauteur()-5,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 + 25 - 10 + 1*this.multi, this.getHauteur()-5,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 + 50 - 10 + 2*this.multi, this.getHauteur()-5,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 25 - 10 - 1*this.multi, this.getHauteur()-5,"gauche"));
            this.aliens.add(new Alien(this.getLargeur()/2 - 50 - 10 - 2*this.multi, this.getHauteur()-5,"gauche"));
            this.typeDerniereVague = 3;
        }
    }

    public void spawnBoss(){
        this.boss.add(new Boss(this.getLargeur()/2 -  15, this.getHauteur()-5));
    }

    public void jouerUnTour(){
        //Initialisation du jeu
        if(this.tourDeJeu == 0){
            this.initialisationDuJeu();
        }
        //Remise à 0 du tour de jeu (éviter de rendre infini l'attribut "tourDeJeu")
        //1500 est choisi car il faut que ce chiffre soit divisible par 5 et 30
        //pour que les animations puissent ce faire sans décalage lors de la remise à 0 de l'attribut
        if(this.tourDeJeu > 1500){
            this.tourDeJeu -= 1500;
        }
        //Augmentation de la difficulté si les critères sont remplis
        if(this.timer > 55 && this.allClear() && this.multi != 10){
            if(this.multi < 3){
                this.incrementeMulti();
                this.timer = 0;
            }
            if(this.multi == 3){
                this.multi = 10;
                this.timer = 0;
            }
        }
        //Génération des aliens
        if(!gameOver && this.timer < 50 && this.multi < 6){
            boolean alienSommetEcran = false;
            for(Alien alien: this.aliens){
                if(alien.getY() == 85){
                    alienSommetEcran = true;
                }
            }
            if(!alienSommetEcran && this.spawnCooldown == 0){
                this.summonAllien();
            }
        }
        if(!gameOver && this.timer < 0.02 && this.multi == 10){
            this.spawnBoss();
        }
        //Victoire si le boss est vaincu
        if(this.allClear() && this.multi == 10){
            this.gameOver = true;
            this.statusVictoire = 1;
        }
        //Suppression des projectiles hors fenêtre et évolution des projectiles
        List<Projectile> listeProjectilesSupp = new ArrayList<>();
        if(this.listeProj.size() > 0){
            for(Projectile projectileElem: this.listeProj){
                projectileElem.evolue(this.multi);
                if(projectileElem.getY() > this.getHauteur()){
                    listeProjectilesSupp.add(projectileElem);
                }
            }
        }
        //Evolution des aliens
        if(this.aliens.size() > 0){
            boolean divisiblePar5 = false;
            if(this.tourDeJeu%5 == 0){
                divisiblePar5 = true;
            }
            boolean divisiblePar30 = false;
            if(this.tourDeJeu%30 == 0){
                divisiblePar30 = true;
            }
            boolean gameOverParAlien = false;
            for(Alien alien: this.aliens){
                if(alien.evolue(this.multi)){
                    gameOverParAlien = true;
                }
                if(alien.getNumberSprite() == 0 && divisiblePar30 || alien.getNumberSprite() == 1 && divisiblePar5){
                    alien.cycleSprite();
                }
            }
            if(gameOverParAlien){
                this.gameOver = true;
                this.statusVictoire = -1;
            }
        }
        //Evolution des boss
        if(this.boss.size() > 0){
            for(Boss bossElem: boss){
                bossElem.evolue();
            }
        }
        //Génération des missiles ennemis

        //Collisions
        List<Alien> listeAlienSupp = new ArrayList<>();
        for(Projectile projectileElement: this.listeProj){
            for(Alien alienElement: this.aliens){
                if(alienElement instanceof Alien){
                    //Check que le projectile soit dans l'alien au niveau des X
                    if(projectileElement.getX() + 1 >= alienElement.getX() && projectileElement.getX() <= alienElement.getX() + 21){
                        //Check que le projectile soit dans l'alien au niveau des Y
                        if(projectileElement.getY() <= alienElement.getY() && projectileElement.getY() >= alienElement.getY() - 8){
                            listeProjectilesSupp.add(projectileElement);
                            listeAlienSupp.add(alienElement);
                        }
                    }
                }
            }
        }
        List<Boss> listeBossSupp = new ArrayList<>();
        for(Projectile projectileElement: this.listeProj){
            for(Boss bossElem: boss){
                //Check que le projectile soit dans l'alien au niveau des X
                if(projectileElement.getX() + 1 >= bossElem.getX() && projectileElement.getX() <= bossElem.getX() + 52){
                    //Check que le projectile soit dans l'alien au niveau des Y
                    if(projectileElement.getY() <= bossElem.getY() && projectileElement.getY() >= bossElem.getY() - 7){
                        listeProjectilesSupp.add(projectileElement);
                        if(bossElem.removeHp()){
                        listeBossSupp.add(bossElem);
                        }
                    }
                }
            }
        }
        //Supression des elements en collision et ajout des points pour chaque alien tué
        if (!listeAlienSupp.isEmpty()){
            this.score += 1000*listeAlienSupp.size()*this.multi;
            this.aliens.removeAll(listeAlienSupp);
        }
        if (!listeProjectilesSupp.isEmpty()){
            this.listeProj.removeAll(listeProjectilesSupp);
        }
        if (!listeBossSupp.isEmpty()){
            this.boss.removeAll(listeBossSupp);
        }
        //Incrémentation des différents attributs
        this.tourDeJeu += 1;
        this.tempsDeSurvie += 0.025;
        this.timer += 0.025;
        //Gestion du cooldown de l'apparition des ennemis
        if(spawnCooldown > 0){
            spawnCooldown -= 0.025;
        }
        if(spawnCooldown < 0){
            this.spawnCooldown = 0;
        }
        //Gestion du cooldown du tir du vaisseau
        if(shootingCooldown > 0){
            shootingCooldown -= 1*(1+this.multi/3);
        }
        if(shootingCooldown < 0){
            this.shootingCooldown = 0;
        }
        //Gestion de l'interface si il y a un GameOver
        if(gameOver){
            this.listeProj = new ArrayList<>();
            this.aliens = new ArrayList<>();
            if(this.statusVictoire == 1){
                System.out.println("You won");
            }
            if(this.statusVictoire == -1){
                System.out.println("You loose");
            }
        }
    }
}