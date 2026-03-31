// Sliding Window Type 1 - Constant Window Size
/*
Eg Q: Return the maximum sum of a subarray of size k from the given input array
GFG: Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.
link: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k
*/

import java.util.*;

public class Type_1_ConstantWindow{
    public static void main(String[] args){
        int[] arr={-1,2,3,3,4,5,-1};
        int n=arr.length;
        int k=4;
        // First window
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
        }
        int left=0;
        int right=k; // the window size k
        int maxSum=sum; //first subarray sum
        while(right<n){
            sum-=arr[left]; // shrink left
            sum+=arr[right]; // expand on the right
            if(sum>maxSum) maxSum=sum;
            left++;
            right++;
        }
        System.out.println("The max sum is: "+maxSum);
    }
}
