package days.day02;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    public BigInteger day02B(){
        try{
            return Pattern.compile(",")
                    .splitAsStream(Files.readString(Paths.get("src/days/day02/day02a.txt")))
                    .parallel()
                    .map(this::convertToPair)
                    .map(this::pairSplit)
                    .flatMap(Collection::parallelStream)
                    .map(this::countMultipleRepeats)
                    .reduce(BigInteger::add)
                    .orElse(BigInteger.ZERO);
        } catch (IOException e) {
            return BigInteger.ZERO;
        }
    }

    private BigInteger countMultipleRepeats(Pair pair) {
        Set<BigInteger> set = new HashSet<>();
        int length = pair.start().length;
        return IntStream.rangeClosed(1,length-1)
                .filter(i->length%i==0)
                .mapToObj(i->countGenericRepeats(pair,i,set))
                .reduce(BigInteger::add)
                .orElse(BigInteger.ZERO);
    }

    private BigInteger countGenericRepeats(Pair pair, int i, Set<BigInteger> set) {
        int length = pair.start().length;
        BigInteger count =BigInteger.valueOf(0);
        BigInteger start = new BigInteger(String.valueOf(pair.start()));
        BigInteger end = new BigInteger(String.valueOf(pair.end()));
        BigInteger entry = new BigInteger(String.valueOf(Arrays.copyOfRange(pair.start(),0,i)));
        BigInteger number = generateActualNumber(entry,length/i);
        while (end.compareTo(number)>=0 ){
            entry = entry.add(BigInteger.ONE);
            if(start.compareTo(number)<=0 && !set.contains(number)){
                count = count.add(number);
                set.add(number);
            }
            number = generateActualNumber(entry,length/i);
        }
        return count;
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

    private BigInteger generateActualNumber(BigInteger entry, int count){
        return new BigInteger(String.valueOf(entry).repeat(count));
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
