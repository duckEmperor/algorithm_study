package 제진명.february2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_5022_연결 {

    static int N, M;
    static int [][] D = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Point [] A = new Point[2];
        Point [] B = new Point[2];

        for (int i = 0 ; i < 2 ; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0 ; i < 2 ; i++) {
            st = new StringTokenizer(br.readLine());
            B[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int res1 = findRoute(A[0], A[1], B[0], B[1]);
        int res2 = findRoute(B[0], B[1], A[0], A[1]);

        if (res1 == -1 && res2 == -1) System.out.println("IMPOSSIBLE");
        else if (res1 != -1 && res2 != -1) System.out.println(Math.min(res1, res2));
        else if (res1 != -1 || res2 != -1) System.out.println(Math.max(res1, res2));

    }

    static class Node implements Comparable<Node> {
        Point bp, np;
        int cnt;

        Node (Point bp, Point np, int cnt) {
            this.bp = bp;
            this.np = np;
            this.cnt = cnt;
        }

        @Override
        public int compareTo (Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int findRoute (Point start, Point end, Point start2, Point end2) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [][] visited = new boolean[N + 1][M + 1];
        visited[start2.x][start2.y] = true;
        visited[end2.x][end2.y] = true;

        Point [][] before = new Point[N + 1][M + 1];

        queue.offer(new Node(start, start, 0));

        int res = -1;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.np.x == end.x && now.np.y == end.y) {
                before[now.np.x][now.np.y] = now.bp;
                res = now.cnt;
                break;
            }

            if (visited[now.np.x][now.np.y]) continue;

            visited[now.np.x][now.np.y] = true;
            before[now.np.x][now.np.y] = now.bp;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.np.x + D[d][0];
                int ny = now.np.y + D[d][1];

                if (isOverflow(nx, ny) || visited[nx][ny]) continue;

                queue.offer(new Node(now.np, new Point(nx, ny), now.cnt + 1));
            }
        }

        visited = new boolean[N + 1][M + 1];

        Point temp = new Point(end.x, end.y);

        while (temp.x != start.x || temp.y != start.y) {
            visited[temp.x][temp.y] = true;
            temp = before[temp.x][temp.y];
        }

        visited[start.x][start.y] = true;

        int cnt = findRoute2(visited, start2, end2);

        return cnt == -1 ? -1 : cnt + res;
    }

    static int findRoute2 (boolean [][] visited, Point start, Point end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(start, start, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.np.x == end.x && now.np.y == end.y) {
                return now.cnt;
            }

            if (visited[now.np.x][now.np.y]) continue;

            visited[now.np.x][now.np.y] = true;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.np.x + D[d][0];
                int ny = now.np.y + D[d][1];

                if (isOverflow(nx, ny) || visited[nx][ny]) continue;

                queue.offer(new Node(now.np, new Point(nx, ny), now.cnt + 1));
            }
        }

        return -1;
    }

    static boolean isOverflow(int nx, int ny) {
        return nx < 0 || ny < 0 || nx > N || ny > M;
    }
}
