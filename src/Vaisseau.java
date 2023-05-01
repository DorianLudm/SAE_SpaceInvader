public class Vaisseau {
    private double posX;
    private EnsembleChaines LesChaines;

    public Vaisseau(){
        this.posX = 140;
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

    public void deplace(double dx, int multi){
        if(multi == 10){
            multi = 1;
        }
        this.posX += dx*multi;
        for(ChainePositionnee chaineElem: LesChaines.chaines){
            chaineElem.x = this.posX;
        }
    }

    public EnsembleChaines getEnsembleChaines(){
        return this.LesChaines;
    }

    public double getPosX(){
        return this.posX;
    }

    public void setPosX(double newPosX){
        this.posX = newPosX;
    }

    public double positionCanon(){
        return (this.posX + 10);
    }
}