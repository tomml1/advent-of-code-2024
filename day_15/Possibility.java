package day_15;

public class Possibility
{
    private boolean possible;

    public Possibility(boolean possible)
    {
        this.possible = possible;
    }

    public boolean setPossible(boolean possible)
    {
        return this.possible = possible;
    }

    public boolean getPossible()
    {
        return possible;
    }
}
