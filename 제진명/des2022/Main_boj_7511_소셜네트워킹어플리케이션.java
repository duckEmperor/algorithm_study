package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_7511_소셜네트워킹어플리케이션 {

    static int [] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {

            sb.append("Scenario " + t + ":\n");

            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());

            parent = new int [N];

            for (int n = 0 ; n < N ; n++) {
                parent[n] = n;
            }

            for (int k = 0 ; k < K ; k++) {
                st = new StringTokenizer(br.readLine());

                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());

            for (int m = 0 ; m < M ; m++) {
                st = new StringTokenizer(br.readLine());

                if (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))) sb.append(1 + "\n");
                else sb.append(0 + "\n");
            }

            if (t != T) sb.append("\n");
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
        else return  parent[a] = find(parent[a]);
    }
}
