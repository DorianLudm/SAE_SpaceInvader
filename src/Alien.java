public class Alien {
    private double posX;
    private double posY;
    private EnsembleChaines LesChainesProj;
    private String direction;

    public Alien(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesProj = new EnsembleChaines();
        this.direction = "droite";
    }

    public EnsembleChaines getEnsembleChaines() {
        this.LesChainesProj = new EnsembleChaines();
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY  , "    ██          ██    ");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-1, "      ██      ██      ");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-2, "    ██████████████    ");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-3, "  ████  ██████  ████  ");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-4, "██████████████████████");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-5, "██  ██████████████  ██");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-6, "██  ██          ██  ██");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-7, "      ████  ████      ");
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY-8, "                      ");
        return this.LesChainesProj;
    }

    public void evolue(){
        System.out.println(this.posY);
        if(this.posY == 15){
            this.posY = 15;
        }
        else{
            if(direction == "droite"){
                if(this.posX + 23 <= 301){
                    this.posX += 2;
                }
                else{
                    this.posY -= 10;
                    this.direction = "gauche";
                }
            }
            else{
                if(direction == "gauche"){
                    if(this.posX > 0){
                        this.posX -= 2;
                    }
                    else{
                        this.posY -= 10;
                        this.direction = "droite";
                    }
                }
            }
        }
    }
}