import java.util.Random;

public class ProjectileAlien extends Projectile{
    private int cooldown;
    private int sprite;

    /** Constructeur de la classe ProjectileAlien
    @param X la position en X du projectileAlien
    @param Y la position en Y du projectileAlien
    */
    public ProjectileAlien(double X, double Y){
        super(X,Y);
        this.cooldown = 5;
        this.sprite = 0;
    }

    /** Constructeur de la classe ProjectileAlien
    @param X la position en X du projectileAlien
    @param Y la position en Y du projectileAlien
    @param Sprite le numéro du Sprite du projectileAlien
    */
    public ProjectileAlien(double X, double Y, int Sprite){
        super(X,Y);
        this.cooldown = 5;
        this.sprite = Sprite;
    }

    /** Fonction "getter" de posX 
    @return la position en X du projectileAlien
    */
    public double getX(){
        return super.getX();
    }

    /** Fonction "getter" de posY 
    @return la position en Y du projectileAlien
    */
    public double getY(){
        return super.getY();
    }

    /** Fonction "getter" du numéro de sprite
    @return le numérodu sprite du projectileAlien
    */
    public int getSrite(){
        return this.sprite;
    }
    
    /** Donne les chaines équivalente du projectileAlien
    @return les chaines du projectileAlien
    */
    @Override
    public EnsembleChaines getEnsembleChaines() {
        super.LesChainesProj = new EnsembleChaines();
        if(this.sprite == 1){
            super.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY +1, "██");
            super.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY, "██");
        }
        else{
            super.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY +1, "██");
            super.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY, "▼▼");
        }
        return super.LesChainesProj;
    }

    /** Fonction qui permet de faire évoluer les attributs du projectileAlien
    @param multiplicateur la difficulté du jeu, qui sers ici à multiplier de combien le projectile sera déplacé
    */
    @Override
    public void evolue(int multi){
        if(multi != 10){
            this.posY -= 1*multi;
        }
        else{ 
            this.posY -= this.sprite + 1;
            if(this.cooldown == 0 && this.sprite == 0){
                Random obj = new Random();
                int nbr = obj.nextInt(2);
                nbr += 1;
                Random obj2 = new Random();
                int decal = obj2.nextInt(3);
                decal += 1;
                if(nbr == 1){
                    this.posX -= decal;
                }
                if(nbr == 2){
                    this.posX += decal;
                }
                this.cooldown = 5;
            }
            else{
                this.cooldown -= 1;
            }
        }
    }
}