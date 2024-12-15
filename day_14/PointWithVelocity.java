package day_14;

import java.awt.Point;

public class PointWithVelocity
{
    Point startPoint;
    int vx;
    int vy;

    public PointWithVelocity(int px, int py, int vx, int vy)
    {
        startPoint = new Point(px, py);
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public String toString()
    {
        return "[Point(" + (int)startPoint.getX() + ", " + (int)startPoint.getY() + "), Velocity(" + vx + ", " + vy + ")]";
    }
}
