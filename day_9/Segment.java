package day_9;

public abstract class Segment {
    final int size;
    final String content;

    public Segment(int size, String content) {
        this.size = size;
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }
}
