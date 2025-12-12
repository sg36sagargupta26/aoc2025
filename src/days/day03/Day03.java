package days.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Day03 {
    public int day03a(){
        try(Stream<String> list = Files.lines(Paths.get("src/days/day03/day03a.txt"))){
            return list
                    .map(this::createIntArray)
                    .mapToInt(this::largestTwoDigitNumber)
                    .sum();
        }catch (IOException ioException){
            return 0;
        }
    }

    public long day03b(){
        try(Stream<String> list = Files.lines(Paths.get("src/days/day03/day03a.txt"))){
            return list
                    .map(this::createIntArray)
                    .mapToLong(this::largestTwelveDigitNumber)
                    .sum();
        }catch (IOException ioException){
            return 0;
        }
    }

    private long largestTwelveDigitNumber(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[0]);
        for(int i =1;i<nums.length;i++){
            int trailingOptions = nums.length-1-i;
            if (!stack.isEmpty() && nums[i]<=stack.peekFirst() && stack.size()<12){
                stack.push(nums[i]);
                continue;
            }
            if(trailingOptions<12- stack.size()){
                stack.push(nums[i]);
                continue;
            }

            while (!stack.isEmpty() && nums[i]>stack.peekFirst() && trailingOptions>=12-stack.size()){
                stack.pop();
                if(stack.isEmpty() || trailingOptions+1 == 12-stack.size() || nums[i]<=stack.peekFirst()){
                    stack.push(nums[i]);
                }
            }

        }
        long res =0;
        while (!stack.isEmpty()){
            res*=10;
            res+=stack.pollLast();
        }
        return res;
    }

    private int largestTwoDigitNumber(int[] numbers) {
        int largestSingle = -1;
        int largestDouble = 0;
        for(int n: numbers){
            int currentDouble = largestSingle*10 + n;
            largestDouble = Math.max(currentDouble,largestDouble);
            if(n>largestSingle){
                largestSingle=n;
            }
        }
        return largestDouble;
    }

    private int[] createIntArray(String s) {
        return s.chars()
                .map(a->a-'0')
                .toArray();
    }
}
