import days.day02.Day02;

void main() {
    Day02 day02 = new Day02();
    Instant start = Instant.now();
    IO.println(day02.day02A());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println(duration.toNanos());
}