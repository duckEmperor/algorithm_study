package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1976_여행가자 {

    static int [] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N];

        int [][] map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            parent[i] = i;
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = i+1 ; j < N ; j++) {
                if (map[i][j] == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());

        int [] route = new int[M];

        route[0] = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 1 ; i < M ; i++) {
            route[i] = Integer.parseInt(st.nextToken()) - 1;
            if (find(route[i-1]) != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

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
