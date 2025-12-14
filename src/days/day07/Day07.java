package days.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    public long day07b() {
        var input = input();
        int startPosition  = 0;
        while (startPosition<input[0].length && input[0][startPosition]!='S'){
            startPosition++;
        }
        Map<Integer,Long> map = new HashMap<>();
        map.put(startPosition,1L);
        int currentLevel = 1;
        while (!map.isEmpty() && currentLevel<input.length){
            var nextMap = getNextMap(map, input, currentLevel);
            currentLevel++;
            map = nextMap;
        }
        return map
                .values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private Map<Integer, Long> getNextMap(Map<Integer, Long> map, char[][] input, int currentLevel) {
        Map<Integer,Long> nextMap = new HashMap<>();
        for(var c : map.entrySet()){
            if(input[currentLevel][c.getKey()]=='.'){
                nextMap.put(c.getKey(),nextMap.getOrDefault(c.getKey(),0L)+c.getValue());
            }else if(input[currentLevel][c.getKey()]=='^'){
                if(c.getKey()-1>=0){
                    nextMap.put(c.getKey()-1,nextMap.getOrDefault(c.getKey()-1,0L)+c.getValue());
                }
                if(c.getKey()+1< input[0].length){
                    nextMap.put(c.getKey()+1,nextMap.getOrDefault(c.getKey()+1,0L)+c.getValue());
                }
            }
        }
        return nextMap;
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
