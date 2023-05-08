import java.util.Random;

public class Alien {
    private double posX;
    private double posY;
    private EnsembleChaines LesChainesAlien;
    private String direction;
    private int numberSprite;

    /** Constructeur de la classe Alien
    @param X la position en X de l'alien
    @param Y la position en Y de l'alien
    */
    public Alien(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesAlien = new EnsembleChaines();
        this.direction = "droite";
        this.numberSprite = 0;
    }

    /** Constructeur de la classe Alien
    @param X la position en X de l'alien
    @param Y la position en Y de l'alien
    @param direction la direction dans laquelle l'alien va bouger
    */
    public Alien(double X, double Y, String direction){
        this.posX = X;
        this.posY = Y;
        this.LesChainesAlien = new EnsembleChaines();
        this.direction = direction;
        this.numberSprite = 0;
    }

    /** Fonction "getter" de posX 
    @return la position en X de l'alien
    */
    public double getX(){
        return this.posX;
    }

    /** Fonction qui incrémente posX par le paramètre X 
    @param X un nombre de combien X doit être incrémenté
    */
    public void addX(double X){
        this.posX += X;
    }

    /** Fonction "getter" de posY 
    @return la position en Y de l'alien
    */
    public double getY(){
        return this.posY;
    }

    /** Fonction "getter" de direction 
    @return la direction de l'alien
    */
    public String getDirection(){
        return this.direction;
    }

    /** Fonction "setter" de direction 
    @param direction la nouvelle direction de l'alien
    */
    public void setDirection(String direction){
        this.direction = direction;
    }

    /** Fonction "getter" du numéro du sprite de l'alien
    @return le numéro du sprite de l'alien 
    */
    public int getNumberSprite(){
        return this.numberSprite;
    }

    /** Fonction "getter" des chaines de l'alien
    @return les chaines de l'alien 
    */
    public EnsembleChaines getChaines(){
        return this.LesChainesAlien;
    }

    /** Fonction "setter" des chaines de l'alien
    @param lesChaines les nouvelles chaines de l'alien 
    */
    public void setChaines(EnsembleChaines lesChaines){
        this.LesChainesAlien = lesChaines;
    }

    /** Donne les chaines équivalente de l'alien selon son numéro de sprite associé
    @return les chaines de l'alien 
    */
    public EnsembleChaines getEnsembleChaines() {
        this.LesChainesAlien = new EnsembleChaines();
        if(this.numberSprite == 0){
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY  , "    ██          ██    ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-1, "      ██      ██      ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-2, "    ██████████████    ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-3, "  ████  ██████  ████  ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-4, "██████████████████████");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-5, "██  ██████████████  ██");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-6, "██  ██          ██  ██");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-7, "      ████  ████      ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-8, "                      ");
        }
        if(this.numberSprite == 1){
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY  , "  ████          ████  ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-1, "      ██      ██      ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-2, "    ██████████████    ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-3, "  ████  ██████  ████  ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-4, "██████████████████████");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-5, "██  ██████████████  ██");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-6, "██    ██      ██    ██");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-7, " ██  ██        ██  ██ ");
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-8, "  ████          ████  ");
        }
        return this. LesChainesAlien;
    }

    /** Fonction qui permet d'obtenir la position en X du canon de l'alien
    @return la position en X du canon de l'alien
    */
    public double positionCanonX(){
        Random obj = new Random();
        int nbr = obj.nextInt(3);
        while(nbr == 0){
            nbr = obj.nextInt(3);
        }
        if(nbr == 1){
            return this.getX() + 3;
        }
        else{
            return this.getX() + 17;
        }
    }

    /** Fonction qui permet d'obtenir la position en Y du canon de l'alien
    @return la position en Y du canon de l'alien
    */
    public double positionCanonY(){
        return this.getY() - 9;
    }

    /** Fonction qui passe l'attribut sprite à 1 SSI celui ci est à 0
    */
    public void cycleSprite0to1(){
        if(this.numberSprite == 0){
            this.numberSprite = 1;
        }
    }

    /** Fonction qui passe l'attribut sprite à 0 SSI celui ci est à 1
    */
    public void cycleSprite1to0(){
        if(this.numberSprite == 1){
            this.numberSprite = 0;
        }
    }

    /** Fonction qui permet de faire évoluer les attributs de l'alien
    @param multiplicateur la difficulté du jeu, qui sers ici à multiplier de combien l'alien sera déplacé
    @return un boolean associé au fait qu'un alien arrive à la hauteur du vaisseau, ce qui proccure la défaite
    */
    public boolean evolue(int multiplicateur){
        double decalage = 2;
        if(multiplicateur != 10){
            decalage = 2*(1 +(0.5*(multiplicateur - 1)));
        }
        if(this.posY == 15){
            return true;
        }
        else{
            if(direction == "droite"){
                if(this.posX + 21 <= 300 - decalage){
                    this.posX += decalage;
                }
                else{
                    this.posY -= 10;
                    this.direction = "gauche";
                }
            }
            else{
                if(direction == "gauche"){
                    if(this.posX > decalage){
                        this.posX -= decalage;
                    }
                    else{
                        this.posY -= 10;
                        this.direction = "droite";
                    }
                }
            }
        }
        return false;
    }
}