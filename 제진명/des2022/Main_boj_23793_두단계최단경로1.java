package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_23793_두단계최단경로1 {
    static class Node implements Comparable<Node> {
        int e, w;

        Node (int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static int N, M, X, Y, Z;
    static ArrayList<Node> [] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N];

        for (int i = 0 ; i < N ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            nodes[Integer.parseInt(st.nextToken()) - 1].add(new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
        Z = Integer.parseInt(st.nextToken()) - 1;

        int XtoY = dijkstra(X, Y, -1);
        if (XtoY != -1) {
            int YtoZ = dijkstra(Y, Z, -1);
            if (YtoZ != -1) sb.append(XtoY + YtoZ);
            else sb.append(-1);
        } else sb.append(-1);

        int XtoZ = dijkstra(X, Z, Y);
        sb.append(" " + XtoZ);

        System.out.println(sb);
    }

    static int dijkstra (int start, int end, int pass) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int [] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.e] < now.w) continue;

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);

                if (next.e == pass) continue;

                if (dist[next.e] <= dist[now.e] + next.w) continue;

                dist[next.e] = dist[now.e] + next.w;
                queue.offer(new Node(next.e, dist[next.e]));
            }
        }

        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }
}
