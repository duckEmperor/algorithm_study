package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_12834_주간미팅 {

    static class Node implements Comparable<Node> {

        int e, w;

        public Node (int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static int N, V, E;
    static int A, B;
    static int [] homes;
    static ArrayList<Node> [] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;

        homes = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            homes[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        nodes = new ArrayList[V];

        for (int i = 0 ; i < V ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, w));
            nodes[e].add(new Node(s, w));
        }

        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            answer += dijkstra(homes[i]);
        }

        System.out.println(answer);
    }

    static int dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int [] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.w > dist[now.e]) continue;

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);

                if (dist[next.e] < dist[now.e] + next.w) continue;

                dist[next.e] = dist[now.e] + next.w;
                queue.offer(new Node(next.e, dist[next.e]));
            }
        }

        int res = 0;
        res += dist[A] == Integer.MAX_VALUE ? -1 : dist[A];
        res += dist[B] == Integer.MAX_VALUE ? -1 : dist[B];

        return res;
    }
}
