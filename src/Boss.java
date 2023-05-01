public class Boss{
    private double posX;
    private double posY;
    private EnsembleChaines LesChainesBoss;
    private String direction;
    private int hp;

    public Boss(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesBoss = new EnsembleChaines();
        this.direction = "droite";
        this.hp = 50;
    }
    
    public double getX(){
        return this.posX;
    }

    public double getY(){
        return posY;
    }

    public int getHp(){
        return this.hp;
    }

    public String getDirection(){
        return this.direction;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

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

    public EnsembleChaines getEnsembleChaines() {
        EnsembleChaines lesChainesRes = new EnsembleChaines();
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()  , "              ▄▄▄▄▄▄▄▄████████████████▄▄▄▄▄▄▄▄              ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-1, "      ▄▄████████████████████████████████████████████▄▄      ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-2, "   ▄██████▀▀▀▀████████▀▀▀▀████████▀▀▀▀████████▀▀▀▀██████▄   ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-3, " ▄████████    ████████    ████████    ████████    ████████▄ ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-4, "██████████▄▄▄▄████████▄▄▄▄████████▄▄▄▄████████▄▄▄▄██████████");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-5, " ▀▀▀▀██████████████▀▀▀▀▀▀██████████▀▀▀▀▀▀██████████████▀▀▀▀ ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-6, "       ██████████         ████████         ██████████       ");
        lesChainesRes.ajouteChaine((int) getX(), (int) getY()-7, "        ▀▀████▀▀          ▀▀▀▀▀▀▀▀          ▀▀████▀▀        ");
        return lesChainesRes;
    }

    public void evolue(){
        if(this.getDirection() == "droite"){
            if(this.getX() + 59 <= 300){
                this.posX += 2;
            }
            else{
                this.setDirection("gauche");
            }
        }
        else{
            if(this.getDirection() == "gauche"){
                if(this.getX() > 2){
                    this.posX -= 2;
                }
                else{
                    this.setDirection("droite");
                }
            }
        }
    }
}