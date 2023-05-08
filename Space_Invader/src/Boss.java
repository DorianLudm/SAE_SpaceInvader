import java.util.Random;

public class Boss{
    private double posX;
    private double posY;
    private EnsembleChaines LesChainesBoss;
    private String direction;
    private int hp;

    /** Constructeur de la classe Boss
    @param X la position en X du Boss
    @param Y la position en Y du Boss
    */
    public Boss(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesBoss = new EnsembleChaines();
        this.direction = "droite";
        this.hp = 50;
    }

    /** Fonction "getter" de posX 
    @return la position en X du boss
    */
    public double getX(){
        return this.posX;
    }

    /** Fonction "getter" de posY 
    @return la position en Y du boss
    */
    public double getY(){
        return posY;
    }

    /** Fonction "getter" des points de vie du boss 
    @return la nombre de points de vie du boss 
    */
    public int getHp(){
        return this.hp;
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

    /** Fonction qui enlève un point de vie au boss
    @return Un booléen qui associe si le boss est mort (HP = 0)
    */
    public boolean removeHp(){
        boolean result = false;
        if(this.getHp() == 1){
            result = true;
        }
        if(this.getHp() > 1){
            this.hp -= 1;
        }
        return result;
    } 

    /** Fonction qui permet d'obtenir la position en X du canon du boss
    @return la position en X du canon du boss
    */
    public double positionCanonX(){
        Random obj = new Random();
        int nbr = obj.nextInt(3);
        nbr += 1;
        if(nbr == 1){
            return this.getX() + 11;
        }
        if(nbr == 2){
            return this.getX() + 29;
        }
        else{
            return this.getX() + 47;
        }
    }

    /** Fonction qui permet d'obtenir la position en Y du canon du boss
    @return la position en Y du canon du boss
    */
    public double positionCanonY(){
        return this.getY() - 8;
    }

    /** Donne les chaines équivalente associés au boss
    @return les chaines du boss
    */
    public EnsembleChaines getEnsembleChaines() {
        this.LesChainesBoss = new EnsembleChaines();
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()  , "              ▄▄▄▄▄▄▄▄████████████████▄▄▄▄▄▄▄▄              ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-1, "      ▄▄████████████████████████████████████████████▄▄      ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-2, "   ▄██████▀▀▀▀████████▀▀▀▀████████▀▀▀▀████████▀▀▀▀██████▄   ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-3, " ▄████████    ████████    ████████    ████████    ████████▄ ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-4, "██████████▄▄▄▄████████▄▄▄▄████████▄▄▄▄████████▄▄▄▄██████████");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-5, " ▀▀▀▀██████████████▀▀▀▀▀▀██████████▀▀▀▀▀▀██████████████▀▀▀▀ ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-6, "       ██████████         ████████         ██████████       ");
        this.LesChainesBoss.ajouteChaine((int) getX(), (int) getY()-7, "        ▀▀████▀▀          ▀▀▀▀▀▀▀▀          ▀▀████▀▀        ");
        return this.LesChainesBoss;
    }
    /** Fonction qui permet de faire évoluer les attributs du boss
    */
    public void evolue(){
        if(this.getDirection() == "droite"){
            if(this.getX() + 59 <= 297){
                this.posX += 3;
            }
            else{
                this.setDirection("gauche");
            }
        }
        else{
            if(this.getDirection() == "gauche"){
                if(this.getX() > 3){
                    this.posX -= 3;
                }
                else{
                    this.setDirection("droite");
                }
            }
        }
    }
}