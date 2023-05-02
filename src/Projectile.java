public class Projectile {
    protected double posX;
    protected double posY;
    protected EnsembleChaines LesChainesProj;

    public Projectile(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.LesChainesProj = new EnsembleChaines();
    }

    public double getX(){
        return this.posX;
    }

    public double getY(){
        return this.posY;
    }

    public EnsembleChaines getEnsembleChaines() {
        this.LesChainesProj = new EnsembleChaines();
        this.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY, "██");
        return this.LesChainesProj;
    }

    public void evolue(int multi){
        if(multi != 10){
            this.posY += 1*multi;
        }
        else{
            this.posY += 1;
        }
    }
}