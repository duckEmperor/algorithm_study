package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_11437_LCA {

    static class Node {
        int d, p;
        Node (int d, int p) {
            this.d = d;
            this.p = p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Node [] nodes = new Node[N + 1];
        nodes[1] = new Node(1, 1);

        ArrayList<Integer> [] input = new ArrayList[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            input[i] = new ArrayList<>();
        }

        for (int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            input[s].add(e);
            input[e].add(s);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean [] visited = new boolean[N + 1];
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < input[now].size(); i++) {
                int next = input[now].get(i);

                if (visited[next]) continue;

                visited[next] = true;
                queue.offer(next);
                nodes[next] = new Node(nodes[now].d + 1, now);
            }
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (nodes[x].d > nodes[y].d) {
                while (nodes[x].d != nodes[y].d) {
                    x = nodes[x].p;
                }
            } else {
                while (nodes[x].d != nodes[y].d) {
                    y = nodes[y].p;
                }
            }

            while (x != y) {
                x = nodes[x].p;
                y = nodes[y].p;
            }

            sb.append(x).append("\n");

        }

        System.out.print(sb);
    }
}
