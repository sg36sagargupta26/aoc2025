import days.day03.Day03;

void main() {
    Day03 day03 = new Day03();
    Instant start = Instant.now();
    IO.println("result -> " + day03.day03a());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}