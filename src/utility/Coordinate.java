package utility;

public record Coordinate(long x, long y, long z) {
    public long squaredDistance(final Coordinate coordinate){
        return ((this.x()- coordinate.x())*(this.x()- coordinate.x()))
                + ((this.y()- coordinate.y())*(this.y()- coordinate.y()))
                + ((this.z()- coordinate.z())*(this.z()- coordinate.z()));
    }
    public static Coordinate parseCoordinate(final String s){
        String [] split = s.split(",");
        return new Coordinate(Long.parseLong(split[0]),Long.parseLong(split[1]),Long.parseLong(split[2]));
    }

}
