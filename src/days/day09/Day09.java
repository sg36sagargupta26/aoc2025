package days.day09;

import utility.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day09 {
    public long day09a() {
        var input = getInput();
        return getBiggestRectangle(input);
    }

    public long day09b() {
        var input = getInput();
        var edges = getAllEdges(input);
        return getBiggestOverlappingRectangle(input,edges);
    }

    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getAllEdges(List<Pair<Integer, Integer>> input) {
        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> res = new ArrayList<>();
        for(int i =0;i<input.size()-1;i++){
            var pairI = input.get(i);
            var pairJ= input.get(i+1);
            res.add(new Pair<>(pairI, pairJ));
        }
        res.add(new Pair<>(input.getFirst(),input.getLast()));
        return res;
    }

    private boolean hasIntersections(Pair<Integer,Integer> pointA, Pair<Integer,Integer> pointB,List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges) {
        int minX = Math.min(pointA.key(),pointB.key());
        int minY = Math.min(pointA.value(),pointB.value());
        int maxX = Math.max(pointA.key(),pointB.key());
        int maxY = Math.max(pointA.value(),pointB.value());
        for (var inter : edges) {
            int x1 = inter.key().key();
            int x2 = inter.value().key();
            int y1 = inter.key().value();
            int y2 = inter.value().value();
            long iMinX = Math.min(x1, x2);
            long iMaxX = Math.max(x1, x2);
            long iMinY = Math.min(y1, y2);
            long iMaxY = Math.max(y1, y2);
            if (minX < iMaxX && maxX > iMinX && minY < iMaxY && maxY > iMinY) {
                return true;
            }
        }
        return false;
    }

    private long getBiggestOverlappingRectangle(List<Pair<Integer, Integer>> input,List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges) {
        var len = input.size();
        long result = 0;
        for( int i =0;i<len;i++){
            var pairI = input.get(i);
            for(int j= i;j<len;j++){
                var pairJ= input.get(j);
                if(!hasIntersections(pairI,pairJ,edges)){
                    long x = (long) Math.abs(pairI.key()-pairJ.key()) +1;
                    long y = (long) Math.abs(pairI.value()-pairJ.value()) +1 ;
                    long currArea = x*y;
                    result = Math.max(currArea,result);
                }
            }
        }
        return result;
    }

    private long getBiggestRectangle(List<Pair<Integer, Integer>> input) {
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

    private List<Pair<Integer, Integer>> getInput() {
        try(Stream<String> lines = Files.lines(Path.of("src/days/day09/day09.txt"))){
           return lines.map(this::getPairFromList).toList();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Pair<Integer,Integer> getPairFromList(String string) {
        String [] strings = string.split(",");
        return new Pair<>(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
    }


}
