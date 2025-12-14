import days.day06.Day06;

void main() {
    Day06 day06 = new Day06();
    Instant start = Instant.now();
    IO.println("result -> " + day06.day06b());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}