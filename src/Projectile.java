public class Projectile {
    protected double posX;
    protected double posY;
    protected boolean shield;
    protected EnsembleChaines LesChainesProj;

    public Projectile(double X, double Y){
        this.posX = X;
        this.posY = Y;
        this.shield = false;
        this.LesChainesProj = new EnsembleChaines();
    }

    public Projectile(double X, double Y, boolean shield){
        this.posX = X;
        this.posY = Y;
        this.shield = true;
        this.LesChainesProj = new EnsembleChaines();
    }

    public boolean isShield(){
        return this.shield;
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