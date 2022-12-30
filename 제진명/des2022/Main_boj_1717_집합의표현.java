package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1717_집합의표현 {

    static int [] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];

        for (int n = 0 ; n <= N ; n++) {
            parent[n] = n;
        }

        for (int m = 0 ; m < M ; m++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (c == 1) {
                if (find(u) == find(v)) sb.append("YES\n");
                else sb.append("NO\n");
            } else {
                union(u, v);
            }
        }
        System.out.print(sb);
    }

    static void union (int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    static int find (int a) {
        if (parent[a] == a) return a;
        else return find(parent[a]);
    }
}
