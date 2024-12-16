package day_13;

public class Day13SingleInput {
    int ax,
        ay,
        bx,
        by,
        px,
        py;

    public Day13SingleInput(int ax, int ay, int bx, int by, int px, int py)
    {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.px = px;
        this.py = py;
    }

    @Override
    public String toString()
    {
        return "Button A: X+" + ax + ", Y+" + ay + "\nButton B: X+" + bx + ", Y+" + by + "\n" + "Prize: X=" + px + ", Y=" + py;
    }
}
