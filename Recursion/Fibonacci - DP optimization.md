# Fibonacci - DP optimization | Top Down (Memoization) Approach

```java
import java.util.*;

class Main {
    static int[] dp;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        dp[1] = 1;
        System.out.println(func(n));
    }

    static int func(int n){
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        dp[n] = func(n-1) + func(n-2);
        return dp[n];
    }
}
```
