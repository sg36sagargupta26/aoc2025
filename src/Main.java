import days.day08.Day08;

void main() {
    Day08 day08 = new Day08();
    Instant start = Instant.now();
    IO.println("result -> " + day08.day08a());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}