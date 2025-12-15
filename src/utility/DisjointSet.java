package utility;

import java.util.*;

public class DisjointSet {
    private final int size;
    private final int [] parents;
    private int noOfSets;


    public DisjointSet(int size) {
        this.size = size;
        this.parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i]=i;
        }
        this.noOfSets = size;
    }
    private int findParent(int i){
        if(i==parents[i]){
            return i;
        }
        return findParent(parents[i]);
    }

    public boolean alreadyMerged(int i, int j){
        return findParent(i)==findParent(j);
    }

    public void union(int i, int j){
        int parentI = findParent(i);
        int parentJ = findParent(j);
        this.parents[parentI]=parentJ;
        this.noOfSets--;
    }

    public boolean isFinalConnection(){
        return noOfSets==1;
    }

    public long productOfThreeCircuits(){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<this.size;i++){
            int parent = findParent(i);
            map.put(parent, map.getOrDefault(parent,0)+1);
        }
        return map.values()
                .stream()
                .sorted(Comparator.comparingInt(a -> -1*a))
                .limit(3)
                .mapToLong(a->(long)a)
                .reduce(1,(a,b)->a*b);
    }
}
