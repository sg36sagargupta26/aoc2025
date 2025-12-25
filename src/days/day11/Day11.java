package days.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Day11 {

    public long problemA(){
        Map<String,GraphNodes> graph = createGraph();
        Map<String,Long> memo = new HashMap<>();
        String inputString = "you";
        String outputString = "out";
        return bfs(inputString,graph,memo,outputString);
    }

    private long bfs(String inputString, Map<String, GraphNodes> graph, Map<String, Long> memo, String outputString) {
        if(outputString.equals(inputString)){
            return 1;
        }
        if(memo.containsKey(inputString)){
            return memo.get(inputString);
        }
        if(!graph.containsKey(inputString)){
            return 0;
        }
        var node = graph.get(inputString);
        long numOfPaths =0 ;
        for(var children: node.getChildren()){
            numOfPaths += bfs(children.getName(),graph,memo, outputString);
        }
        memo.put(inputString,numOfPaths);
        return numOfPaths;
    }

    private Map<String,GraphNodes>  createGraph(){
        Map<String,GraphNodes> graph = new HashMap<>();
        try(Stream<String> lines = Files.lines(Path.of("src/days/day11/day11.txt"))){
            lines.forEach(s-> getGraphNodes(s,graph));
        } catch (IOException e) {
            //do nothing
        }
        return graph;
    }

    private void getGraphNodes(String s,Map<String,GraphNodes> graph) {
        String[] nodes = s.split("[:\\s]+");
        String parentNode = nodes[0];
        String [] childrenNodes = Arrays.copyOfRange(nodes,1,nodes.length);
        graph.putIfAbsent(parentNode,new GraphNodes(parentNode));
        for(var children: childrenNodes){
            graph.putIfAbsent(children,new GraphNodes(children));
            graph.get(parentNode).updateChildren(graph.get(children));
        }
    }
}
