import java.io.BufferedInputStream;
import java.util.*;

public class Strlenthcal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        String str = sc.next();
        char[] arr = str.toCharArray();
         
        int[] count_a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count_a[i] = count_a[i - 1] + (arr[i - 1] == 'a' ? 1 : 0);
        }

         
        int left = 0, right = n;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (canBeTransformed(mid, count_a, m)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }

    private static boolean canBeTransformed(int step, int[] count_a, int m) {
        for (int i = 0; i + step < count_a.length; i++) {
            int a = count_a[i + step] - count_a[i];     // 区间内字符'a'的个数
             
            if (a <= m) {
                return true;
            }
             
            if (step - a <= m) {
                return true;
            }
        }
        return false;
    }
}