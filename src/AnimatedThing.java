import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    //DebutInit
    private double x;
    private double y;
    protected ImageView animatedSprite;
    protected Integer attitude; //still, running, jumping up, jumping down
    protected Integer index;
    private Integer duration; //duration between two frames
    private Integer maxIndex;
    private Integer windowSize;
    private Integer offset; //offset between each frame

    private double vx;
    private double ax;
    private double vy;
    private double ay;
    private long lastTime;
    //FinInit

    //DebutFonction
    public ImageView getAnimatedSprite() {
        return animatedSprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    //FinFonction

    //Constructor
    public AnimatedThing(double xpos, double ypos, String fileName){
        this.x = xpos;
        this.y = ypos;
        this.index = index;
        Image spriteSheet = new Image(fileName);
        animatedSprite = new ImageView(spriteSheet);
        animatedSprite.setX(xpos);
        animatedSprite.setY(ypos);
    }
}

