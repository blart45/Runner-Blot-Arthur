import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameScene extends Scene {

    //Initialisation des variables
    private ImageView leftsprite;
    private ImageView rightsprite;
    private StaticThing leftBackground;
    private StaticThing rightBackground;
    private ImageView heroSprite;
    private Hero knight;
    private Camera camera;
    private AnimationTimer timer;
    private Foe foe;
    private double x; //position de foe
    private Integer random;
    //HitBox du hero
    private double xHitBox;
    private double yHitBox;
    private double withHitBox;
    private double lengthHitBox;
    //HitBox des ennemies
    private double xHitBoxFoe;
    private double yHitBoxFoe;
    private double withHitBoxFoe;
    private double lengthHitBoxFoe;
    //pour les vies
    private Integer nbrlives;
    private StaticThing lives;
    private ImageView livesSprite;
    private boolean collision;
    private Integer invinsible;
    //fin Init


    //Debut Fonction
    public ImageView getLeftsprite() {
        return leftsprite;
    }
    public ImageView getRightsprite() {
        return rightsprite;
    }
    public ImageView getHeroSprite() {
        return heroSprite;
    }

    //render
    public void render(){
        double offset = camera.getX()%800;
        knight.getAnimatedSprite().setX(knight.getX()-camera.getX());
        knight.getAnimatedSprite().setY(knight.getY()-camera.getY());
        foe.getAnimatedSprite().setX(x-camera.getX());
        foe.getAnimatedSprite().setY(foe.getY()-camera.getY()); //ATTENTION L'ORDRE
        leftBackground.getSprite().setX(0-offset);
        rightBackground.getSprite().setX(800-offset);

    }

    //Permet de changer le Viewport des vies
    public void livesUpdate(){
        if(nbrlives==0){ //3vies
//            this.lives = new StaticThing(0,0,"lives.png",113,242,114,31);
//            this.livesSprite = lives.getSprite();
            livesSprite.setViewport(new Rectangle2D(113,242,114,31));
            //root.getChildren().add(livesSprite); //Display heart

        }
        if(nbrlives==1){ //2vies
            //this.lives = new StaticThing(0,0,"lives.png",113,275,114,32);
            livesSprite.setViewport(new Rectangle2D(113,275,114,32));

        }
        if(nbrlives==2){ //1vie
//            this.lives = new StaticThing(0,0,"lives.png",114,309,113,31);
//            this.livesSprite = lives.getSprite();
            livesSprite.setViewport(new Rectangle2D(114,309,113,31));
            //root.getChildren().add(livesSprite); //Display heart
        }
        if(nbrlives==3){ //plus de vie game over
            System.out.println("GAME OVER");
//            this.lives = new StaticThing(0,0,"lives.png",114,343,112,33);
//            this.livesSprite = lives.getSprite();
            livesSprite.setViewport(new Rectangle2D(114,343,112,33));
            //root.getChildren().add(livesSprite); //Display heart
        }

    }
    //Enleve une vie à la collision
    public void nombreDeVie(){
        if (collision==true && nbrlives!=4)
        {
            nbrlives = nbrlives+1;
        }
    }

    //vérifie les collisions entre le hero et l'ennemi
    public void getHitBox(Hero hero, Foe foe){
        this.xHitBox = hero.getX()-40;
        this.yHitBox = hero.getY()-40;
        this.lengthHitBox = 40;
        this.withHitBox = 70;

        this.xHitBoxFoe = x-40;
        this.yHitBoxFoe = foe.getY()-40;
        this.lengthHitBoxFoe = 70;
        this.withHitBoxFoe = 120;

        if(invinsible <0){
            this.invinsible = 0;
            this.collision = false;
        }
        if(invinsible>0){
            invinsible = invinsible-1;
            this.collision = false;
        }
        if(invinsible==0){
            if (xHitBox < xHitBoxFoe + lengthHitBoxFoe &&
                    xHitBox + lengthHitBox > xHitBoxFoe &&
                    yHitBox < yHitBoxFoe + withHitBoxFoe &&
                    withHitBox + yHitBox > yHitBoxFoe) {
                this.collision = true;
                this.invinsible = 100;
            }
        }
    }

    //crée un nouveau foe à une abscisse random
    private void foeUpdate() {
        if (x < knight.getX()-300) {
            random = (int)(Math.random() * 2000); //random spawn of foe
            x = x + 1500 + Math.random();
        }
    }
    //Fin Fonction

    //Debut Constructor
    public GameScene(Group root, double length, double width) {
        super(root, length, width);

            //creation des backgrounds sur deux instances différentes
        this.leftBackground = new StaticThing(0,0,"desert.png",0,0,800,400);
        this.rightBackground = new StaticThing(800,0,"desert.png",0,0,800,400);
            //association à deux instances différentes
        this.leftsprite = leftBackground.getSprite();
        this.rightsprite = rightBackground.getSprite();
            //affichage du background
        root.getChildren().add(leftsprite);
        root.getChildren().add(rightsprite);

            //Enemy
        this.foe=new Foe();
        root.getChildren().add(foe.getAnimatedSprite());

            //creation du personnage et affichage du personnage
        this.knight = new Hero();
        this.heroSprite = knight.getAnimatedSprite();
        root.getChildren().add(heroSprite);     //Display the hero

            //nbr de vie
        nbrlives=0; //3 vies
        invinsible=0; //pas invincible
        this.collision=false; //pas de collision
        this.lives = new StaticThing(0,0,"lives.png",113,242,114,31);
        this.livesSprite = lives.getSprite();

        camera = new Camera(0,0,knight);    // coordinate top-left of the camera;

            //timer
        timer = new AnimationTimer() {
            private long lastTime;
            @Override
            public void handle(long time) {
                if (time-lastTime>0.016*1_000_000_000) {
                    knight.update();
                    foe.update();
                    lastTime = time;
                    foeUpdate();
                    nombreDeVie(); //Change le nombre de vie si collision à l'interruption precedente
                    getHitBox(knight,foe);
                    root.getChildren().remove(livesSprite); //remove heart
                    livesUpdate(); //update le sprite du nombre de vies
                    root.getChildren().add(livesSprite); //Display heart

                }
                    //Update de la camera
                camera.update();
                render();
            }
        };
        timer.start();

            //Jump
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                //Jump
                if(keyEvent.getCode()== KeyCode.UP){
                    knight.jump();
                }
                //Shoot
                if(keyEvent.getText().equals(" ") && knight.getShootcount()==-1){
                    knight.shoot();
                }
            }
        });
    }
    //fin Constructor
}


