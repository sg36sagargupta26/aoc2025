package days.day06;

import utility.Operand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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


}
