package days.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;



public class Day10 {
    public int problemA(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day10/day10.txt"))){
            return lines.map(this::createIndicatorDetails)
                    .mapToInt(this::minimumCountToAchieveSwitches)
                    .sum();
        } catch (IOException e) {
            IO.println("failed to read file");
            return 0;
        }
    }

    public int problemB(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day10/day10.txt"))){
            return lines.map(this::createIndicatorDetails)
                    .mapToInt(this::solveMultiIntegerProgramming)
                    .sum();
        } catch (IOException e) {
            IO.println("failed to read file");
            return 0;
        }
    }

    private int solveMultiIntegerProgramming(IndicatorDetails indicatorDetails) {
        MultiIntegerProgrammingSolver multiIntegerProgrammingSolver = new MultiIntegerProgrammingSolver(indicatorDetails);
        return multiIntegerProgrammingSolver.solve();
    }


    private int minimumCountToAchieveSwitches(IndicatorDetails indicatorDetails){
        Set<String> globalState = new HashSet<>();
        Set<String> currentStates = new HashSet<>();
        String finalState = indicatorDetails.switches();
        String initialState = generateInitialString(finalState.length());
        currentStates.add(initialState);
        globalState.add(initialState);
        int depth = 0;
        while(!currentStates.isEmpty()){
            Set<String> nextStates = new HashSet<>();
            for(var state: currentStates){
                if(finalState.equals(state)){
                    return depth;
                }
                for(var buttons: indicatorDetails.buttons()){
                    char[] tempState = state.toCharArray();
                    for(var i: buttons){
                        if(tempState[i]=='.'){
                            tempState[i]='#';
                        }else{
                            tempState[i]='.';
                        }
                    }
                    var tempNextState=String.valueOf(tempState);
                    if(!globalState.contains(tempNextState)) {
                        nextStates.add(tempNextState);
                        globalState.add(tempNextState);
                    }
                }
            }
            currentStates = nextStates;
            depth++;
        }
        return 0;
    }


    private IndicatorDetails createIndicatorDetails(String line){
        var string = line.split(" ");
        String switches = string[0].substring(1,string[0].length()-1);
        var voltages = getIntegerList(string[string.length-1]);
        List<List<Integer>> buttons = new ArrayList<>();
        for(int i =1; i< string.length-1;i++){
            buttons.add(getIntegerList(string[i]));
        }
        return new IndicatorDetails(switches,buttons,voltages);

    }

    private List<Integer> getIntegerList(String s) {
        return Arrays.stream(s.substring(1,s.length()-1).split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private String generateInitialString(int n){
        char [] charArray = new char[n];
        Arrays.fill(charArray,'.');
        return String.valueOf(charArray);
    }
}
