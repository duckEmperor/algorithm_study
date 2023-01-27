package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_16946_벽부수고이동하기4 {

    static int N, M;
    static int [][] map, res, D = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    static ArrayList<Integer> resCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int [N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();

        int [][] ans = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 0) {
                    sb.append(0);
                    continue;
                }
                boolean [] visited = new boolean[resCnt.size()];
                ans[i][j] = 1;
                for (int d = 0 ; d < 4 ; d++) {
                    int nx = i + D[d][0];
                    int ny = j + D[d][1];

                    if (isOverflow(nx, ny) || visited[res[nx][ny]]) continue;

                    visited[res[nx][ny]] = true;
                    ans[i][j] += resCnt.get(res[nx][ny]);
                }
                sb.append(ans[i][j]%10);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
    static void bfs () {
        Queue<Point> queue = new LinkedList<>();
        boolean [][] visited = new boolean[N][M];
        res = new int[N][M];
        int idx = 0;
        resCnt = new ArrayList<>();
        resCnt.add(0);

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (visited[i][j] || map[i][j] == 1) continue;

                idx++;
                int cnt = 1;
                visited[i][j] = true;
                res[i][j] = idx;
                queue.offer(new Point(i, j));

                while (!queue.isEmpty()) {
                    Point now = queue.poll();

                    for (int d = 0 ; d < 4 ; d++) {
                        int nx = now.x + D[d][0];
                        int ny = now.y + D[d][1];

                        if (isOverflow(nx, ny) || map[nx][ny] == 1 || visited[nx][ny]) continue;

                        visited[nx][ny] = true;
                        res[nx][ny] = idx;
                        cnt++;

                        queue.offer(new Point(nx, ny));
                    }
                }

                resCnt.add(cnt);
            }
        }
    }

    static boolean isOverflow (int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}
