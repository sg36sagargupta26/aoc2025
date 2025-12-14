package days.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day07 {
    public int day07a() {
        var input = input();
        int startPosition  = 0;
        while (startPosition<input[0].length && input[0][startPosition]!='S'){
            startPosition++;
        }
        int count = 0;
        Set<Integer> set = new HashSet<>();
        set.add(startPosition);
        int currentLevel = 1;
        while (!set.isEmpty() && currentLevel<input.length){
            Set<Integer> nextSet = new HashSet<>();
            for(int c : set){
                if(input[currentLevel][c]=='.'){
                    nextSet.add(c);
                }else if(input[currentLevel][c]=='^'){
                    count ++;
                    if(c-1>=0){
                        nextSet.add(c-1);
                    }
                    if(c+1<input[0].length){
                        nextSet.add(c+1);
                    }
                }
            }
            currentLevel++;
            set = nextSet;
        }
        return count;
    }

    private char[][] input(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day07/day07.txt"))){
            var list = lines
                    .map(String::toCharArray)
                    .toList();
            return list.toArray(new char[list.size()][]);
        } catch (IOException e) {
            return new char[0][0];
        }
    }
}
