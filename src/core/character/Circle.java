package core.character;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Circle {
    int x,y;
    int radius;

    Circle(int x,int y,int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public boolean check(Circle circle){
        double distance = Math.sqrt(Math.pow(this.x-circle.x,2)+Math.pow(this.y-circle.y,2));
        double ref = Math.abs(this.radius - circle.getRadius());

        boolean ret = distance > ref ? false : true;
        return ret;
    }
}
