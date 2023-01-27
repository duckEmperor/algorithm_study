package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_23840_두단계최단경로4 {
    static class Node implements Comparable<Node> {
        int e;
        long w;
        Node (int e, long w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo (Node o) {
            return this.w > o.w ? 1 : this.w == o.w ? 0 : -1;
        }
    }
    static int N, M, P, statusFullBit;
    static long INF = 1_000_000_000_000L;
    static int [] Y;
    static long [][] cost, dp;
    static ArrayList<Node> [] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];

        for (int i = 1 ; i <= N ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new Node(v, w));
            nodes[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Z = Integer.parseInt(st.nextToken());

        P = Integer.parseInt(br.readLine());

        Y = new int[P + 2];
        st = new StringTokenizer(br.readLine());

        Y[0] = X;
        for (int i = 1 ; i <= P ; i++) {
            Y[i] = Integer.parseInt(st.nextToken());
        }
        Y[P+1] = Z;

        cost = new long[P+2][P+2];

        for (int i = 1 ; i <= P ; i++) {
            long [] res = dijkstra(Y[i]);

            // 다익스트라의 결과 배열을 받고
            // X, Z, Y에 대한 경로만 cost 배열에 담음.
            for (int j = 0 ; j < P+2 ; j++) {
                cost[i][j] = res[Y[j]];
                cost[j][i] = res[Y[j]];
            }
        }

        // X -> Y -> Z로 이동하는 외판원 순회
        // 비트 마스킹에 X는 포함하되 Z는 포함하지 않음.
        statusFullBit = (1 << P+1) - 1;

        dp = new long[P+1][statusFullBit];

        for (int i = 0 ; i < P+1 ; i++) {
            Arrays.fill(dp[i], -1);
        }

        long res = tsp(0, 1);

        System.out.println(res == INF ? -1 : res);
    }

    static long [] dijkstra (int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        long [] dist = new long[N+1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.e] < now.w) continue;

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);

                if (dist[next.e] > dist[now.e] + next.w) {
                    dist[next.e] = dist[now.e] + next.w;
                    queue.offer(new Node(next.e, dist[next.e]));
                }
            }
        }

        return dist;
    }

    static long tsp (int x, int check) {
        if (check == statusFullBit) {
            return cost[x][P+1] == 0 ? INF : cost[x][P+1];
        }

        if (dp[x][check] != -1) return dp[x][check];

        dp[x][check] = INF;

        for (int i = 1 ; i < P+1 ; i++) {
            int next = check | (1 << i);

            if (cost[x][i] == 0 || (check & (1 << i)) != 0) continue;

            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + cost[x][i]);
        }

        return dp[x][check];
    }

}
