package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_14284_간선이어가기2 {

    static class Node implements Comparable<Node>{
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

    static int N, M;
    static ArrayList<Node> [] nodes;
    static int s, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];

        for (int i = 0 ; i < N+1 ; i++) {
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

        st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        System.out.println(solution());

    }

    static int solution () {
        int [] dist = new int[N+1];
        Arrays.fill(dist, 987654321);

        dist[s] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(s, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.e] < now.w) {
                continue;
            }

            for (int i = 0; i < nodes[now.e].size(); i++) {
                Node next = nodes[now.e].get(i);
                if (dist[next.e] > now.w + next.w) {
                    dist[next.e] = now.w + next.w;

                    queue.offer(new Node(next.e, dist[next.e]));
                }
            }
        }

        return dist[t];
    }
}
