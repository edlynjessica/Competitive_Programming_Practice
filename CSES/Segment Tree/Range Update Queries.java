import java.util.*;
import java.io.*;
 
public class Main {
    static class FastScanner {
 
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;
 
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
 
                if (len <= 0) return -1;
            }
 
            return buffer[ptr++];
        }
 
        int nextInt() throws IOException {
            int c;
 
            do {
                c = read();
            } while (c <= ' ');
 
            int sign = 1;
 
            if (c == '-') {
                sign = -1;
                c = read();
            }
 
            int num = 0;
 
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
 
            return num * sign;
        }
    }
 
    public static void update_range(long[]seg_tree, int i , int l, int r, int start, int end, int val){
        if(r<start || l>end || l>r) return;
        if(l>=start && r<= end) {
            seg_tree[i] += val;
            return ;
        }
        int mid = l + (r-l)/2;
 
        update_range(seg_tree, 2*i+1, l, mid, start, end, val);
        update_range(seg_tree, 2*i+2, mid+1, r, start, end, val);
    }
    public static long get_val(long[] seg_tree, int i,int l, int r, int pos, long cur_sum){
        if(l> pos || r < pos) return 0;
        if(l== r) return cur_sum+seg_tree[i];
        int mid = l+ (r-l)/2;
        if(mid < pos)  return get_val(seg_tree, 2*i+2, mid+1, r , pos, cur_sum+seg_tree[i]);
        return get_val(seg_tree, 2*i+1, l, mid, pos, cur_sum+seg_tree[i]);
    }
    public static void main(String[] args) throws IOException {
 
        FastScanner sc = new FastScanner();
        int N = sc.nextInt() , Q = sc.nextInt();
        StringBuilder out = new StringBuilder();
 
        int[] arr =new int[N];
        for(int i=0 ; i<N; i++) arr[i] = sc.nextInt();
 
        long[] seg_tree=  new long[4*N];
        while(Q -- > 0){
            int C = sc.nextInt();
            if(C == 1){
                int start = sc.nextInt()-1,  end = sc.nextInt()-1 , val = sc.nextInt();
                update_range(seg_tree, 0, 0, N-1, start, end , val);
            }
            else{
                int pos = sc.nextInt()-1;
                out.append(arr[pos] + get_val(seg_tree, 0 , 0 ,N-1, pos,0 )).append("\n");
            }
        }
        System.out.println(out);
//        for(long val : seg_tree)System.out.print(val+" ");
    }
 
}
