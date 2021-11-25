import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing{

    //debut initialisation des variables
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private int index;
    private boolean shoot;
    private int shootcount;
    private double acc; //compensation du saut
    //Fin init

    //Debut fonction
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getShootcount() {
        return shootcount;
    }

    public void jump() {
        if (y == 250) {
            ay = -60000;
        }
    }

    public void shoot(){
        shoot = true;
        shootcount =0;
    }

    //Permet d'update la viewport du hero
    public void update(){
        double elapsedTime=0.016;  ////update fixe à 16ms (60Hz)
        ax=10;
        vx=vx+elapsedTime*ax;
        x=x+elapsedTime*vx;
        vy=vy+elapsedTime*ay;
        y=y+elapsedTime*vy;
        acc=2500+vx*1.3;

        if (y>=250) {
            y=250;
            vy=0;
        }
        if (vy<0) {
            super.getAnimatedSprite().setViewport(new Rectangle2D(20,160,70,110)); //jump up
        }
        if (vy>0){
            super.getAnimatedSprite().setViewport(new Rectangle2D(90,160,80,110)); //jump down
        }
        if (y<250){
            ay=acc;
        }
        if (vy==0 && y==250 && shoot==false) {
            index++;
            super.getAnimatedSprite().setViewport(new Rectangle2D(10 + (index/5)%6 * 83, 0, 70, 100)); //image pendant run
        }
        if (vy==0 && y==250 && shoot==true ) {//image pendant tir

            if (shootcount < 29) {
                super.getAnimatedSprite().setViewport(new Rectangle2D(3 + (shootcount / 5) % 6 * 83, 326, 79, 100));
                shootcount = shootcount + 1;
            }
            if (shootcount == 29) {
                shootcount = shootcount + 1;
                shoot = false;
            }
        }
        if(shootcount>29 && shootcount<70){
            shootcount = shootcount+1;
        }
        if(shootcount==70){
            shootcount = -1;
        }
    }

    //Fin fonction

    //Debut constructor
    public Hero(){
        super(800,250,"heros.png");
        //reglage du hero
        this.x=800;
        this.y=250;
        this.vx=400;
        this.ax=0;
        this.vy=0;
        this.ay=0;
        shootcount=-1; //Initialisation de la valeur du tir à -1
    }
    //Fin constructor

}
