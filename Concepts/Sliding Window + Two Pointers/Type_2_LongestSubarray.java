// Sliding Window Type 2 - Longest Subarray / Subtsring with a condition
/*
Eg Length of Longest subarray with sum<=k

*/

import java.util.*;

public class Type_2_LongestSubarray{
    public static void main(String[] args){
        int[] arr1={2,5,1,7,10};
        int k1=14;
        System.out.println(betterSolution(arr1,k1));
        System.out.println(OptimalSolution(arr1,k1));
    }
    
// left -> shrink;
// right -> expand;

    static int betterSolution(int[] arr,int k){
        int n=arr.length;
        int sum=0;
        int left=0;
        int right=0;
        int max=0;
        while(right<n){
            sum+=arr[right];
            while(sum>k){
                sum-=arr[left];
                left++;
            }
            max=Math.max(max,right-left+1);
            right++;
        }
        return max;
    }
    
    static int OptimalSolution(int[] arr,int k){
        int n=arr.length;
        int sum=0;
        int left=0;
        int right=0;
        int max=0;
        while(right<n){
            sum+=arr[right];
            if(sum>k){ // maintain maxlen window, so just 1 movement per iteration
                sum-=arr[left];
                left++;
            }
            max=Math.max(max,right-left+1);
            right++;
        }
        return max;
    }
}
