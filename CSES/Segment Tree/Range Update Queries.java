import java.util.*;

public class Main {
    public static void update(Queue<Integer> remove_q,List<Queue<Integer>> l_q,List<List<Integer>> lst, int i){
        if(l_q.get(i).isEmpty()) return;
        int l_val = l_q.get(i).poll();
        lst.get(l_val).add(i);
        if(lst.get(l_val).size()==2){
            remove_q.offer(lst.get(l_val).get(0));
            remove_q.offer(lst.get(l_val).get(1));
        }
        while(!remove_q.isEmpty()){
            int v1 = remove_q.poll();
//                int v1 = l_q.get(val).poll();
            update(remove_q, l_q, lst,v1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<List<Integer>> lst = new ArrayList<>();
        Queue<Integer> remove_q = new ArrayDeque<>();

        List<Queue<Integer>> l_q = new ArrayList<>();
        for(int i=0; i<=N; i++ ) lst.add(new ArrayList<>());

        for(int i=0 ;i<M ;i++){
            int K = sc.nextInt();

            l_q.add(new ArrayDeque<>());
            for(int j=0; j<K; j++) l_q.get(i).offer(sc.nextInt());
            update(remove_q,l_q, lst, i);
        }
//        System.out.println(l_q);
        for(Queue<Integer> t_q : l_q){
            if(!t_q.isEmpty()){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
        //System.out.println(map);
    }

}
