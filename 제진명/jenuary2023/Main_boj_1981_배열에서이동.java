package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_1981_배열에서이동 {
    static int N;
    static int [][] map;
    static int max = 0, min = 200;
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        int start = 0;
        int end = max - min;
        int answer = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            boolean flag = false;

            for (int i = min ; i <= max ; i++) {
                if (i <= map[0][0] && map[0][0] <= i + mid) {
                    if (flag = bfs(i, i + mid)) break;
                }
            }

            if (flag) {
                end = mid - 1;
                answer = Math.min(answer, mid);
            } else start = mid + 1;
        }

        System.out.println(answer);

    }

    static boolean bfs (int min, int max) {
        Queue<Point> queue = new LinkedList<>();
        boolean [][] visited = new boolean[N][N];

        queue.offer(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.x == N - 1 && now.y == N - 1) {
                return true;
            }

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (isOverflow(nx, ny) || visited[nx][ny] || map[nx][ny] > max || map[nx][ny] < min) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }

        return false;
    }

    static boolean isOverflow (int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return true;
        return false;
    }
}
