package days.day06;

import utility.Operand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day06 {
    public long day06a() {
        var input = getInput();
        long res = 0;
        for(int i=0;i<input[0].length;i++){
            Operand operand = Operand.getOperand(input[input.length-1][i]);
            long cuur = operand.starter();
            for(int j=0;j<input.length-1;j++){
                long val = Long.parseLong(input[j][i]);
                cuur= operand.doOperation(cuur,val);
            }
            res+=cuur;
        }
        return res;
    }

    public long day06b() {
        var input = getSecondInput();
        long res = 0;
        List<Long> currRes = new ArrayList<>();
        Operand operand = Operand.getOperand(String.valueOf(input[input.length-1][0]));
        for(int i =0;i<input[0].length;i++){
            long x = 0;
            boolean anyElementAdded = false;
            for(int j = 0;j< input.length-1;j++){
                if(input[j][i]==' '){
                    continue;
                }
                anyElementAdded = true;
                x *= 10;
                x += input[j][i]-'0';
            }
            if(anyElementAdded){
                currRes.add(x);
            }

            if((i==input[0].length-1 || input[input.length-1][i+1]!=' ') ){
                long curr = operand.starter();
                for(long l: currRes){
                    curr=operand.doOperation(l,curr);
                }
                res+=curr;
                currRes=new ArrayList<>();
                operand = Operand.getOperand(String.valueOf(input[input.length-1][i+1]));

            }
        }
        return res;
    }

    private String [][] getInput(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day06/day06.txt"))){
            var list = lines
                    .map(String::trim)
                    .map(a->a.split("\\s+"))
                    .toList();
            return list.toArray(new String[list.size()][]);
        } catch (IOException e) {
            return new String[0][0];
        }
    }

    private char [][] getSecondInput(){
        try(Stream<String> lines = Files.lines(Path.of("src/days/day06/day06.txt"))){
            var list = lines
                    .map(String::toCharArray)
                    .toList();
            return list.toArray(new char[list.size()][]);
        } catch (IOException e) {
            return new char[0][0];
        }
    }


}
