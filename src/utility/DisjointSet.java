package utility;

import java.util.*;

public class DisjointSet {
    private final int size;
    private final int [] parents;


    public DisjointSet(int size) {
        this.size = size;
        this.parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i]=i;
        }
    }
    private int findParent(int i){
        if(i==parents[i]){
            return i;
        }
        return findParent(parents[i]);
    }

    private boolean alreadyMerged(int i, int j){
        return findParent(i)==findParent(j);
    }

    public void union(int i, int j){
        if(alreadyMerged(i,j)){
            return;
        }
        int parentI = findParent(i);
        int parentJ = findParent(j);
        this.parents[parentI]=parentJ;
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
