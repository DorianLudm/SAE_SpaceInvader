public class Vaisseau {
    private double posX;
    private EnsembleChaines LesChaines;
    private int shootingCooldown;
    
    /** Constructeur de la classe Vaisseau
    */
    public Vaisseau(){
        this.posX = 140;
        this.shootingCooldown = 0;
        this.LesChaines = new EnsembleChaines();
        LesChaines.ajouteChaine(this.posX, 8, "         █▄▄█         ");
        LesChaines.ajouteChaine(this.posX, 7, "  ▄████▄▄████▄▄████▄  ");
        LesChaines.ajouteChaine(this.posX, 6, " ████████████████████ ");
        LesChaines.ajouteChaine(this.posX, 5, "  █▀██████████████▀█  ");
        LesChaines.ajouteChaine(this.posX, 4, "   █ ▄███▀▀▀▀███▄ █   ");
        LesChaines.ajouteChaine(this.posX, 3, "  ▄█ ▀███▄▄▄▄███▀ █▄  ");
        LesChaines.ajouteChaine(this.posX, 2, " ▄██████████████████▄ ");
        LesChaines.ajouteChaine(this.posX, 1, "▄███▀▀██████████▀▀███▄");
        LesChaines.ajouteChaine(this.posX, 0, "▀██      ▀▀▀▀      ██▀");
    }

    /**Fonction qui permet de modifier les attributs de vaisseau selon les paramètres
    @param dx un nombre de combien la position X va être modifier
    @param multi la difficulté du jeu, qui sers ici à multiplier de combien le vaisseau sera déplacé
    */
    public void deplace(double dx, int multi){
        if(multi == 10){
            multi = 1;
        }
        this.posX += dx*multi;
        for(ChainePositionnee chaineElem: LesChaines.chaines){
            chaineElem.x = this.posX;
        }
    }

    /**Fonction qui permet de réduire le cooldown de tir du vaisseau
    @param Nombre un nombre de combien le cooldown va être réduit
    */
    public void incrementeCooldown(int Nombre){
        this.shootingCooldown -= Nombre;
        if(this.shootingCooldown < 0){
            this.shootingCooldown = 0;
        }
    }

    /**Fonction "setter" du cooldown de tir du vaisseau
    @param Nombre le nouveau cooldown de tir du vaisseau
    */
    public void setCooldown(int Nombre){
        this.shootingCooldown = Nombre;
    }

    /**Fonction "getter" du cooldown de tir du vaisseau
    @return Le nouveau cooldown de tir du vaisseau
    */
    public int getShootingCooldown(){
        return this.shootingCooldown;
    }

    /** Fonction "getter" des chaines du Vaisseau
    @return les chaines du Vaisseau
    */
    public EnsembleChaines getEnsembleChaines(){
        return this.LesChaines;
    }

    /** Fonction "getter" de posX 
    @return la position en X du Vaisseau
    */
    public double getPosX(){
        return this.posX;
    }

    /** Fonction "setter" de la position en X du Vaisseau
    @param newPosX la nouvelle position en X du Vaisseau
    */
    public void setPosX(double newPosX){
        this.posX = newPosX;
    }

     /** Fonction qui permet d'obtenir la position en X du canon du Vaisseau
    @return la position en X du canon du Vaisseau
    */
    public double positionCanon(){
        return (this.posX + 10);
    }
}