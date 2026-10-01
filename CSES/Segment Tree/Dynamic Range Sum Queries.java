import java.io.*;
 
class Main {
 
    public static void update(long[] seg_tree, int val, int i, int l, int r, int req) {
        if (l > req || r < req) return;
 
        if (l == r) {
            seg_tree[i] = val;
            return;
        }
 
        int mid = l + (r - l) / 2;
 
        if (mid >= req) {
            update(seg_tree, val, 2 * i + 1, l, mid, req);
        } else {
            update(seg_tree, val, 2 * i + 2, mid + 1, r, req);
        }
 
        seg_tree[i] = seg_tree[i * 2 + 1] + seg_tree[i * 2 + 2];
    }
 
    public static long search(long[] seg_tree, int i, int l, int r, int ql, int qr) {
        if (r < ql || l > qr) {
            return 0;
        }
 
        if (ql <= l && r <= qr) {
            return seg_tree[i];
        }
 
        int mid = l + (r - l) / 2;
 
        long left = search(seg_tree, 2 * i + 1, l, mid, ql, qr);
        long right = search(seg_tree, 2 * i + 2, mid + 1, r, ql, qr);
 
        return left + right;
    }
 
    public static void build(long[] seg_tree, int[] arr, int i, int l, int r) {
        if (l == r) {
            seg_tree[i] = arr[l];
            return;
        }
 
        int mid = l + (r - l) / 2;
 
        build(seg_tree, arr, i * 2 + 1, l, mid);
        build(seg_tree, arr, i * 2 + 2, mid + 1, r);
 
        seg_tree[i] = seg_tree[2 * i + 1] + seg_tree[2 * i + 2];
    }
 
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
 
    public static void main(String[] args) throws Exception {
 
        FastScanner sc = new FastScanner();
 
        int N = sc.nextInt();
        int Q = sc.nextInt();
 
        int[] arr = new int[N];
        long[] seg_tree = new long[4 * N];
 
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();
 
        build(seg_tree, arr, 0, 0, N - 1);
 
        StringBuilder out = new StringBuilder();
 
        while (Q-- > 0) {
 
            int C = sc.nextInt();
            int A = sc.nextInt() - 1;
            int B = sc.nextInt();
 
            if (C == 2) {
 
                out.append(
                    search(seg_tree, 0, 0, N - 1, A, B - 1)
                ).append('\n');
 
            } else {
 
                update(seg_tree, B, 0, 0, N - 1, A);
            }
        }
 
        System.out.print(out);
 
        // System.out.println(search(seg_tree, 0, 0, N-1, A, B));
    }
}
