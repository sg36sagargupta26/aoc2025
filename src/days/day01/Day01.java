package days.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class Day01 {
    public long day01A(){
        try (Stream<String> lines = Files.lines(Paths.get("src/days/day01/day01a.txt"))) {
            return lines.map(this::mapStringToShift)
                    .gather(Gatherers.scan(
                            ()->50,
                            (currVal,update)->(((currVal + update) % 100) + 100) % 100
                    ))
                    .filter(val->val==0)
                    .count();
        } catch (IOException e) {
            return 0;
        }
    }
    private int mapStringToShift(String string) {
        int prefix = string.charAt(0)=='L'?-1:1;
        int value = Integer.parseInt(string.substring(1));
        return prefix*value;
    }



}
