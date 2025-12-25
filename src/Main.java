import days.day11.Day11;

void main() {
    var d = new Day11();
    Instant start = Instant.now();
    IO.println("result -> " + d.problemA());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}