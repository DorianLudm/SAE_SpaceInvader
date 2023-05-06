import java.util.Random;

public class ProjectileAlien extends Projectile{
    private int cooldown;
    private int sprite;

    public ProjectileAlien(double X, double Y){
        super(X,Y);
        this.cooldown = 5;
        this.sprite = 0;
    }

    public ProjectileAlien(double X, double Y, int Sprite){
        super(X,Y);
        this.cooldown = 5;
        this.sprite = Sprite;
    }


    public double getX(){
        return super.getX();
    }

    public double getY(){
        return super.getY();
    }

    public int getSrite(){
        return this.sprite;
    }
    
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