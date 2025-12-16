import days.day09.Day09;

void main() {
    Day09 day09 = new Day09();
    Instant start = Instant.now();
    IO.println("result -> " + day09.day09a());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}