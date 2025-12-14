import days.day05.Day05;

void main() {
    Day05 day05 = new Day05();
    Instant start = Instant.now();
    IO.println("result -> " + day05.day05a());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}