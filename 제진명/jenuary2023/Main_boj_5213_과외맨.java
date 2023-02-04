package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_5213_과외맨 {

    static class Block {
        int idx, num;
        Block (int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    static int N, blockCnt;
    static Block [][] blocks;
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        blocks = new Block[N][N * 2];

        blockCnt = 1;

        for (int i = 0 ; i < N ; i++) {
            for (int j = (i % 2 == 0 ? 0 : 1) ; j < (i % 2 == 0 ? N * 2 : N * 2 -1) ; j+=2) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                blocks[i][j] = new Block(blockCnt, s);
                blocks[i][j + 1] = new Block(blockCnt, e);
                blockCnt++;
            }
        }

        Node res = bfs();

        System.out.println(res.c);
        System.out.println(res.route);

    }

    static class Node implements Comparable<Node> {
        Point p;
        int c;
        String route;
        Node (Point p, int c, String route) {
            this.p = p;
            this.c = c;
            this.route = route;
        }

        @Override
        public int compareTo (Node o) {
            return this.c - o.c;
        }
    }

    static Node bfs () {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [][] visited = new boolean[N][N * 2];

        queue.offer(new Node(new Point(0, 0), 1,   "1"));

        Node max = queue.peek();

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (blocks[now.p.x][now.p.y].idx == blockCnt - 1) return now;

            if (blocks[max.p.x][max.p.y].idx < blocks[now.p.x][now.p.y].idx) {
                max = now;
            }

            if (visited[now.p.x][now.p.y]) continue;

            visited[now.p.x][now.p.y] = true;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.p.x + D[d][0];
                int ny = now.p.y + D[d][1];

                if (isOverflow(nx, ny) || visited[nx][ny]) continue;

                boolean isSameBlock = blocks[now.p.x][now.p.y].idx == blocks[nx][ny].idx;

                if (!isSameBlock && blocks[now.p.x][now.p.y].num != blocks[nx][ny].num) continue;

                queue.offer(new Node(new Point(nx, ny), isSameBlock ? now.c : now.c + 1, isSameBlock ? now.route : now.route + " " + blocks[nx][ny].idx));

            }
        }

        return max;

    }

    static boolean isOverflow (int nx, int ny) {
        // nx 가 홀수 일때와 짝수 일때
        boolean odd = nx % 2 == 0;
        return nx < 0 || nx >= N || ny < (odd ? 0 : 1) || ny >= (odd ? N * 2 : N * 2 - 1);
    }

}
