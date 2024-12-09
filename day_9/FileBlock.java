package day_9;

public class FileBlock extends Segment
{
    public FileBlock(int size, String content)
    {
        super(size, content);
    }

    @Override
    public String toString()
    {
        return "FileBlock(" + super.content + "," + super.size + ")";
    }
}
