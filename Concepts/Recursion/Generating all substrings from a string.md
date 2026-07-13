## Recursion - Generating all substrings from a string

``` java

import java.util.*;

class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Set<String> ans=new TreeSet<>();
        int left=0;
        int right=s.length();
        helper(left,right,s,ans);
        for(String str:ans) System.out.println(str);

    }
    public static void helper(int left,int right,String s,Set<String> set){

        if(left>=right) return;
        if(s.substring(left,right).length()!=0) set.add(s.substring(left,right));
        helper(left,right-1,s,set);
        helper(left+1,right,s,set);

    }
}

```
