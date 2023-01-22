package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_2211_네트워크복구 {
    static class Node implements Comparable<Node> {
        int s, e, w;
        Node (int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo (Node o) {
            return this.w - o.w;
        }
    }
    static int N, M;
    static ArrayList<Node> [] nodes;
    static ArrayList<Node> res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList [N+1];

        for (int i = 0 ; i <= N ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(s, e, w));
            nodes[e].add(new Node(e, s, w));
        }

        res = new ArrayList<>();

        bfs();

        sb.append(res.size()).append("\n");
        for (Node n : res) sb.append(n.s).append(" ").append(n.e).append("\n");

        System.out.print(sb);
    }

    static void bfs () {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [] visited = new boolean[N+1];

        queue.offer(new Node(-1,1, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.e]) continue;

            visited[now.e] = true;

            if (now.s != -1) {
                res.add(now);
            }

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);


                queue.offer(new Node(now.e, next.e, now.w + next.w));
            }
        }
    }
}
