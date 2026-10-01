import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int q = sc.nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(l);
        while(q-->0 ){
            int c = sc.nextInt();
            int val = sc.nextInt();
            if(c == 1){
                set.add(val);
            }
            else{
                int lower = set.lower(val), higher = set.higher(val);
                System.out.println(higher-lower);
            }
        }
    }
}
