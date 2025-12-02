package days.day02;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

record Pair(char [] start, char [] end ){}
public class Day02 {
    public BigInteger day02A(){
        try{
            return Pattern.compile(",")
                    .splitAsStream(Files.readString(Paths.get("src/days/day02/day02a.txt")))
                    .parallel()
                    .map(this::convertToPair)
                    .map(this::pairSplit)
                    .flatMap(Collection::parallelStream)
                    .filter(this::isEvenLength)
                    .map(this::countRepeats)
                    .reduce(BigInteger::add)
                    .orElse(BigInteger.ZERO);
        } catch (IOException e) {
            return BigInteger.ZERO;
        }
    }

    private BigInteger countRepeats(Pair pair) {
        BigInteger count =BigInteger.valueOf(0);
        BigInteger start = new BigInteger(String.valueOf(pair.start()));
        BigInteger end = new BigInteger(String.valueOf(pair.end()));
        BigInteger entry = new BigInteger(String.valueOf(Arrays.copyOfRange(pair.start(),0,pair.start().length/2)));
        BigInteger number = generateActualNumber(entry);
        while (end.compareTo(number)>=0 ){
            entry = entry.add(BigInteger.ONE);
            if(start.compareTo(number)<=0){
                count = count.add(number);
            }
            number = generateActualNumber(entry);
        }
        return count;
    }

    private BigInteger generateActualNumber(BigInteger entry){
        String a = entry.toString();
        return new BigInteger(a + a);
    }

    private boolean isEvenLength(Pair pair) {
        return pair.start().length%2==0;
    }

    private List<Pair> pairSplit(Pair initialPair) {
        List<Pair> pairList = new ArrayList<>();
        Pair current = initialPair;
        while (current.start().length != current.end().length) {
            char[] endChunk = new char[current.start().length];
            Arrays.fill(endChunk, '9');
            char[] startNext = new char[current.start().length + 1];
            Arrays.fill(startNext, '0');
            startNext[0] = '1';
            pairList.add(new Pair(current.start(), endChunk));
            current = new Pair(startNext, current.end());
        }
        pairList.add(current);
        return pairList;
    }

    private Pair convertToPair(String s) {
        String [] res = s.split("-");
        return new Pair(res[0].toCharArray(),res[1].toCharArray());
    }
}
