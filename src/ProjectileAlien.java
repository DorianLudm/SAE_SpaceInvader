public class ProjectileAlien extends Projectile{

    public ProjectileAlien(double X, double Y){
        super(X,Y);
    }

    public double getX(){
        return super.getX();
    }

    public double getY(){
        return super.getY();
    }

    @Override
    public EnsembleChaines getEnsembleChaines() {
        super.LesChainesProj = new EnsembleChaines();
        super.LesChainesProj.ajouteChaine((int) this.posX, (int) this.posY, "▼▼");
        return super.LesChainesProj;
    }

    @Override
    public void evolue(int multi){
        if(multi != 10){
            this.posY -= 1*multi;
        }
        else{
            this.posY -= 1;
        }
    }
}