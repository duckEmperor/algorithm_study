package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_boj_13913_숨바꼭질4 {

    static int [] before;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int res = bfs(N, K);

        sb.append(res).append("\n");

        int start = K;


        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (start != N) {
            stack.push(before[start]);
            start = before[start];
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.print(sb);

    }

    static class Node implements Comparable<Node> {
        int b, p, c;
        Node (int b, int p, int c) {
            this.b = b;
            this.p = p;
            this.c = c;
        }

        @Override
        public int compareTo (Node o) {
            return this.c - o.c;
        }
    }

    static int bfs (int N, int K) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [] visited = new boolean[Math.max(N, K) * 2 + 1];
        before = new int[Math.max(N, K) * 2 + 1];

        queue.offer(new Node(N, N, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.p == K) {
                before[now.p] = now.b;
                return now.c;
            }

            if (visited[now.p]) continue;

            visited[now.p] = true;
            before[now.p] = now.b;

            if (now.p - 1 >= 0 && !visited[now.p - 1]) queue.offer(new Node(now.p, now.p - 1, now.c + 1));
            if (now.p + 1 <= Math.max(N, K) && !visited[now.p + 1]) queue.offer(new Node(now.p, now.p + 1, now.c + 1));
            if (now.p * 2 <= Math.max(N, K) * 2 && !visited[now.p * 2]) queue.offer(new Node(now.p, now.p * 2, now.c + 1));
        }

        return -1;
    }
}
