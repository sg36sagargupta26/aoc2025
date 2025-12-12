import days.day04.Day04;

void main() {
    Day04 day04 = new Day04();
    Instant start = Instant.now();
    IO.println("result -> " + day04.day04b());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}