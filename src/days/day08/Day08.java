package days.day08;

import utility.Coordinate;
import utility.DisjointSet;
import utility.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Day08 {
    public long day08a(){
        int maxConnection = 10;
        var coordinates = getInput();
        int len = coordinates.length;
        var priorityQueue = getPairsSortedByDistance(coordinates);
        var disjointSet = new DisjointSet(len);
        while (maxConnection>0){
            var a = priorityQueue.remove().value();
            disjointSet.union(a.key(),a.value());
            maxConnection--;
        }
        return disjointSet.productOfThreeCircuits();
    }

    private Coordinate [] getInput(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day08/day08.txt"))){
            return lines.map(Coordinate::parseCoordinate)
                    .toList()
                    .toArray(new Coordinate[0]);
        } catch (IOException e) {
            return new Coordinate[0];
        }
    }

    private PriorityQueue<Pair<Long,Pair<Integer,Integer>>> getPairsSortedByDistance(Coordinate[] coordinates){
        int len = coordinates.length;
        PriorityQueue<Pair<Long,Pair<Integer,Integer>>> priorityQueue =
                new PriorityQueue<>(this::comparator);
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                var pair = generatePairFromCoordinate(coordinates[i], coordinates[j], i, j);
                priorityQueue.add(pair);
            }
        }
        return priorityQueue;

    }

    private int comparator(Pair<Long,Pair<Integer,Integer>> a, Pair<Long,Pair<Integer,Integer>> b){
        if(a.key()> b.key()){
            return 1;
        }
        if(a.key().equals(b.key())){
            return 0;
        }
        return -1;
    }

    private Pair<Long,Pair<Integer,Integer>> generatePairFromCoordinate(Coordinate coordinateI, Coordinate coordinateJ, int i, int j){
        return new Pair<>(coordinateI.squaredDistance(coordinateJ),new Pair<>(i,j));
    }

}
