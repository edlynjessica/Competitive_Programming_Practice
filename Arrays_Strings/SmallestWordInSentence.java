// Find the Smallest word in the given string
// Constraints: No split(), no in built methods

import java.util.*;
public class SmallestWordInSentence{
    public static void main(String[] args) {
        String str="I love java programming";
        List<String> ans=new ArrayList<>();
        int n=str.length();
        int j=0;
        while(j<n){
            int i=j;
            StringBuilder sb=new StringBuilder();
            while(i<n && str.charAt(i)!=' '){
                sb.append(str.charAt(i));
                i++;
            }
            ans.add(sb.toString());
            j=i+1;
        }
        int min=Integer.MAX_VALUE;
        int lens=ans.size();
        int[] lengths=new int[lens];
        for(int i=0;i<lens;i++){
            String s=ans.get(i);
            lengths[i]=s.length();
            if(lengths[i]<min) min=lengths[i];
        }
        for(int i=0;i<lens;i++){
            if(lengths[i]==min){
                System.out.println(ans.get(i));
            }
        }
    }
    
}
