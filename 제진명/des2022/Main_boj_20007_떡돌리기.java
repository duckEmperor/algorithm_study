package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_20007_떡돌리기 {

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

    static int N, M, X, Y;
    static ArrayList<Node> [] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N];

        for (int i = 0 ; i < N ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, w));
            nodes[e].add(new Node(s, w));
        }

        System.out.println(dijkstra());

    }

    static int dijkstra () {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int [] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new Node(Y, 0));
        dist[Y] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);

                if (dist[next.e] <= dist[now.e] + next.w) continue;

                dist[next.e] = dist[now.e] + next.w;
                queue.offer(new Node(next.e, dist[next.e]));
            }
        }


        int today = 0;
        int days = 1;

        Arrays.sort(dist);

        for (int i = 0 ; i < N ; i++) {
            if (dist[i] * 2 > X) return -1;
            if (today + dist[i] * 2 > X) {
                today = 0;
                days++;
            }
            today += dist[i] * 2;
        }

        return days;
    }
}
