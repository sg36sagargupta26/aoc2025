import days.day07.Day07;

void main() {
    Day07 day07 = new Day07();
    Instant start = Instant.now();
    IO.println("result -> " + day07.day07b());
    Instant end = Instant.now();
    Duration duration = Duration.between(start,end);
    IO.println("time -> " +duration.toMillis());
}