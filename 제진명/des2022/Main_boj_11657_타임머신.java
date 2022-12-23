package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_11657_타임머신 {
    static class Node {
        int s, e, w;
        Node (int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    static int N, M;
    static Node [] nodes;
    static long [] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[M];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(s, e, w);
        }

        dist = new long[N + 1];

        Arrays.fill(dist, 987654321);

        boolean cycle = bellmanFord();

        if (cycle) System.out.println(-1);
        else {
            for (int i = 2 ; i <= N ; i++) {
                if (dist[i] == 987654321) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }
    }

    static boolean bellmanFord () {
        dist[1] = 0;

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                Node now = nodes[j];

                if (dist[now.s] != 987654321 && dist[now.e] > dist[now.s] + now.w) {
                    dist[now.e] = dist[now.s] + now.w;

                    if (i == N) return true;
                }
            }
        }

        return false;
    }
}
