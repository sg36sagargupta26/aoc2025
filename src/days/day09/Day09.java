package days.day09;

import utility.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Day09 {
    public long day09a() {
        var input = getInput().orElse(new ArrayList<>());
        var len = input.size();
        long result = 0;
        for( int i =0;i<len;i++){
            var pairI = input.get(i);
            for(int j= i+1;j<len;j++){
                var pairJ= input.get(j);
                long x = (long) Math.abs(pairI.key()-pairJ.key()) +1;
                long y = (long) Math.abs(pairI.value()-pairJ.value()) +1 ;
                result = Math.max(x*y,result);
            }
        }
        return result;
    }

    private Optional<List<Pair<Integer, Integer>>> getInput() {
        try(Stream<String> lines = Files.lines(Path.of("src/days/day09/day09.txt"))){
            var list = lines.map(this::getPairFromList).toList();
            return Optional.of(list);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private Pair<Integer,Integer> getPairFromList(String string) {
        String [] strings = string.split(",");
        return new Pair<>(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
    }


}
