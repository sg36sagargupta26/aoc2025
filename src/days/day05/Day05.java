package days.day05;

import utility.Interval;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day05 {
    private NavigableMap<Long,Long> rangeMap;
    public long day05a() {
        rangeMap = rangeMap(merge(getInputRange()));
        try(Stream<String> inputString= Files.lines(Path.of("src/days/day05/day05b.txt"))){
            return inputString.mapToLong(Long::parseLong).filter(this::contains).count();
        } catch (IOException e) {
            return 0;
        }
    }

    public long day05b() {
        return merge(getInputRange())
                .stream()
                .mapToLong(Interval::difference)
                .sum();
    }

    private List<Interval> getInputRange(){
        try(Stream<String> stringRanges = Files.lines(Path.of("src/days/day05/day05a.txt"))){
            return stringRanges.map(Interval::generateFromString).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }
        intervals.sort(Comparator.comparingLong(Interval::getStart));

        List<Interval> merged = new ArrayList<>();
        merged.add(intervals.getFirst());


        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval lastMerged = merged.getLast();


            if (current.getStart() <= lastMerged.getEnd()) {
                lastMerged.setEnd(Math.max(lastMerged.getEnd(), current.getEnd()));
            } else {
                merged.add(current);
            }
        }

        return merged;
    }
    private NavigableMap<Long, Long> rangeMap(List<Interval> intervals){
        NavigableMap<Long,Long> ranges= new TreeMap<>();
        intervals.forEach(interval->ranges.put(interval.getStart(), interval.getEnd()));
        return ranges;
    }

    public boolean contains(long value) {
        Long floorKey = this.rangeMap.floorKey(value);
        if (floorKey == null) {
            return false;
        }
        long end = rangeMap.get(floorKey);
        return value <= end;
    }

}
