public class Projectile {
    protected double posX;
    protected double posY;
    protected boolean shield;
    protected EnsembleChaines LesChainesProj;

    /** Constructeur de la classe Projectile
    @param X la position en X du projectile
    @param Y la position en Y du projectile
    */
    public Projectile(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.shield = false;
        this.LesChainesProj = new EnsembleChaines();
    }

    /** Constructeur de la classe Projectile
    @param X la position en X du projectile
    @param Y la position en Y du projectile
    @param shield booléen qui défini si le projectile est un bouclier ou non
    */
    public Projectile(double X, double Y, boolean shield){
        this.posX = X;
        this.posY = Y;
        this.shield = true;
        this.LesChainesProj = new EnsembleChaines();
    }

    /** Fonction "getter" de l'attribut isShield
    @return boolean qui associe si le projectile est un bouclier ou non
    */
    public boolean isShield(){
        return this.shield;
    }
    
    /** Fonction "getter" de posX 
    @return la position en X du projectile
    */
    public double getX(){
        return this.posX;
    }

    /** Fonction "getter" de posY 
    @return la position en Y du projectile
    */
    public double getY(){
        return this.posY;
    }

    /** Donne les chaines équivalente du projectile
    @return les chaines du projectile
    */
    public EnsembleChaines getEnsembleChaines() {
        this.LesChainesProj = new EnsembleChaines();
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY, "██");
        return this.LesChainesProj;
    }

    /** Fonction qui permet de faire évoluer les attributs du projectile
    @param multiplicateur la difficulté du jeu, qui sers ici à multiplier de combien le projectile sera déplacé
    */
    public void evolue(int multi){
        if(multi != 10){
            this.posY += 1*multi;
        }
        else{
            if(this.shield){
                this.posY += 1;
            }
            this.posY += 1;
        }
    }
}