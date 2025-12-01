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

    public long day01B(){
        try (Stream<String> lines = Files.lines(Paths.get("src/days/day01/day01a.txt"))) {
            var intList = lines.map(this::mapStringToShift).toList();
            int currVal = 50;
            int count = 0;
            for (int i : intList) {
                int oldVal = currVal;
                currVal=currVal+i;
                if(currVal<=0){
                    count+=Math.abs(Math.floorDiv(currVal,100));
                    if(currVal%100==0){
                        count++;
                    }
                    if(oldVal==0){
                        count--;
                    }
                }else{
                    count+=Math.floorDiv(currVal,100);
                }
                currVal= (((currVal%100))+100)%100;
            }
            return count;
        } catch (IOException e) {
            return 0;
        }
    }
}
