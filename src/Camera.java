public class Camera {
    private double x;
    private double y;
    private Hero center;

    public void update(){
        x = center.getX()-150;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString(){
        return "("+x+";"+y+")";
    }

    //Constructor
    public Camera(Integer x,Integer y,Hero center) {
        this.x = x;
        this.y = y;
        this.center = center;
    }


}
