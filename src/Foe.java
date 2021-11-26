import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing {
    private double x;
    private double y;
    private int index;

    //fonctions
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }


    public void update(){
        index++;
        super.getAnimatedSprite().setViewport(new Rectangle2D(18 + (index/5)%2 * 150, 0, 160, 110)); //animation du Foe
    }
    //fin fonctions

    //Constructor
    public Foe() {
        super(0, 0, "foe.png"); //Copyright from Nahiala665 Thanks Nahiala665 for the design !!
        this.x = 1500;
        this.y = 200;
        super.getAnimatedSprite().setViewport(new Rectangle2D(0,0,160,110));
    }

    public void addY(int y){
        this.y=y;
    }

}
