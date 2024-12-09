package day_9;

public class FreeSpace extends Segment
{
    public FreeSpace(int size)
    {
        super(size, ".");
    }

    @Override
    public String toString()
    {
        return "FreeSpace(.," + super.size + ")";
    }
}
