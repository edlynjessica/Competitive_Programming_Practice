# Recursion - Generating all subarrays from an array

```java

import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int n = arr.length;
        helper(0, n, arr, n);
    }
    public static void helper(int left, int right, int[] arr, int n){
        if (left > right) return;
        for (int i = left; i < right ;i++){
            System.out.print( arr[i] + " ");
        }
        System.out.println();
        helper( left, right-1, arr, n );
        helper( left+1, right, arr, n );
    }
}

```
