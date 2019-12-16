package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
 
class Interval{
    int lower;
    int upper;
    Interval(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }
}
 

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        Interval[] ins = new Interval[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String[] arr = strs[i].split(",");
            ins[i] = new Interval(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }
 
        Arrays.sort(ins, Comparator.comparingInt(o -> o.lower));
 
        int cur = 0;
        for (int i = 1; i < ins.length; i++) {
            if (ins[i].lower <= ins[cur].upper) {
                ins[cur].upper = Math.max(ins[cur].upper, ins[i].upper);
            } else {
                System.out.print(ins[cur].lower + "," + ins[cur].upper + " ");
                cur = i;
            }
        }
 
        System.out.println(ins[cur].lower + "," + ins[cur].upper);
    }
}