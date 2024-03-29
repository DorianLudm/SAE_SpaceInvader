import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GestionJeu{
    private EnsembleChaines chaines;
    private Vaisseau leVaisseau;
    private List<Projectile> listeProj;
    private List<ProjectileAlien> listeProjAlien;
    private List<Alien> aliens;
    private List<Boss> boss;    
    private int tourDeJeu;
    private float spawnCooldown;
    private boolean gameOver;
    private int statusVictoire;
    private int score;
    private float tempsDeSurvie;
    private float timer;
    private int multi;
    private int typeDerniereVague;
    private int nombreAlienTues;
    private int nombreProjTiré;
    private int tirDurantBoss;
    private int tirTouché;
    private int statut;
    private int shieldUse;

    /** Constructeur de la classe GestionJeu
    */
    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.leVaisseau = new Vaisseau();
        this.listeProj = new ArrayList<>();
        this.listeProjAlien = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.boss = new ArrayList<>();
        this.tourDeJeu = 0;
        this.spawnCooldown = 0;
        this.gameOver = false;
        this.statusVictoire = 0;
        this.score = 0;
        this.tempsDeSurvie = 0;
        this.timer = 0;
        this.multi = 1;
        this.typeDerniereVague = 0 ;
        this.nombreAlienTues = 0;
        this.nombreProjTiré = 0;
        this.tirDurantBoss = 0;
        this.tirTouché = 0;
        this.statut = 0;
        this.shieldUse = 0;
    }

    /** Fonction qui reinitialiser les attributs de GestionJeu
    */
    public void restart(){
        if(this.gameOver){
            this.chaines = new EnsembleChaines();
            this.leVaisseau = new Vaisseau();
            this.listeProj = new ArrayList<>();
            this.listeProjAlien = new ArrayList<>();
            this.aliens = new ArrayList<>();
            this.boss = new ArrayList<>();
            this.tourDeJeu = 0;
            this.spawnCooldown = 0;
            this.gameOver = false;
            this.statusVictoire = 0;
            this.score = 0;
            this.tempsDeSurvie = 0;
            this.timer = 0;
            this.multi = 1;
            this.typeDerniereVague = 0 ;
            this.nombreAlienTues = 0;
            this.nombreProjTiré = 0;
            this.tirDurantBoss = 0;
            this.tirTouché = 0;
            this.statut = 0;
            this.shieldUse = 0;
        }
    }

    /** Fonction qui retourne la hauteur de la fenêtre de jeu
    @return La hauteur de la fenêtre de jeu
    */
    public int getHauteur(){
        return 90;
    }

    /** Fonction qui retourne la largeur de la fenêtre de jeu
    @return La largeur de la fenêtre de jeu
    */
    public int getLargeur(){
        return 300;
    }

    /** Fonction gestionnaire utilisée lorsque la touche gauche est pressée, bouge le vaisseau sur la gauche
    */
    public void toucheGauche(){
        if(this.statut == 1){
            int decal = this.multi;
            if(decal == 10){
                decal = 1;
            }
            if(leVaisseau.getPosX() - (3*decal) >= 0)
                leVaisseau.deplace(-3, decal);
        }
    }

    /** Fonction gestionnaire utilisée lorsque la touche droite est pressée, bouge le vaisseau sur la droite
    */
    public void toucheDroite(){
        if(this.statut == 1){
            int decal = this.multi;
            if(decal == 10){
                decal = 1;
            }
            if(leVaisseau.getPosX() + (3*decal) < this.getLargeur() - 21)
                leVaisseau.deplace(3, decal);
        }
    }

    /** Fonction gestionnaire utilisée lorsque la touche espace est pressée, fait tirer un projectile
    */
    public void toucheEspace(){
        if(this.statut == 1){
            if(this.leVaisseau.getShootingCooldown() == 0){
                this.listeProj.add(new Projectile(leVaisseau.positionCanon(), 8));
                this.leVaisseau.setCooldown(20);
                this.nombreProjTiré += 1;
            }
        }
        if(this.statut == 0 || this.statut == -1){
            this.statut = 1;
            this.tourDeJeu = 0;
        }
        if(this.statut == 2 || this.statut == 3){
            this.statut = 1;
        }
    }

    /** Fonction gestionnaire utilisée lorsque la touche escape est pressée, met la partie en pause
    */
    public void toucheEscape(){
        if(this.statut == 1){
            this.statut = 2;
        }
    }

    /** Fonction gestionnaire utilisée lorsque la touche R est pressée, fait tirer un bouclier
    */
    public void toucheR(){
        if(this.multi == 10 && this.statut == 1 && this.leVaisseau.getShootingCooldown() == 0){
            this.score -= 1000;
            this.leVaisseau.setCooldown(150);
            this.shieldUse += 1;
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 19 , 6, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 17, 6, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 15, 6, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 15, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 13, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 11, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 9 , 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 7 , 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 6 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 4 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() - 2 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon()     , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 2 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 4 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 6 , 8, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 7 , 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 9 , 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 11, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 13, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 15, 7, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 15, 6, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 17, 6, true));
            this.listeProj.add(new Projectile(leVaisseau.positionCanon() + 19, 6, true));
        }
    }

    /** Fonction qui collecte l'ensemble des chaines des éléments du jeu et les regroupe pour pouvoir les afficher
    @return l'ensemble des chaines du jeux
    */
    public EnsembleChaines getChaines(){
        this.chaines = new EnsembleChaines();
        if(this.statut == 0 || this.statut == -1)
            this.chaines.union(this.getText());
        else{
            if(!this.gameOver){
                this.chaines.union(leVaisseau.getEnsembleChaines());
                for(Projectile proj: this.listeProj){
                    this.chaines.union(proj.getEnsembleChaines());
                }
                for(ProjectileAlien projAlien: this.listeProjAlien){
                    this.chaines.union(projAlien.getEnsembleChaines());
                }
                for(Alien alien: aliens){
                    this.chaines.union(alien.getEnsembleChaines());
                }
                for(Boss bossElem: boss){
                    this.chaines.union(bossElem.getEnsembleChaines());
                }
                this.chaines.union(this.getText());
            }
            else{
                this.chaines.union(getTextGameOver());
            }
        }
        return this.chaines;
    }

    /** Fonction qui fait apparaitre les aliens lorsque le jeu commence
    */
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

    /** Fonction qui fait apparaitre les aliens lorsque le jeu commence
    */
    public void incrementeMulti(){
        this.multi += 1;
    }

    /** Fonction qui permet de savoir si il reste un alien sur l'écran, utiliser pour passer à la difficulté suivante
    @return un boolean qui associe si il reste un alien sur l'écran
    */
    public boolean allClear(){
        return (this.aliens.isEmpty() && this.boss.isEmpty());
        
    }

    /** Fonction qui permet de faire apparaitre une nouvelle vague d'alien, différente de la vague précédente
    */
    public void summonAllien(){
        this.spawnCooldown = (float) 5 - (float) (0.8 * this.multi);
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

    /** Fonction qui fait apparaitre le boss, utiliser lorsque la 3ème vague est accomplie
    */
    public void spawnBoss(){
        this.boss.add(new Boss(this.getLargeur()/2 -  15, this.getHauteur()-15));
    }

    /** Fonction qui permet d'afficher un texte selon le stade du jeu
    @return l'ensemble des chaines des textes à affiché à l'écran
    */
    public EnsembleChaines getText(){
        EnsembleChaines text = new EnsembleChaines();
        boolean statutNoneAbove = true;
        if(this.statut == -1){
            statutNoneAbove = false;
            text.ajouteChaine(118,55," __                            _____                     _           ");
            text.ajouteChaine(118,54,"/ _\\_ __   __ _  ___ ___       \\_   \\_ ____   ____ _  __| | ___ _ __ ");
            text.ajouteChaine(118,53,"\\ \\| '_ \\ / _` |/ __/ _ \\       / /\\/ '_ \\ \\ / / _` |/ _` |/ _ \\ '__|");
            text.ajouteChaine(118,52,"_\\ \\ |_) | (_| | (_|  __/    /\\/ /_ | | | \\ V / (_| | (_| |  __/ |   ");
            text.ajouteChaine(118,51,"\\__/ .__/ \\__,_|\\___\\___|    \\____/ |_| |_|\\_/ \\__,_|\\__,_|\\___|_|   ");
            text.ajouteChaine(118,50,"   |_|                                                               ");
        }
        else{
            if(this.statut == 0){
                statutNoneAbove = false;
                text.ajouteChaine(105,20,"   ___                                  _                _                _             _   ");
                text.ajouteChaine(105,19,"  / _ \\_ __ ___  ___ ___      ___ _ __ | |_ ___ _ __    | |_ ___      ___| |_ __ _ _ __| |_             ");
                text.ajouteChaine(105,18," / /_)/ '__/ _ \\/ __/ __|    / _ \\ '_ \\| __/ _ \\ '__|   | __/ _ \\    / __| __/ _` | '__| __|");
                text.ajouteChaine(105,17,"/ ___/| | |  __/\\__ \\__ \\   |  __/ | | | ||  __/ |      | || (_) |   \\__ \\ || (_| | |  | |_ ");
                text.ajouteChaine(105,16,"\\/    |_|  \\___||___/___/    \\___|_| |_|\\__\\___|_|       \\__\\___/    |___/\\__\\__,_|_|   \\__|");
                text.ajouteChaine(118,55," __                            _____                     _           ");
                text.ajouteChaine(118,54,"/ _\\_ __   __ _  ___ ___       \\_   \\_ ____   ____ _  __| | ___ _ __ ");
                text.ajouteChaine(118,53,"\\ \\| '_ \\ / _` |/ __/ _ \\       / /\\/ '_ \\ \\ / / _` |/ _` |/ _ \\ '__|");
                text.ajouteChaine(118,52,"_\\ \\ |_) | (_| | (_|  __/    /\\/ /_ | | | \\ V / (_| | (_| |  __/ |   ");
                text.ajouteChaine(118,51,"\\__/ .__/ \\__,_|\\___\\___|    \\____/ |_| |_|\\_/ \\__,_|\\__,_|\\___|_|   ");
                text.ajouteChaine(118,50,"   |_|                                                               ");
            }
        }
        if(statutNoneAbove){
            int hauteur = this.getHauteur() - 1;
            String diff = String.valueOf(this.multi);
            if(this.multi == 10){
                diff = "UFO";
                text.ajouteChaine(25, hauteur - 2, "Point de vie de UFO");
                double accu = 0;
                if(this.tirDurantBoss != 0){
                    accu = this.tirTouché / this.tirDurantBoss * 100;
                }
                text.ajouteChaine(5, hauteur, ("Score: " + this.score + "   Temps de survie: " + (int) this.tempsDeSurvie) + "   Difficulté: " + diff + "   Précision: " + String.format("%.2f", accu) +"%"+ "   Nombre de bouclier utilisés: " + this.shieldUse);
                String chainePv = "";
                if(!this.boss.isEmpty()){
                    int pv = this.boss.get(0).getHp();
                    for(int i = 0; i < pv; i++){
                        chainePv += "█████";
                    } 
                    text.ajouteChaine(25, hauteur-4, chainePv);
                    text.ajouteChaine(25, hauteur-5, chainePv);
                }
            }
            else{
                text.ajouteChaine(5, hauteur, ("Score: " + this.score + "   Temps de survie: " + (int) this.tempsDeSurvie) + "   Difficulté: " + diff + "   Nombre d'ennemis tué: " + this.nombreAlienTues+ "   Nombre de projectile tiré: " + this.nombreProjTiré);
            }
        }
        if(this.statut == 2){
            text.ajouteChaine(125, 20, "Appuyer sur ESPACE pour reprendre votre partie!");
        }
        return text;
    }

    /** Fonction qui permet d'afficher les statistiques de la partie lorsque celle-ci est terminée
    @return l'ensemble des chaines des textes à affiché à l'écran
    */
    public EnsembleChaines getTextGameOver(){
        EnsembleChaines text = new EnsembleChaines();
        text.ajouteChaine(125,75,"   ___                         ___                  _ ");
        text.ajouteChaine(125,74,"  / _ \\__ _ _ __ ___   ___    /___\\__   _____ _ __ / \\");
        text.ajouteChaine(125,73," / /_\\/ _` | '_ ` _ \\ / _ \\  //  //\\ \\ / / _ \\ '__/  /");      
        text.ajouteChaine(125,72,"/ /_\\\\ (_| | | | | | |  __/ / \\_//  \\ V /  __/ | /\\_/ ");
        text.ajouteChaine(125,71,"\\____/\\__,_|_| |_| |_|\\___| \\___/    \\_/ \\___|_| \\/   ");
        if(this.statusVictoire == 1){
            text.ajouteChaine(130, 60, "                  __    __              _             ");
            text.ajouteChaine(130, 59, "/\\_/\\___  _   _  / / /\\ \\ \\___  _ __   / \\            ");
            text.ajouteChaine(130, 58, "\\_ _/ _ \\| | | | \\ \\/  \\/ / _ \\| '_ \\ /  /            ");
            text.ajouteChaine(130, 57, " / \\ (_) | |_| |  \\  /\\  / (_) | | | /\\_/             ");
            text.ajouteChaine(130, 56, " \\_/\\___/ \\__,_|   \\/  \\/ \\___/|_| |_\\/               ");
        }
        else{
            text.ajouteChaine(127, 60, "                    __                         _       ");
            text.ajouteChaine(127, 59, "/\\_/\\___  _   _    / /  ___   ___  ___  ___  / \\      ");
            text.ajouteChaine(127, 58, "\\_ _/ _ \\| | | |  / /  / _ \\ / _ \\/ __|/ _ \\/  /      ");
            text.ajouteChaine(127, 57, " / \\ (_) | |_| | / /__| (_) | (_) \\__ \\  __/\\_/       ");
            text.ajouteChaine(127, 56, " \\_/\\___/ \\__,_| \\____/\\___/ \\___/|___/\\___\\/         ");
        }
        text.ajouteChaine(130, 50, "Statistiques de la partie:");
        text.ajouteChaine(130, 49, "-------------------------------");
        text.ajouteChaine(130, 48, "Score: " + this.score);
        if(this.multi == 10){
            double accu = 0;
            if(this.tirDurantBoss != 0){
                accu = this.tirTouché / this.tirDurantBoss * 100;
            }
            text.ajouteChaine(130, 47, "Vague atteinte: BOSS");
            text.ajouteChaine(130, 43, "Nombre de projectiles tirés lors du boss: " + this.tirDurantBoss);
            text.ajouteChaine(130, 42, "Précision lors du BOSS: " + String.format("%.2f", accu) +"%");
            text.ajouteChaine(130, 41, "Nombre de boucliers utilisés: " + this.shieldUse);
        }
        else{
            text.ajouteChaine(130, 47, "Vague atteinte: " + this.multi);
        }
        text.ajouteChaine(130, 46, "Nombre d'aliens tués: " + this.nombreAlienTues);
        int nbTirTotal = this.nombreProjTiré + this.tirDurantBoss;
        text.ajouteChaine(130, 45, "Nombre de projectiles tirés: " + nbTirTotal);
        double accu2 = 0;
        if(this.nombreProjTiré != 0){
            accu2 = (double) this.nombreAlienTues / (double) this.nombreProjTiré * 100; 
        }
        text.ajouteChaine(130, 44, "Précision lors des trois premières vagues " + String.format("%.2f", accu2) +"%");
        if(this.statut == 1){
            this.statut = 3;
        }
        if(this.statut == 3){
            this.statut = 4;
            text.ajouteChaine(130 , 36, "Appuyer sur A pour relancer une partie! ");
        }
        if(this.statut == 4){
            this.statut = 3;
        }
        return text;
    }

    /** Fonction qui permet de modifier l'ensemble des attributs de chaque élément, elle permet au final de faire jouer le jeu
    */
    public void jouerUnTour(){
        if(this.statut == 1 && !this.gameOver){
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
            //Calcul de la précision 
            double accu = 0;
            if(this.nombreProjTiré != 0){
                accu = (double) this.nombreAlienTues / (double) this.nombreProjTiré;
            }
            //Augmentation de la difficulté si les critères sont remplis
            if(this.timer > 25 && this.allClear() && this.multi != 10){
                this.score += this.multi*10000*accu;
                if(this.multi <= 3){
                    this.incrementeMulti();
                    this.timer = 0;
                }
                if(this.multi == 3){
                    this.multi = 10;
                    this.timer = 0;
                }
            }
            //Génération des aliens
            if(!gameOver && this.timer < 30 && this.multi <= 3){
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
            //Suppression des projectiles (du vaisseau) hors fenêtre et évolution des projectiles
            List<Projectile> listeProjectilesSupp = new ArrayList<>();
            if(this.listeProj.size() > 0){
                for(Projectile projectileElem: this.listeProj){
                    projectileElem.evolue(this.multi);
                    if(projectileElem.getY() > this.getHauteur()){
                        listeProjectilesSupp.add(projectileElem);
                        if(!projectileElem.isShield()){
                            if(this.multi == 10){
                                this.tirDurantBoss += 1;
                            }
                        }
                    }
                }
            }
            //Suppression des projectiles (aliens) hors fenêtre et évolution des projectiles
            List<ProjectileAlien> listeProjectilesAlienSupp = new ArrayList<>();
            if(this.listeProjAlien.size() > 0){
                for(ProjectileAlien projectileElem: this.listeProjAlien){
                    projectileElem.evolue(this.multi);
                    if(projectileElem.getY() < 0){
                        listeProjectilesAlienSupp.add(projectileElem);
                    }
                }
            }
            //Evolution des aliens
            if(this.aliens.size() > 0){
                boolean divisiblePar25 = false;
                if(this.tourDeJeu%25 == 0){
                    divisiblePar25 = true;
                }
                boolean divisiblePar75 = false;
                if(this.tourDeJeu%75 == 0){
                    divisiblePar75 = true;
                }
                boolean gameOverParAlien = false;
                for(Alien alien: this.aliens){
                    if(alien.evolue(this.multi)){
                        gameOverParAlien = true;
                    }
                    if(alien.getNumberSprite() == 1 && divisiblePar25){
                        alien.cycleSprite1to0();
                    }
                    if(alien.getNumberSprite() == 0 && divisiblePar75){
                        alien.cycleSprite0to1();
                    }
                }
                if(gameOverParAlien){
                    this.gameOver = true;
                    this.statusVictoire = -1;
                    if(this.multi == 10){
                        this.tirDurantBoss += this.listeProj.size();
                    }
                    else{
                        this.nombreProjTiré += this.listeProj.size();
                    }
                }
            }
            //Evolution des boss
            if(this.boss.size() > 0){
                for(Boss bossElem: boss){
                    bossElem.evolue();
                }
            }
            //Génération des missiles ennemis
            if(!aliens.isEmpty()){
                if(aliens.get(0).getNumberSprite() == 1){
                    Random obj = new Random();
                    for(Alien alienElem: aliens){
                        int nbr = obj.nextInt(100);
                        if(nbr > 99 - 1*this.multi){
                            this.listeProjAlien.add(new ProjectileAlien(alienElem.positionCanonX(), alienElem.positionCanonY()));
                        }
                    }
                }
            }
            //Génération des missiles du boss
            if(this.multi == 10){
                Random obj = new Random();
                for(int i = 0; i < 5; i++){
                    for(Boss bossElem: boss){
                        int nbr = obj.nextInt(100);
                        if(nbr > 85){
                            this.listeProjAlien.add(new ProjectileAlien(bossElem.positionCanonX(), bossElem.positionCanonY()));
                        }
                        nbr = obj.nextInt(100);
                        Random obj1 = new Random();
                        if(nbr > 95){
                            this.listeProjAlien.add(new ProjectileAlien(obj1.nextInt(this.getLargeur()), this.getHauteur(), 1));
                            this.listeProjAlien.add(new ProjectileAlien(obj1.nextInt(this.getLargeur()), this.getHauteur(), 1));
                        }
                    }
                }
            }
            //Collisions
            //Génération des listes qui vont contenir les éléments à supprimé
            List<Alien> listeAlienSupp = new ArrayList<>();
            List<Boss> listeBossSupp = new ArrayList<>();
            //Recherche de collisions sur chaque projectile
            for(Projectile projectileElement: this.listeProj){
                if(!projectileElement.isShield()){
                    //Collisions avec les aliens
                    for(Alien alienElement: this.aliens){
                        if(alienElement instanceof Alien){
                            //Check que le projectile soit dans l'alien au niveau des X
                            if(projectileElement.getX() + 1 >= alienElement.getX() && projectileElement.getX() <= alienElement.getX() + 21){
                                //Check que le projectile soit dans l'alien au niveau des Y
                                if(projectileElement.getY() <= alienElement.getY() && projectileElement.getY() >= alienElement.getY() - 8){
                                    listeProjectilesSupp.add(projectileElement);
                                    listeAlienSupp.add(alienElement);
                                    this.nombreAlienTues += listeAlienSupp.size();
                                }
                            }
                        }
                    }
                    //Collisions avec le boss
                    for(Boss bossElem: boss){
                        //Check que le projectile soit dans l'alien au niveau des X
                        if(projectileElement.getX() + 1 >= bossElem.getX() && projectileElement.getX() <= bossElem.getX() + 59){
                            //Check que le projectile soit dans l'alien au niveau des Y
                            if(projectileElement.getY() <= bossElem.getY() && projectileElement.getY() >= bossElem.getY() - 7){
                                listeProjectilesSupp.add(projectileElement);
                                this.tirTouché += 1;
                                this.tirDurantBoss += 1;
                                this.score += 50*listeProjectilesSupp.size()*this.multi;
                                if(bossElem.removeHp()){
                                    listeBossSupp.add(bossElem);
                                    this.nombreAlienTues += 1;
                                }
                            }
                        }
                    }
                }
            }
            //Collisions entre les projectiles alien et le vaisseau
            for(ProjectileAlien projectileAlienElem: listeProjAlien){
                 //Check que le projectile soit dans l'alien au niveau des X
                if(projectileAlienElem.getX() + 1 >= leVaisseau.getPosX() && projectileAlienElem.getX() <= leVaisseau.getPosX() + 21){
                    //Check que le projectile soit dans l'alien au niveau des Y
                    if(projectileAlienElem.getY() <= 8 && projectileAlienElem.getY() >= 0){
                        this.gameOver = true;
                        this.statusVictoire = -1;
                    }
                }
                //Collisions avec les projectiles protecteurs 
                for(Projectile projectileElem: this.listeProj){
                    if(projectileElem.isShield()){
                        if(projectileAlienElem.getY() > projectileElem.getY() - 2 && projectileAlienElem.getY() <= projectileElem.getY() + 2 ){
                            if(projectileAlienElem.getX() == projectileElem.getX() || projectileAlienElem.getX() == projectileElem.getX() + 1 || projectileAlienElem.getX() + 1 == projectileElem.getX()){
                                listeProjectilesAlienSupp.add(projectileAlienElem);
                            }
                        }
                    }
                    //Collisions avec les projectiles de base
                    else{
                        if(projectileAlienElem.getSrite() == 1){
                            if(projectileAlienElem.getY() == projectileElem.getY() || projectileAlienElem.getY() == projectileElem.getY() + 1 || projectileAlienElem.getY() + 1 == projectileElem.getY()){
                                if(projectileAlienElem.getX() + 1 == projectileElem.getX() || projectileAlienElem.getX() == projectileElem.getX() || projectileAlienElem.getX() == projectileElem.getX() + 1){
                                    listeProjectilesAlienSupp.add(projectileAlienElem);
                                    listeProjectilesSupp.add(projectileElem); 
                                    this.score += 150; 
                                }  
                            }
                        }
                    }
                }
            }
            //Supression des elements en collision et ajout des points pour chaque alien tué
            if (!listeAlienSupp.isEmpty()){
                this.score += 1000*listeAlienSupp.size()*this.multi*accu;
                this.aliens.removeAll(listeAlienSupp);
            }
            if (!listeProjectilesSupp.isEmpty()){
                this.listeProj.removeAll(listeProjectilesSupp);
            }
            if (!listeBossSupp.isEmpty()){
                this.boss.removeAll(listeBossSupp);
                this.score += 10000*listeAlienSupp.size()*this.multi;
            }
            if (!listeProjectilesAlienSupp.isEmpty()){
                this.listeProjAlien.removeAll(listeProjectilesAlienSupp);
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
            if(leVaisseau.getShootingCooldown() > 0){
                this.leVaisseau.incrementeCooldown(1*(1+this.multi/3));
            }
            if(leVaisseau.getShootingCooldown() < 0){
                this.leVaisseau.setCooldown(0);
            }
            //Gestion de l'interface si il y a un GameOver
            if(gameOver){
                System.out.println(this.statusVictoire);
                this.listeProj = new ArrayList<>(); 
                this.aliens = new ArrayList<>();
                if(this.statusVictoire == 1){
                }
                if(this.statusVictoire == -1){
                }
            }
        }
        else{
            this.tourDeJeu += 1;
            boolean divisiblePar10 = false;
            if(this.tourDeJeu%10 == 0){
                divisiblePar10 = true;
            }
            if(divisiblePar10){
                if(this.statut == 0){
                    this.statut = -1;
                }
                else{
                    if(this.statut == -1){
                        this.statut = 0;
                    }
                }
                if(this.statut == 2){
                    this.statut = 3;
                }
                else{
                    if(this.statut == 3){
                        this.statut = 2;
                    }
                }
            }
        }
    }
}