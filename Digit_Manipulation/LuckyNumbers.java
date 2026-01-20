/*

Numbers are considered as lucky and unlucky by people based on their experiences. 
The upper bound(U) and the lower bound(L) of a span of numbers is given. 
Also an unlucky digit D is provided. 
The program must find all the numbers between U and L such that
the sum of the digits on the right side of the number must be lesser than the digit on its left. 
Eg: 9520 is a lucky number as 2>0, 5>2+0, 9>5+2+0.
the number must not consist of the unlucky digit D.

Input Format:
The first line contains U.
The second line contains L.
The third line contains D.

example input: 
510
550
3

example output:
510 520 521 540

#TCS NQT
*/



import java.util.*;

public class LuckyNumbers{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int u=sc.nextInt();
        int l=sc.nextInt();
        int d=sc.nextInt();
        int step= (u<l)? 1:-1;
        for(int i=u;i<=l;i+=step){
            int num=i;
            boolean flag=true;
            int sum=0;
            while(num>0){
                int digit=num%10;
                if(digit==d){
                    flag=false;
                    break;
                }
                if(digit<=sum && sum!=0){
                    flag=false;
                    break;
                }
                sum+=digit;
                num/=10;
            }
            if(flag){
                System.out.print(i+" ");
            }
        }
    }
}

