package days.day04;

import utility.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
record Pair(int i, int j){}
public class Day04 {
    public char[][] getInput(){
        try(Stream<String> list = Files.lines(Paths.get("src/days/day04/day04a.txt"))){
            var a = list
                    .map(String::toCharArray)
                    .toList();
            return a.toArray(new char[a.size()][]);
        }catch (IOException ioException){
            return null;
        }
    }
    public int day04b() {
        char [][] input = getInput();
        int result =0;
        int currResult =1;
        int rowLen = input.length;
        int colLen = input[0].length;
        while (currResult>0){
            currResult =0;
            List<Pair> pairs = new ArrayList<>();
            for(int i =0;i<rowLen;i++){
                for(int j=0;j<colLen;j++){
                    if(input[i][j]!='@'){
                        continue;
                    }
                    int count =0;
                    for(Direction direction : Direction.values()){
                        if(validDirection(direction,i,j,input)){
                            count++;
                        }
                    }
                    if(count<4){
                        pairs.add(new Pair(i,j));
                        currResult++;
                    }
                }
            }
            pairs.forEach(a->input[a.i()][a.j()]='.');
            result+=currResult;
        }

        return result;
    }

    public int day04a() {
        char [][] input = getInput();
        int result =0;
        int rowLen = input.length;
        int colLen = input[0].length;
        for(int i =0;i<rowLen;i++){
            for(int j=0;j<colLen;j++){
                if(input[i][j]!='@'){
                    continue;
                }
                int count =0;
                for(Direction direction : Direction.values()){
                    if(validDirection(direction,i,j,input)){
                        count++;
                    }
                }
                if(count<4){
                    result++;
                }
            }
        }
        return result;
    }

    private boolean validDirection(Direction direction, int I, int J, char[][] input) {
        int i = direction.getI()+ I;
        int j = direction.getJ()+ J;
        if(i<0 || j<0 || i>=input.length || j>=input[0].length){
            return false;
        }
        return input[i][j] == '@';
    }


}
