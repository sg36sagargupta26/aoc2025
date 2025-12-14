package utility;

public class Interval{
    private final long start;
    private long end;

    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public static Interval generateFromString(String string){
        String [] split = string.split("-");
        return new Interval(Long.parseLong(split[0]),Long.parseLong(split[1]));
    }
}