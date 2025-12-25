import days.day10.Day10;

void main() {
    Day10 day10 = new Day10();
    Instant start = Instant.now();
    IO.println("result -> " + day10.problemA());
    IO.println("result -> " + day10.problemB());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}