// Find the second most frequent character in the given string
// CONSTRAINTS: No hashmap / freq array.. just using "for" loops

import java.util.*;
public class SecondMostFrequentChar{
    public static void main(String[] args) {
        String str="aabbbcba";
        int max1=0;
        int max2=0;
        // boolean[] visited=new boolean[26];
        int n=str.length();
        char ch1=' ';
        char ch2=' ';
        for(int i=0;i<n;i++){
            int c=0;
            char ch=str.charAt(i);
            //if(!visited[ch-'a']){
                for(int j=i+1;j<n;j++){
                    if(str.charAt(j)==ch) c++;
                }
                if(c>max1){
                    ch2=ch1;
                    max2=max1;
                    ch1=ch;
                    max1=c;
                }else if(c>max2){
                    ch2=ch;
                    max2=c;
                }
            //}
            //visited[ch-'a']=true;
        }
        System.out.println("Second most frequent character: "+ch2);
    }
    
}
