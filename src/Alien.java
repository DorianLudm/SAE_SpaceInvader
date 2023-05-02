public class Alien {
    private double posX;
    private double posY;
    private EnsembleChaines LesChainesAlien;
    private String direction;
    private int numberSprite;

    public Alien(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesAlien = new EnsembleChaines();
        this.direction = "droite";
        this.numberSprite = 0;
    }

    public Alien(double X, double Y, String direction){
        this.posX = X;
        this.posY = Y;
        this.LesChainesAlien = new EnsembleChaines();
        this.direction = direction;
        this.numberSprite = 0;
    }

    public double getX(){
        return this.posX;
    }

    public void addX(double X){
        this.posX += X;
    }

    public double getY(){
        return this.posY;
    }

    public String getDirection(){
        return this.direction;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public int getNumberSprite(){
        return this.numberSprite;
    }

    public EnsembleChaines getChaines(){
        return this.LesChainesAlien;
    }

    public void setChaines(EnsembleChaines lesChaines){
        this.LesChainesAlien = lesChaines;
    }

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
            this. LesChainesAlien.ajouteChaine((int) this.posX, (int) this.posY-1, "██    ██      ██    ██");
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

    public void cycleSprite(){
        if(this.numberSprite == 0){
            this.numberSprite = 1;
        }
        if(this.numberSprite == 1){
            this.numberSprite = 0;
        }
    }

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