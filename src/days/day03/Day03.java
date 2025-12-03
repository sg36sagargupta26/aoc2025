package days.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day03 {
    public int day03a(){
        try(Stream<String> list = Files.lines(Paths.get("src/days/day03/day03a.txt"))){
            return list
                    .map(this::createIntArray)
                    .mapToInt(this::largestTwoDigitNumber)
                    .sum();
        }catch (IOException ioException){
            return 0;
        }
    }

    private int largestTwoDigitNumber(int[] numbers) {
        int largestSingle = -1;
        int largestDouble = 0;
        for(int n: numbers){
            int currentDouble = largestSingle*10 + n;
            largestDouble = Math.max(currentDouble,largestDouble);
            if(n>largestSingle){
                largestSingle=n;
            }
        }
        return largestDouble;
    }

    private int[] createIntArray(String s) {
        return s.chars()
                .map(a->a-'0')
                .toArray();
    }
}
